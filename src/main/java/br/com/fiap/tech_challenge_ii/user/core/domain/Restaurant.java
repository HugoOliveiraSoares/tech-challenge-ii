package br.com.fiap.tech_challenge_ii.user.core.domain;

import br.com.fiap.tech_challenge_ii.restaurant.core.domain.Menu;
import br.com.fiap.tech_challenge_ii.user.core.domain.valueObjects.Address;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Restaurant {

    private Long id;
    private String name;
    private Address address;

}
