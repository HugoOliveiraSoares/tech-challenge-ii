package br.com.fiap.tech_challenge_ii.restaurant.core.usecase.mapper;

import br.com.fiap.tech_challenge_ii.restaurant.core.domain.Restaurant;
import br.com.fiap.tech_challenge_ii.restaurant.core.dto.AddressDTO;
import br.com.fiap.tech_challenge_ii.restaurant.core.dto.GetRestaurantOutput;
import br.com.fiap.tech_challenge_ii.restaurant.core.dto.WeeklyScheduleDTO;

public class GetRestaurantOutputMapper {
    public static GetRestaurantOutput from(final Restaurant restaurant) {
        AddressDTO addressDTO = new AddressDTO(restaurant.getAddress().street(),
                restaurant.getAddress().number(),
                restaurant.getAddress().neighborhood(),
                restaurant.getAddress().city(),
                restaurant.getAddress().zipCode());

        WeeklyScheduleDTO weeklyScheduleDTO = WeeklyScheduleMapper.toDTO(restaurant.getOpeningHours());

        return new GetRestaurantOutput(
                restaurant.getName(),
                addressDTO,
                restaurant.getKitchenType().name(),
                weeklyScheduleDTO
        );
    }
}
