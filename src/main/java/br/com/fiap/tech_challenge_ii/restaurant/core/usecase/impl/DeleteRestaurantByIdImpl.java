package br.com.fiap.tech_challenge_ii.restaurant.core.usecase.impl;

import br.com.fiap.tech_challenge_ii.restaurant.core.exception.NotFoundException;
import br.com.fiap.tech_challenge_ii.restaurant.core.gateway.RestaurantGateway;
import br.com.fiap.tech_challenge_ii.restaurant.core.usecase.DeleteRestaurantById;

public class DeleteRestaurantByIdImpl implements DeleteRestaurantById {
    private final RestaurantGateway restaurantGateway;

    public DeleteRestaurantByIdImpl(RestaurantGateway restaurantGateway) {
        this.restaurantGateway = restaurantGateway;
    }

    @Override
    public void deleteRestaurantById(Long userId, Long restaurantId) {
        var restaurant = restaurantGateway.findById(restaurantId)
                .orElseThrow(() -> new NotFoundException(""));

        //TODO: Check if logged user is restaurant owner
        restaurantGateway.deleteById(restaurantId);
    }
}
