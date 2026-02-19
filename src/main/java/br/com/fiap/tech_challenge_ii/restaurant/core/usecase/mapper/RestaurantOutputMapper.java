package br.com.fiap.tech_challenge_ii.restaurant.core.usecase.mapper;

import br.com.fiap.tech_challenge_ii.restaurant.core.domain.Restaurant;
import br.com.fiap.tech_challenge_ii.restaurant.core.dto.ListRestaurantOutput;

public class RestaurantOutputMapper {
    public static ListRestaurantOutput from(Restaurant restaurant) {
        return new ListRestaurantOutput(
                restaurant.getId(),
                restaurant.getName(),
                restaurant.getKitchenType().toString()
        );
    }
}
