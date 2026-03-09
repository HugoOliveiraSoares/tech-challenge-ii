package br.com.fiap.tech_challenge_ii.restaurant.core.dto;

import br.com.fiap.tech_challenge_ii.restaurant.core.domain.KitchenType;
import br.com.fiap.tech_challenge_ii.restaurant.core.domain.Restaurant;

public record UpdateRestaurantOutput(Long id,
                                     String name,
                                     AddressDTO address,
                                     KitchenType kitchenType,
                                     String openingHours) {
}
