package br.com.fiap.tech_challenge_ii.user.infra.gateway.db.repository;

import br.com.fiap.tech_challenge_ii.user.infra.gateway.db.entity.UserTypeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface UserTypeEntityRepository extends JpaRepository<UserTypeEntity, Long> {

    Optional<UserTypeEntity> findByNameType(String nameType);

}
