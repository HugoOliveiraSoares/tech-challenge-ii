package br.com.fiap.tech_challenge_ii.restaurant.infra.controller.mapper;

import br.com.fiap.tech_challenge_ii.restaurant.core.dto.AddressDTO;
import br.com.fiap.tech_challenge_ii.restaurant.core.dto.CreateRestaurantInput;
import br.com.fiap.tech_challenge_ii.restaurant.core.dto.CreateRestaurantOutput;
import br.com.fiap.tech_challenge_ii.restaurant.core.dto.GetRestaurantOutput;
import br.com.fiap.tech_challenge_ii.restaurant.infra.controller.json.AddressJson;
import br.com.fiap.tech_challenge_ii.restaurant.infra.controller.json.CreateRestaurantRequest;
import br.com.fiap.tech_challenge_ii.restaurant.infra.controller.json.CreateRestaurantResponse;
import br.com.fiap.tech_challenge_ii.restaurant.infra.controller.json.GetRestaurantResponse;
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

    public GetRestaurantResponse toResponse(GetRestaurantOutput restaurant) {
        return new GetRestaurantResponse(restaurant.id(),
                restaurant.name(),
                toAddressResponse(restaurant.addressDTO()),
                restaurant.kitchenType(),
                restaurant.openingHours());
    }

    private AddressDTO toAddressInput(AddressJson addressJson){
        return new AddressDTO(addressJson.id(),
                addressJson.street(),
                addressJson.number(),
                addressJson.neighborhood(),
                addressJson.city(),
                addressJson.zipCode());
    }

    private AddressJson toAddressResponse(AddressDTO addressDTO){
        return new AddressJson(addressDTO.id(),
                addressDTO.street(),
                addressDTO.number(),
                addressDTO.neighborhood(),
                addressDTO.city(),
                addressDTO.zipCode());
    }
}
