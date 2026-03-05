package br.com.fiap.tech_challenge_ii.restaurant.infra.gateway.db.repository;

import br.com.fiap.tech_challenge_ii.restaurant.infra.gateway.db.entity.RestaurantEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RestaurantJPARepository extends JpaRepository<RestaurantEntity, Long> {

}
