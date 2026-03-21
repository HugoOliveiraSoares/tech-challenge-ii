package br.com.fiap.tech_challenge_ii.user.infra.controller;

import br.com.fiap.tech_challenge_ii.user.core.domain.Client;
import br.com.fiap.tech_challenge_ii.user.core.domain.Owner;
import br.com.fiap.tech_challenge_ii.user.core.domain.User;
import br.com.fiap.tech_challenge_ii.user.core.usecase.CreateUserTypeUseCase;
import br.com.fiap.tech_challenge_ii.user.infra.controller.json.UserType;
import br.com.fiap.tech_challenge_ii.user.infra.controller.json.UserTypeJson;
import br.com.fiap.tech_challenge_ii.user.infra.exception.UserTypeNotFoundException;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;



import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequiredArgsConstructor
public class UserTypeController {

	private final CreateUserTypeUseCase createUserTypeUseCase;
	
	@PostMapping("/restaurantes/{idRestaurante}/tipo-usuarios")
	public Long criar(@RequestHeader("x-user-id") Long loggedInUserId, @PathVariable("idRestaurante") Long idRestaurant, @RequestBody UserTypeJson userTypeJson) {
		
		var nameType = userTypeJson.getName();
		
		var userType = map(userTypeJson);
		
		return createUserTypeUseCase.create(loggedInUserId, idRestaurant, nameType, userType);
	}

	private Class<? extends User> map(UserTypeJson userTypeJson) {

		if(UserType.CLIENT.equals(userTypeJson.getType())) {
			return Client.class;
		}
		
		if(UserType.OWNER.equals(userTypeJson.getType())) {
			return Owner.class;
		}
		
		log.warn("Tipo de usuário não existe. tipo={}", userTypeJson.getName());
		throw new UserTypeNotFoundException();
	}
	
}
