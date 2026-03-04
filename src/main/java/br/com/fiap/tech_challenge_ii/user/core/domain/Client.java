package br.com.fiap.tech_challenge_ii.user.core.domain;

import java.time.LocalDateTime;
import java.util.List;


import br.com.fiap.tech_challenge_ii.user.core.domain.valueObjects.Address;

import jakarta.persistence.criteria.Order;
import lombok.Getter;



@Getter
public class Client extends User {

    private List<Order> orders;

    public Client(Long id, String name, String email, String login, String password, LocalDateTime lastModifiedDate,
            Address address) {
        super(id, name, email, login, password, lastModifiedDate, address);
    }

}
