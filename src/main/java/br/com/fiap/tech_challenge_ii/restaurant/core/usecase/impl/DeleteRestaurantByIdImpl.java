package br.com.fiap.tech_challenge_ii.restaurant.core.usecase.impl;

import br.com.fiap.tech_challenge_ii.restaurant.core.exception.NotFoundException;
import br.com.fiap.tech_challenge_ii.restaurant.core.exception.UnauthorizedOperationException;
import br.com.fiap.tech_challenge_ii.restaurant.core.gateway.RestaurantGateway;
import br.com.fiap.tech_challenge_ii.restaurant.core.usecase.DeleteRestaurantById;

public class DeleteRestaurantByIdImpl implements DeleteRestaurantById {
    private final RestaurantGateway restaurantGateway;

    public DeleteRestaurantByIdImpl(RestaurantGateway restaurantGateway) {
        this.restaurantGateway = restaurantGateway;
    }

    @Override
    public void deleteById(Long userId, Long restaurantId) {
        var restaurant = restaurantGateway.findById(restaurantId)
                .orElseThrow(() -> new NotFoundException("Restaurant not found"));

        if(!restaurant.getOwnerId().equals(userId)) {
            throw new UnauthorizedOperationException("User not authorized to delete this restaurant");
        }
        restaurantGateway.deleteById(restaurantId);
    }
}
