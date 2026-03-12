package br.com.fiap.tech_challenge_ii.restaurant.core.usecase.impl;

import br.com.fiap.tech_challenge_ii.restaurant.core.domain.KitchenType;
import br.com.fiap.tech_challenge_ii.restaurant.core.domain.Restaurant;
import br.com.fiap.tech_challenge_ii.restaurant.core.dto.AddressDTO;
import br.com.fiap.tech_challenge_ii.restaurant.core.dto.CreateRestaurantInput;
import br.com.fiap.tech_challenge_ii.restaurant.core.gateway.RestaurantGateway;
import br.com.fiap.tech_challenge_ii.restaurant.core.gateway.UserGateway;
import br.com.fiap.tech_challenge_ii.user.core.domain.Owner;
import br.com.fiap.tech_challenge_ii.user.core.domain.valueObjects.Address;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Optional;

import static br.com.fiap.tech_challenge_ii.restaurant.core.domain.valueObjects.Address.newAddress;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CreateRestaurantUseCaseImplTest {

    @Mock
    private RestaurantGateway restaurantGateway;

    @Mock
    private UserGateway userGateway;

    @InjectMocks
    private CreateRestaurantImpl createRestaurant;

    @Test
    void create_shouldCreateRestaurant_whenUserTypeIsOwner() {

        CreateRestaurantInput dto = new CreateRestaurantInput(
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
                "seg-sex: 16:00-22:59, dom: 11:00-21:59",
                1L
        );

        Owner ownerUser = new Owner(
                1L,
                "Pedro Souza",
                "pedros@email.com",
                "pedrosz",
                "pass123",
                LocalDateTime.now(),
                new Address("Av. 17 de Agosto",
                        "250",
                        "Casa Forte",
                        "Recife",
                        "51234-567"),
                null
        );

        when(userGateway.getUserById(1L))
                .thenReturn(Optional.of(ownerUser));

        Restaurant restaurant = Restaurant.newRestaurant(
                1L,
                "Bean Pizza",
                newAddress(1L,
                        "Av. Rosa e Silva",
                        "1000",
                        "Aflitos",
                        "Recife",
                        "50000-000"
                ),
                KitchenType.ITALIAN,
                "seg-sex: 16:00-22:59, dom: 11:00-21:59",
                1L
        );

        when(restaurantGateway.create(any())).thenReturn(restaurant);

        var output = createRestaurant.create(dto);

        assertNotNull(output);
        assertEquals("Bean Pizza", output.name());

        verify(userGateway, times(1)).getUserById(1L);
        verify(restaurantGateway, times(1)).create(any());

    }


}
