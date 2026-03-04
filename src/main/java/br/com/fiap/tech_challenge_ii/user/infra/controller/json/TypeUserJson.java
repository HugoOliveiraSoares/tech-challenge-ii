package br.com.fiap.tech_challenge_ii.user.infra.controller.json;

import br.com.fiap.tech_challenge_ii.user.core.domain.UserType;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class TypeUserJson {
    private Long id;
    private String name;
    private UserType type;

}
