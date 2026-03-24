package br.com.fiap.tech_challenge_ii.user.core.domain;

import lombok.Getter;

@Getter
public abstract class User {
    protected Long id;
    protected String name;
    protected String userType;

    protected User(Long id, String name, String nameType) {
        this.id = id;
        this.userType = nameType;
    }

    public void update(String name, String userType) {
        this.name = name;
        this.userType = userType;
    }

}
