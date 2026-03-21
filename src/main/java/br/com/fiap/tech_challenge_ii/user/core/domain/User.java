package br.com.fiap.tech_challenge_ii.user.core.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public abstract class User {
	protected Long id;
	protected String nameType;

}
