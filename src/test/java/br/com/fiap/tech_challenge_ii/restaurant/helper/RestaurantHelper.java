package br.com.fiap.tech_challenge_ii.restaurant.helper;

import br.com.fiap.tech_challenge_ii.restaurant.core.domain.Restaurant;
import br.com.fiap.tech_challenge_ii.restaurant.core.domain.valueObjects.User;
import br.com.fiap.tech_challenge_ii.restaurant.core.dto.AddressDTO;
import br.com.fiap.tech_challenge_ii.restaurant.core.dto.CreateRestaurantInput;

import static br.com.fiap.tech_challenge_ii.restaurant.core.domain.valueObjects.Address.newAddress;

public class RestaurantHelper {
    public static CreateRestaurantInput buildCreateRestaurantInput(){
        return new CreateRestaurantInput(
                "Bean Pizza",
                new AddressDTO(
                        1L,
                        "Av. Rosa e Silva",
                        "1000",
                        "Aflitos",
                        "Recife",
                        "50000-000"
                ),
                "Italian",
                "seg-sex: 16:00-22:59, dom: 11:00-21:59"
        );
    }

    public static Restaurant buildRestaurant(){
        return Restaurant.newRestaurant(
                1L,
                "Bean Pizza",
                newAddress(1L,
                        "Av. Rosa e Silva",
                        "1000",
                        "Aflitos",
                        "Recife",
                        "50000-000"
                ),
                "Italian",
                "seg-sex: 16:00-22:59, dom: 11:00-21:59",
                1L
        );
    }

    public static User buildOwner(){
        return new User(1L,"owner");
    }

    public static User buildCustomer(){
        return new User(2L,"customer");
    }
}
