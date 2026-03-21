package br.com.fiap.tech_challenge_ii.user.infra.gateway.db;

import br.com.fiap.tech_challenge_ii.user.core.domain.Owner;
import br.com.fiap.tech_challenge_ii.user.core.domain.User;
import br.com.fiap.tech_challenge_ii.user.core.gateway.UserTypeGateway;
import br.com.fiap.tech_challenge_ii.user.infra.gateway.db.entity.UserTypeEntity;
import br.com.fiap.tech_challenge_ii.user.infra.gateway.db.repository.UserTypeEntityRepository;
import org.springframework.stereotype.Component;


import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class UserTypeSpringDataGateway implements UserTypeGateway {

	private final UserTypeEntityRepository userTypeEntityRepository;
	
	@Override
	public Long save(Long idRestaurant, String nameType, Class<? extends User> newTypeUser) {
		var categoria = newTypeUser.equals(Owner.class) ? 0 : 1;
		var tipoUsuarioEntity = new UserTypeEntity(null, nameType, categoria);
		
		return userTypeEntityRepository.save(tipoUsuarioEntity).getId();
	}
}
