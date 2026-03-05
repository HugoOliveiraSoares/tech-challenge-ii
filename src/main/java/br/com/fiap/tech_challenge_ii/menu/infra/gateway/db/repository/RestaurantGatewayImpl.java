package br.com.fiap.tech_challenge_ii.menu.infra.gateway.db.repository;

import java.util.Optional;

import org.springframework.stereotype.Component;

import br.com.fiap.tech_challenge_ii.menu.core.domain.valueObjects.Restaurant;
import br.com.fiap.tech_challenge_ii.menu.core.gateway.RestaurantGateway;
import br.com.fiap.tech_challenge_ii.menu.infra.gateway.db.mapper.RestaurantMapper;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class RestaurantGatewayImpl implements RestaurantGateway {

    private final RestaurantRepository restaurantRepository;
    private final RestaurantMapper restaurantMapper;

    @Override
    public Optional<Restaurant> findRestaurantById(Long restaurantId) {
        return restaurantRepository.findById(restaurantId)
                .map(restaurantMapper::toDomain);
    }

}
