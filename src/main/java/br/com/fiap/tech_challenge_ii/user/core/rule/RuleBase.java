package br.com.fiap.tech_challenge_ii.user.core.rule;


import br.com.fiap.tech_challenge_ii.user.core.rule.dto.RuleContextDto;

public interface RuleBase {

	void validate(RuleContextDto inputDto);

	
}
