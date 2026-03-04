package br.com.fiap.tech_challenge_ii.user.infra.gateway.db.repository;

import br.com.fiap.tech_challenge_ii.user.infra.gateway.db.entity.TypeUserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TypeUserEntityRepository extends JpaRepository<TypeUserEntity, Long> {
}
