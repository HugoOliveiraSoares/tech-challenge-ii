package br.com.fiap.tech_challenge_ii.user.core.gateway;

import br.com.fiap.tech_challenge_ii.user.core.domain.User;

public interface UserTypeGateway {

    Long save(Long id, User newTypeUser);
}
