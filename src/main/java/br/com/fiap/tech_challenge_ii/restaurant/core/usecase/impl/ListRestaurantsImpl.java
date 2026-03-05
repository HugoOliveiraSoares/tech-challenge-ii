package br.com.fiap.tech_challenge_ii.restaurant.core.usecase.impl;

import br.com.fiap.tech_challenge_ii.restaurant.core.domain.Restaurant;
import br.com.fiap.tech_challenge_ii.restaurant.core.dto.ListRestaurantOutput;
import br.com.fiap.tech_challenge_ii.restaurant.core.gateway.RestaurantGateway;
import br.com.fiap.tech_challenge_ii.restaurant.core.usecase.ListRestaurants;
import br.com.fiap.tech_challenge_ii.restaurant.core.gateway.filter.RestaurantSearchFilter;
import br.com.fiap.tech_challenge_ii.restaurant.core.usecase.mapper.ListRestaurantOutputMapper;

import java.util.List;
import java.util.Optional;

public class ListRestaurantsImpl implements ListRestaurants {
    private final RestaurantGateway restaurantGateway;

    public ListRestaurantsImpl(RestaurantGateway restaurantGateway) {this.restaurantGateway = restaurantGateway;}

    @Override
    public List<ListRestaurantOutput> list(RestaurantSearchFilter filter) {
        List<Restaurant> restaurants = Optional.ofNullable(restaurantGateway.findAll(filter))
                .orElse(List.of());

        return restaurants.stream()
                .map(ListRestaurantOutputMapper::from)
                .toList();
    }
}
