package br.com.fiap.tech_challenge_ii.restaurant.core.usecase.impl;

import br.com.fiap.tech_challenge_ii.restaurant.core.domain.Restaurant;
import br.com.fiap.tech_challenge_ii.restaurant.core.domain.valueObjects.User;
import br.com.fiap.tech_challenge_ii.restaurant.core.dto.AddressDTO;
import br.com.fiap.tech_challenge_ii.restaurant.core.dto.CreateRestaurantInput;
import br.com.fiap.tech_challenge_ii.restaurant.core.exception.UnauthorizedOperationException;
import br.com.fiap.tech_challenge_ii.restaurant.core.exception.UserNotFoundException;
import br.com.fiap.tech_challenge_ii.restaurant.core.gateway.RestaurantGateway;
import br.com.fiap.tech_challenge_ii.restaurant.core.gateway.UserGateway;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static br.com.fiap.tech_challenge_ii.restaurant.core.domain.valueObjects.Address.newAddress;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CreateRestaurantUseCaseImplTest {

    @Mock
    private RestaurantGateway restaurantGateway;

    @Mock
    private UserGateway userGateway;

    @InjectMocks
    private CreateRestaurantImpl createRestaurant;

    @Test
    void create_shouldCreateRestaurant_whenUserTypeIsOwner() {

        CreateRestaurantInput json = new CreateRestaurantInput(
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

        User userOwner = new User(1L,"owner");

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
                "Italian",
                "seg-sex: 16:00-22:59, dom: 11:00-21:59",
                1L
        );

        when(userGateway.getUserById(1L))
                .thenReturn(Optional.of(userOwner));

        when(restaurantGateway.create(any(Restaurant.class))).thenReturn(restaurant);

        var output = createRestaurant.create(1L, json);

        assertNotNull(output);
        assertAll(
                () -> assertEquals("Bean Pizza", output.name()),
                () -> assertEquals("ITALIAN", output.kitchenType())
        );

        verify(userGateway, times(1)).getUserById(1L);
        verify(restaurantGateway, times(1)).create(any());

    }

    @Test
    void create_shouldThrowUserNotFoundException_whenUserNotFound(){
        CreateRestaurantInput json = new CreateRestaurantInput(
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

        when(userGateway.getUserById(2L))
                .thenReturn(Optional.empty());

        UserNotFoundException exception = assertThrows(
                UserNotFoundException.class,
                () -> createRestaurant.create(2L, json)
        );

        assertEquals("There is no user with id %s".formatted(2L), exception.getMessage());

        verify(userGateway, times(1)).getUserById(2L);
        verify(restaurantGateway, never()).create(any(Restaurant.class));
    }

    @Test
    void create_shouldThrowUnauthorizedOperationException_whenUserTypeIsNotOwner() {
        CreateRestaurantInput json = new CreateRestaurantInput(
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

        User userCustomer = new User(2L,"customer");

        when(userGateway.getUserById(2L))
                .thenReturn(Optional.of(userCustomer));

        UnauthorizedOperationException exception =  assertThrows(
                UnauthorizedOperationException.class,
                () -> createRestaurant.create(2L, json)
        );

        assertEquals("Only users with OWNER role can create restaurants", exception.getMessage());

        verify(userGateway, times(1)).getUserById(2L);
        verify(restaurantGateway, never()).create(any(Restaurant.class));

    }
}
