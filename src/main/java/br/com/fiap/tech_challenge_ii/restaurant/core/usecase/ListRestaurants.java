package br.com.fiap.tech_challenge_ii.restaurant.core.usecase;

import br.com.fiap.tech_challenge_ii.restaurant.core.dto.ListRestaurantOutput;
import br.com.fiap.tech_challenge_ii.restaurant.core.gateway.filter.RestaurantSearchFilter;

import java.util.List;

public interface ListRestaurants {
    List<ListRestaurantOutput> list(RestaurantSearchFilter filter);
}
