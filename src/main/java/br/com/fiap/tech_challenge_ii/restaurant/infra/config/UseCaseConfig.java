package br.com.fiap.tech_challenge_ii.restaurant.infra.config;

import br.com.fiap.tech_challenge_ii.restaurant.core.gateway.RestaurantGateway;
import br.com.fiap.tech_challenge_ii.restaurant.core.gateway.UserGateway;
import br.com.fiap.tech_challenge_ii.restaurant.core.usecase.*;
import br.com.fiap.tech_challenge_ii.restaurant.core.usecase.impl.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UseCaseConfig {
    @Bean
    public CreateRestaurantUseCase createRestaurant(RestaurantGateway gateway, UserGateway userGateway) {
        return new CreateRestaurantUseCaseImpl(gateway, userGateway);
    }

    @Bean
    public GetRestaurantById getRestaurantById(RestaurantGateway gateway) {
        return new GetRestaurantByIdImpl(gateway);
    }

    @Bean
    public ListRestaurants listRestaurants(RestaurantGateway gateway) {
        return new ListRestaurantsImpl(gateway);
    }

    @Bean
    public DeleteRestaurantUseCase deleteRestaurantById(RestaurantGateway gateway) {
        return new DeleteRestaurantUseCaseImpl(gateway);
    }

    @Bean
    public UpdateRestaurant updateRestaurant(RestaurantGateway gateway) {
        return new UpdateRestaurantImpl(gateway);
    }

}
