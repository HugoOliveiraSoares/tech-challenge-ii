package br.com.fiap.tech_challenge_ii.restaurant.core.usecase.impl;

import br.com.fiap.tech_challenge_ii.restaurant.core.dto.GetRestaurantOutput;
import br.com.fiap.tech_challenge_ii.restaurant.core.exception.RestaurantNotFoundException;
import br.com.fiap.tech_challenge_ii.restaurant.core.gateway.RestaurantGateway;
import br.com.fiap.tech_challenge_ii.restaurant.core.usecase.GetRestaurantById;
import br.com.fiap.tech_challenge_ii.restaurant.core.usecase.mapper.GetRestaurantOutputMapper;

public class GetRestaurantByIdImpl implements GetRestaurantById {
    private final RestaurantGateway restaurantGateway;

    public GetRestaurantByIdImpl(RestaurantGateway restaurantGateway) {this.restaurantGateway = restaurantGateway;}

    @Override
    public GetRestaurantOutput getById(Long id) {
        var restaurantById = restaurantGateway.findById(id)
                .orElseThrow(RestaurantNotFoundException::new);

        return GetRestaurantOutputMapper.from(restaurantById);
    }
}
