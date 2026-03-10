package br.com.fiap.tech_challenge_ii.menu.core.usecase.impl;

import org.springframework.stereotype.Service;

import br.com.fiap.tech_challenge_ii.menu.core.domain.MenuItem;
import br.com.fiap.tech_challenge_ii.menu.core.domain.valueObjects.Restaurant;
import br.com.fiap.tech_challenge_ii.menu.core.exception.MenuItemNotFoundException;
import br.com.fiap.tech_challenge_ii.menu.core.exception.RestaurantNotFoundException;
import br.com.fiap.tech_challenge_ii.menu.core.exception.UnauthorizedException;
import br.com.fiap.tech_challenge_ii.menu.core.exception.UserNotFoundException;
import br.com.fiap.tech_challenge_ii.menu.core.gateway.MenuItemGateway;
import br.com.fiap.tech_challenge_ii.menu.core.gateway.RestaurantGateway;
import br.com.fiap.tech_challenge_ii.menu.core.gateway.UserGateway;
import br.com.fiap.tech_challenge_ii.menu.core.usecase.DeleteMenuItemUseCase;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DeleteMenuItemUseCaseImpl implements DeleteMenuItemUseCase {

    private final MenuItemGateway menuItemGateway;
    private final UserGateway userGateway;
    private final RestaurantGateway restaurantGateway;

    @Override
    public void delete(Long menuItemId, Long userId) {

        verifyIfUserExists(userId);

        MenuItem menuItemById = getMenuItemById(menuItemId);

        Restaurant restaurant = getRestaurant(menuItemById.getRestaurantId());

        if (!restaurant.isOwnedBy(userId)) {
            throw new UnauthorizedException("User is not the owner of this restaurant");
        }

        menuItemGateway.delete(menuItemById);

    }

    private MenuItem getMenuItemById(Long menuItemId) {
        return menuItemGateway.findById(menuItemId)
                .orElseThrow(
                        () -> new MenuItemNotFoundException("Menu Item with id '%s' not found".formatted(menuItemId)));

    }

    private void verifyIfUserExists(Long userId) {
        userGateway.findUserById(userId)
                .orElseThrow(() -> new UserNotFoundException("User with id '%s' not found".formatted(userId)));
    }

    private Restaurant getRestaurant(Long restaurantId) {
        Restaurant restaurant = restaurantGateway
                .findRestaurantById(restaurantId)
                .orElseThrow(() -> new RestaurantNotFoundException(
                        "Restaurant with id '%s' not found".formatted(restaurantId)));
        return restaurant;
    }

}
