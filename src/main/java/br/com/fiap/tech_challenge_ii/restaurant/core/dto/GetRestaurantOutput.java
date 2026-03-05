package br.com.fiap.tech_challenge_ii.restaurant.core.dto;

public record GetRestaurantOutput(String name,
                                  AddressDTO address,
                                  String kitchenType,
                                  WeeklyScheduleDTO openingHours
) {
}
