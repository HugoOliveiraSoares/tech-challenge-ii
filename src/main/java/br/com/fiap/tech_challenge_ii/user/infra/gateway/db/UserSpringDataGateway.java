package br.com.fiap.tech_challenge_ii.user.infra.gateway.db;

import java.util.Optional;

import org.springframework.stereotype.Component;

import br.com.fiap.tech_challenge_ii.user.core.domain.Client;
import br.com.fiap.tech_challenge_ii.user.core.domain.Owner;
import br.com.fiap.tech_challenge_ii.user.core.domain.User;
import br.com.fiap.tech_challenge_ii.user.core.gateway.UserGateway;
import br.com.fiap.tech_challenge_ii.user.infra.gateway.db.repository.UserEntityRepository;
import lombok.RequiredArgsConstructor;

@Component("userEntitySpringDataGateway")
@RequiredArgsConstructor
public class UserSpringDataGateway implements UserGateway {

    private final UserEntityRepository userEntityRepository;

    @Override
    public Optional<User> findById(Long loggedInUserId) {

        var userEntityOp = userEntityRepository.findById(loggedInUserId);

        if (userEntityOp.isPresent()) {
            var userEntity = userEntityOp.get();
            var name = userEntity.getName();
            var nameType = userEntity.getType();

            if (userEntity.isOwner()) {
                return Optional.of(new Owner(loggedInUserId, name, nameType, null));
            } else {
                return Optional.of(new Client(loggedInUserId, name, nameType));
            }
        }

        return Optional.empty();
    }

    @Override
    public void update(User updatedUser) {
        var userEntity = userEntityRepository.findById(updatedUser.getId())
                .orElseThrow(() -> new RuntimeException("User not found")); // TODO: Se quiser pode criar uma exception
                                                                            // propria
        userEntity.setName(updatedUser.getName());
        userEntity.setType(updatedUser.getUserType());
        userEntityRepository.save(userEntity);
    }

}
