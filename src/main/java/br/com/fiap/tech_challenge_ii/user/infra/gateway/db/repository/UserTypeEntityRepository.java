package br.com.fiap.tech_challenge_ii.user.infra.gateway.db.repository;

import br.com.fiap.tech_challenge_ii.user.infra.gateway.db.entity.UserTypeEntity;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserTypeEntityRepository extends JpaRepository<UserTypeEntity, Long> {

}
