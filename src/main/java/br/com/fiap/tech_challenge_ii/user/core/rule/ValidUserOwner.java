package br.com.fiap.tech_challenge_ii.user.core.rule;

import br.com.fiap.tech_challenge_ii.user.core.domain.Owner;
import br.com.fiap.tech_challenge_ii.user.core.exception.ActionNotPermittedException;
import br.com.fiap.tech_challenge_ii.user.core.rule.dto.RuleContextDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class ValidUserOwner implements RuleValid{

    @Override
    public void validate(RuleContextDto contextDto) {
        if(contextDto.getIsLoggedIn() instanceof Owner) {
            log.warn("Acao nao permitida");
            throw new ActionNotPermittedException();
        }
    }
}
