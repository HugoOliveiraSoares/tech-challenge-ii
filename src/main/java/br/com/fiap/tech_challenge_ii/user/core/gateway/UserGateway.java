package br.com.fiap.tech_challenge_ii.user.core.gateway;

import br.com.fiap.tech_challenge_ii.user.core.domain.User;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.Optional;


public interface UserGateway {
    Optional<User> findById(Long idIsLoggedIn);
}
