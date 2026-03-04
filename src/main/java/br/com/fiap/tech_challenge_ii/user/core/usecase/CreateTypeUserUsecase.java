package br.com.fiap.tech_challenge_ii.user.core.usecase;

import br.com.fiap.tech_challenge_ii.user.core.domain.User;

public interface CreateTypeUserUsecase {

    public Long create(Long idIsLoggedIn, Long idRestaurant, User newTypeUser);

}
