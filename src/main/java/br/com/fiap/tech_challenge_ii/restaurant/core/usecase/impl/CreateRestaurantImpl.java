package br.com.fiap.tech_challenge_ii.restaurant.core.usecase.impl;

import br.com.fiap.tech_challenge_ii.restaurant.core.domain.KitchenType;
import br.com.fiap.tech_challenge_ii.restaurant.core.domain.Restaurant;
import br.com.fiap.tech_challenge_ii.restaurant.core.domain.valueObjects.Address;
import br.com.fiap.tech_challenge_ii.restaurant.core.dto.CreateRestaurantInput;
import br.com.fiap.tech_challenge_ii.restaurant.core.dto.CreateRestaurantOutput;
import br.com.fiap.tech_challenge_ii.restaurant.core.exception.UnauthorizedOperationException;
import br.com.fiap.tech_challenge_ii.restaurant.core.exception.UserNotFoundException;
import br.com.fiap.tech_challenge_ii.restaurant.core.gateway.RestaurantGateway;
import br.com.fiap.tech_challenge_ii.restaurant.core.gateway.UserGateway;
import br.com.fiap.tech_challenge_ii.restaurant.core.usecase.CreateRestaurant;
import br.com.fiap.tech_challenge_ii.restaurant.core.usecase.mapper.CreateRestaurantOutputMapper;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CreateRestaurantImpl implements CreateRestaurant {
    private final RestaurantGateway restaurantGateway;
    private final UserGateway userGateway;

    @Override
    public CreateRestaurantOutput create(Long userId, CreateRestaurantInput restaurantInput) {
        var user = userGateway.getUserById(userId)
                .orElseThrow(() -> new UserNotFoundException("There is no user with id %s".formatted(userId)));

        if(!user.isOwner()) {
            throw new UnauthorizedOperationException("You are not allowed to perform this operation");
        }

        var newAddress = Address.newAddress(null,
                restaurantInput.address().street(),
                restaurantInput.address().number(),
                restaurantInput.address().neighborhood(),
                restaurantInput.address().city(),
                restaurantInput.address().zipCode());

        var kitchenType = KitchenType.valueOf(restaurantInput.kitchenType().trim().toUpperCase());

        var newRestaurant = Restaurant.newRestaurant(null,
                restaurantInput.name(),
                newAddress,
                kitchenType,
                restaurantInput.openingHours(),
                userId);

        var restaurant = restaurantGateway.create(newRestaurant);
        return CreateRestaurantOutputMapper.from(restaurant);
    }
}
