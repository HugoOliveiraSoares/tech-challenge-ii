package br.com.fiap.tech_challenge_ii.user.core.gateway;

import br.com.fiap.tech_challenge_ii.user.core.domain.User;

import java.util.Optional;

public interface UserGateway {

    Optional<User> findById(Long loggedInUserId);

    void update(User user);

}
