package br.com.fiap.tech_challenge_ii.restaurant.infra.controller.json;


public record CreateRestaurantRequest(String name,
                                      AddressRequest address,
                                      String kitchenType,
                                      String openingHours,
                                      Long ownerId) {
}
