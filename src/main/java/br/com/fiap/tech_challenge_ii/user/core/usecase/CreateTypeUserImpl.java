package br.com.fiap.tech_challenge_ii.user.core.usecase;

import br.com.fiap.tech_challenge_ii.user.core.gateway.RestaurantGateway;
import br.com.fiap.tech_challenge_ii.user.core.domain.Owner;
import br.com.fiap.tech_challenge_ii.user.core.domain.User;
import br.com.fiap.tech_challenge_ii.user.core.exception.UserNotFoundException;
import br.com.fiap.tech_challenge_ii.user.core.gateway.UserGateway;
import br.com.fiap.tech_challenge_ii.user.core.gateway.UserTypeGateway;
import br.com.fiap.tech_challenge_ii.user.core.rule.RuleValid;
import br.com.fiap.tech_challenge_ii.user.core.rule.ValidUserOwner;
import br.com.fiap.tech_challenge_ii.user.core.rule.dto.RuleContextDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor

public class CreateTypeUserImpl implements CreateTypeUserUsecase{


    private final UserGateway userGateway;
    private final UserTypeGateway userTypeGateway;
    private final RestaurantGateway restaurantGateway;
    private final List<RuleValid> rules;


    @Override
    public Long create(Long idIsLoggedIn, Long idRestaurant, User newTypeUser) {

        var userLoggedOp = userGateway.findById(idIsLoggedIn);
        var userLogged = validateLoggedInUser(idIsLoggedIn, userLoggedOp);

        var ruleContextDto = new RuleContextDto(idRestaurant, userLogged);
        validateIfLoggedUserIsOwner(ruleContextDto);

        getRestaurants(idIsLoggedIn, newTypeUser);
        rules.forEach(r -> r.validate(ruleContextDto));

        return userTypeGateway.save(idRestaurant, newTypeUser);
    }

    private void validateIfLoggedUserIsOwner(RuleContextDto ruleContextDto) {
        rules.stream().filter(ValidUserOwner.class::isInstance).forEach(r -> r.validate(ruleContextDto));
    }

    private void getRestaurants(Long idIsLoggedIn, User newTypeUser) {
        if(newTypeUser instanceof Owner owner) {
            var restaurants = restaurantGateway.findByUserId(idIsLoggedIn);
            owner.addRestaurants(restaurants);
        }
    }

    private User validateLoggedInUser(Long idIsLoggedIn, Optional<User> userLoggedOp) {
        if(userLoggedOp.isEmpty()){
            log.warn("Usuario não encontrado com id informado. idUsuarioLogado={}", idIsLoggedIn);
            throw new UserNotFoundException();
        }

        return userLoggedOp.get();
    }
}
