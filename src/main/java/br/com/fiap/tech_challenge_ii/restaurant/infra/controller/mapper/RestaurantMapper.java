package br.com.fiap.tech_challenge_ii.restaurant.infra.controller.mapper;

import br.com.fiap.tech_challenge_ii.restaurant.core.dto.AddressDTO;
import br.com.fiap.tech_challenge_ii.restaurant.core.dto.CreateRestaurantInput;
import br.com.fiap.tech_challenge_ii.restaurant.core.dto.CreateRestaurantOutput;
import br.com.fiap.tech_challenge_ii.restaurant.infra.controller.json.AddressRequest;
import br.com.fiap.tech_challenge_ii.restaurant.infra.controller.json.CreateRestaurantRequest;
import br.com.fiap.tech_challenge_ii.restaurant.infra.controller.json.CreateRestaurantResponse;
import org.springframework.stereotype.Component;

@Component
public class RestaurantMapper {

    public CreateRestaurantInput toInput(CreateRestaurantRequest request) {
        return new CreateRestaurantInput(request.name(),
                toAddressInput(request.address()),
                request.kitchenType(),
                request.openingHours(),
                request.ownerId());
    }

    public CreateRestaurantResponse toResponse(CreateRestaurantOutput restaurant) {
        return new CreateRestaurantResponse(restaurant.id(),
                restaurant.name(),
                restaurant.kitchenType());
    }

    private AddressDTO toAddressInput(AddressRequest addressDTO){
        return new AddressDTO(addressDTO.id(),
                addressDTO.street(),
                addressDTO.number(),
                addressDTO.neighborhood(),
                addressDTO.city(),
                addressDTO.zipCode());
    }
}
