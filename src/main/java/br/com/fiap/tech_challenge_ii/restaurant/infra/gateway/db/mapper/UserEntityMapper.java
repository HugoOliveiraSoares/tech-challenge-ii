package br.com.fiap.tech_challenge_ii.restaurant.infra.gateway.db.mapper;

import br.com.fiap.tech_challenge_ii.restaurant.core.domain.valueObjects.UserOwner;
import br.com.fiap.tech_challenge_ii.restaurant.infra.gateway.db.entity.UserEntity;
import org.springframework.stereotype.Component;

@Component
public class UserEntityMapper {

    public UserOwner toDomain( UserEntity entity){
        return new UserOwner(entity.getId(),
                entity.getUserType());
    }
}

