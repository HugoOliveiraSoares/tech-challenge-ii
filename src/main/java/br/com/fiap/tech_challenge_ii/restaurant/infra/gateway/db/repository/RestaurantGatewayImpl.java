package br.com.fiap.tech_challenge_ii.restaurant.infra.gateway.db.repository;

import br.com.fiap.tech_challenge_ii.restaurant.core.domain.Restaurant;
import br.com.fiap.tech_challenge_ii.restaurant.core.gateway.RestaurantGateway;
import br.com.fiap.tech_challenge_ii.restaurant.core.gateway.filter.RestaurantSearchFilter;
import br.com.fiap.tech_challenge_ii.restaurant.infra.gateway.db.mapper.RestaurantMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class RestaurantGatewayImpl implements RestaurantGateway {

    private final RestaurantJPARepository restaurantRepository;
    private final RestaurantMapper restaurantMapper;

    @Override
    public Restaurant create(Restaurant restaurant) {
        var restaurantEntity = restaurantRepository.save(restaurantMapper.toEntity(restaurant));

        return restaurantMapper.toDomain(restaurantEntity);
    }

    @Override
    public List<Restaurant> findAll(RestaurantSearchFilter filter) {
        return List.of();
    }

    @Override
    public Optional<Restaurant> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public Restaurant update(Restaurant restaurant) {
        return null;
    }

    @Override
    public void deleteById(Long id) {

    }
}
