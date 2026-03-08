package br.com.fiap.tech_challenge_ii.restaurant.core.usecase.impl;

import br.com.fiap.tech_challenge_ii.restaurant.core.dto.UpdateRestaurantInput;
import br.com.fiap.tech_challenge_ii.restaurant.core.dto.UpdateRestaurantOutput;
import br.com.fiap.tech_challenge_ii.restaurant.core.gateway.RestaurantGateway;
import br.com.fiap.tech_challenge_ii.restaurant.core.usecase.UpdateRestaurant;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class UpdateRestaurantImpl implements UpdateRestaurant {
    private final RestaurantGateway restaurantGateway;

    @Override
    public UpdateRestaurantOutput update(Long userId, Long restaurantId, UpdateRestaurantInput input) {
        return null;
    }
}
