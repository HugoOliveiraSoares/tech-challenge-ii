package br.com.fiap.tech_challenge_ii.menu.core.usecase.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import br.com.fiap.tech_challenge_ii.menu.core.domain.MenuItem;
import br.com.fiap.tech_challenge_ii.menu.core.domain.valueObjects.Restaurant;
import br.com.fiap.tech_challenge_ii.menu.core.dto.MenuItemDTO;
import br.com.fiap.tech_challenge_ii.menu.core.exception.ExistingMenuItemException;
import br.com.fiap.tech_challenge_ii.menu.core.exception.RestaurantNotFoundException;
import br.com.fiap.tech_challenge_ii.menu.core.exception.UnauthorizedException;
import br.com.fiap.tech_challenge_ii.menu.core.gateway.MenuItemGateway;
import br.com.fiap.tech_challenge_ii.menu.core.gateway.RestaurantGateway;
import br.com.fiap.tech_challenge_ii.menu.core.usecase.CreateMenuItemUseCase;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CreateMenuItemUseCaseImpl implements CreateMenuItemUseCase {

    private final MenuItemGateway menuItemGateway;
    private final RestaurantGateway restaurantGateway;

    @Override
    public List<Long> save(List<MenuItemDTO> newMenuItens, Long userId) {

        return newMenuItens.stream()
                .map(newItem -> {
                    Restaurant restaurant = restaurantGateway
                            .findRestaurantById(newItem.restaurantId())
                            .orElseThrow(() -> new RestaurantNotFoundException("Restaurant not found"));

                    if (!restaurant.getOwnerId().equals(userId)) { // TODO: Verificar se o usuario existe
                        throw new UnauthorizedException("User is not the owner of this restaurant");
                    }

                    menuItemGateway
                            .findByMenuItemNameAndRestaurantId(newItem.name(), newItem.restaurantId())
                            .ifPresent(existing -> {
                                throw new ExistingMenuItemException(
                                        "Item with name '%s' already exists".formatted(newItem.name()));
                            });

                    MenuItem itemSaved = menuItemGateway.save(new MenuItem(
                            newItem.name(),
                            newItem.description(),
                            newItem.price(),
                            newItem.isOnlyLocalConsuption(),
                            newItem.photoPath(),
                            newItem.restaurantId()));
                    return itemSaved.getId();
                })
                .toList();
    }

}
