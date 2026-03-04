package br.com.fiap.tech_challenge_ii.user.core.rule.dto;

import br.com.fiap.tech_challenge_ii.user.core.domain.User;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RuleContextDto {
    private Long id;
    private User isLoggedIn;
}
