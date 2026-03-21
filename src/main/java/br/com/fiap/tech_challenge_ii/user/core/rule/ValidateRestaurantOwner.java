package br.com.fiap.tech_challenge_ii.user.core.rule;

import br.com.fiap.tech_challenge_ii.user.core.domain.Owner;
import br.com.fiap.tech_challenge_ii.user.core.exception.ActionNotAllowedException;
import br.com.fiap.tech_challenge_ii.user.core.rule.dto.RuleContextDto;
import org.springframework.stereotype.Component;


import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class ValidateRestaurantOwner implements RuleBase {

	@Override
	public void validate(RuleContextDto inputDto) {
		var userOwner = (Owner) inputDto.getLoggedInUser();
		if(!userOwner.isOwner(inputDto.getIdRestaurant())) {
			log.warn("Acao nao permitida");
			throw new ActionNotAllowedException();
		}
	}

}
