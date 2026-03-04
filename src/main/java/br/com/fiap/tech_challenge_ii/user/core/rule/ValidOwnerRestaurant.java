package br.com.fiap.tech_challenge_ii.user.core.rule;

import br.com.fiap.tech_challenge_ii.user.core.domain.Owner;
import br.com.fiap.tech_challenge_ii.user.core.exception.ActionNotPermittedException;
import br.com.fiap.tech_challenge_ii.user.core.rule.dto.RuleContextDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class ValidOwnerRestaurant implements RuleValid{

    @Override
    public void validate(RuleContextDto contextDto) {
        var userOwner = (Owner) contextDto.getIsLoggedIn();
        if(!userOwner.isRestaurantOwner(contextDto.getId())) {
            log.warn("Acao nao permitida");
            throw new ActionNotPermittedException();
        }

    }
}
