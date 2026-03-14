package br.com.fiap.tech_challenge_ii.restaurant.core.gateway;

import br.com.fiap.tech_challenge_ii.restaurant.core.domain.valueObjects.UserOwner;

import java.util.Optional;

public interface UserGateway {
    Optional<UserOwner> getUserById(Long id);
}
