package br.com.fiap.tech_challenge_ii.user.infra.controller.json;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UserTypeJson {
	private Long id;
	private String name;
	private UserType type;
}
