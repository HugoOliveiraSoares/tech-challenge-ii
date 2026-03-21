package br.com.fiap.tech_challenge_ii.user.core.usecase;


import br.com.fiap.tech_challenge_ii.user.core.domain.User;

public interface CreateUserTypeUseCase {

	Long create(Long idUsuarioLogado, Long idRestaurante, String nomeTipo, Class<? extends User> novoTipoUsuario);
	
}
