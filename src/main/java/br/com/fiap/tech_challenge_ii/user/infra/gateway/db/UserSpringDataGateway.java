package br.com.fiap.tech_challenge_ii.user.infra.gateway.db;

import java.util.Optional;

import br.com.fiap.tech_challenge_ii.user.core.domain.Client;
import br.com.fiap.tech_challenge_ii.user.core.domain.Owner;
import br.com.fiap.tech_challenge_ii.user.core.domain.User;
import br.com.fiap.tech_challenge_ii.user.core.gateway.UserGateway;
import br.com.fiap.tech_challenge_ii.user.infra.gateway.db.repository.UserEntityRepository;
import org.springframework.stereotype.Component;


import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class UserSpringDataGateway implements UserGateway {
	
	private final UserEntityRepository userEntityRepository;
	
	@Override
	public Optional<User> findById(Long loggedInUserId) {
		
		var userEntityOp = userEntityRepository.findById(loggedInUserId);
		
		if(userEntityOp.isPresent()) {
			var userEntity = userEntityOp.get();
			var nameType = userEntity.getType().getNameType();
			
			if(userEntity.isOwner() ) {
				return Optional.of(new Owner(loggedInUserId, nameType, null));
			}
			
			if(!userEntity.isOwner() ) {
				return Optional.of(new Client(loggedInUserId, nameType));
			}
		}
		
		return Optional.empty();
	}


}
