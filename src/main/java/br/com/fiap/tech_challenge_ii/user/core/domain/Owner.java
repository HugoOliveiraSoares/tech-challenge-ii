package br.com.fiap.tech_challenge_ii.user.core.domain;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder
public class Owner extends User {

    private List<Restaurant> restaurants;

    public Owner(Long id, String name, String nameType, List<Restaurant> restaurants) {
        super(id, name, nameType);
        this.restaurants = restaurants;
    }

    public Owner(Long id, String name, String nameType) {
        super(id, name, nameType);
        this.restaurants = new ArrayList<>();
    }

}
