package br.com.fiap.tech_challenge_ii.menu.infra.gateway.db.repository;

import java.util.Optional;

import org.springframework.stereotype.Component;

import br.com.fiap.tech_challenge_ii.menu.core.domain.valueObjects.Restaurant;
import br.com.fiap.tech_challenge_ii.menu.core.gateway.RestaurantGateway;

@Component
public class RestaurantGatewayImpl implements RestaurantGateway {

    @Override
    public Optional<Restaurant> findRestaurantById(Long restaurantId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findRestaurantById'");
    }

}
