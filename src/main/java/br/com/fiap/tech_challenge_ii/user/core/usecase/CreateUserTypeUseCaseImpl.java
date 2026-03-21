package br.com.fiap.tech_challenge_ii.user.core.usecase;

import java.util.List;
import java.util.Optional;

import br.com.fiap.tech_challenge_ii.user.core.domain.Owner;
import br.com.fiap.tech_challenge_ii.user.core.domain.User;
import br.com.fiap.tech_challenge_ii.user.core.exception.UserNotFoundException;
import br.com.fiap.tech_challenge_ii.user.core.gateway.RestaurantGateway;
import br.com.fiap.tech_challenge_ii.user.core.gateway.UserGateway;
import br.com.fiap.tech_challenge_ii.user.core.gateway.UserTypeGateway;
import br.com.fiap.tech_challenge_ii.user.core.rule.RuleBase;
import br.com.fiap.tech_challenge_ii.user.core.rule.ValidateUserOwner;
import br.com.fiap.tech_challenge_ii.user.core.rule.dto.RuleContextDto;
import org.springframework.stereotype.Service;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class CreateUserTypeUseCaseImpl implements CreateUserTypeUseCase {


	
	private final RestaurantGateway restaurantGateway;
	private final UserGateway userGateway;
	private final UserTypeGateway userTypeGateway;
	private final List<RuleBase> rules;
	
	@Override
	public Long create(Long loggedInUserId, Long idRestaurant, String nameType, Class<? extends User> newTypeUser) {
		
		var loggedInUserOp = userGateway.findById(loggedInUserId);
		var loggedInUser = validateAndGetLoggedInUser(loggedInUserId, loggedInUserOp);
		
		var ruleContextDto = new RuleContextDto(idRestaurant, loggedInUser);

		validateCurrentUserIsOwner(ruleContextDto);
		
		getRestaurants(loggedInUser);

		rules.forEach(r -> r.validate(ruleContextDto));
		
		return userTypeGateway.save(idRestaurant, nameType, newTypeUser);
	}

	private void validateCurrentUserIsOwner(RuleContextDto ruleContextDto) {
		rules.stream().filter(ValidateUserOwner.class::isInstance).forEach(r -> r.validate(ruleContextDto));
	}

	private void getRestaurants(User usuarioLogado) {
		if(usuarioLogado instanceof Owner owner) {
			var restaurants = restaurantGateway.getByUserId(usuarioLogado.getId());
			owner.addRestaurants(restaurants);
		}
	}

	private User validateAndGetLoggedInUser(Long loggedInUserId, Optional<User> loggedInUserOp) {
		if(loggedInUserOp.isEmpty()){
			log.warn("Usuario não encontrado com id informado. loggedInUserOp={}", loggedInUserId);
			throw new UserNotFoundException();
		}
		
		return loggedInUserOp.get();
	}
}
