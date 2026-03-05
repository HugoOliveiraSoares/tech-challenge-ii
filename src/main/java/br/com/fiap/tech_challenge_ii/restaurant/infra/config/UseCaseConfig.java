package br.com.fiap.tech_challenge_ii.restaurant.infra.config;

import br.com.fiap.tech_challenge_ii.restaurant.core.gateway.RestaurantGateway;
import br.com.fiap.tech_challenge_ii.restaurant.core.usecase.CreateRestaurant;
import br.com.fiap.tech_challenge_ii.restaurant.core.usecase.impl.CreateRestaurantImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UseCaseConfig {
    @Bean
    public CreateRestaurant createRestaurant(RestaurantGateway gateway) {
        return new CreateRestaurantImpl(gateway);
    }
}
