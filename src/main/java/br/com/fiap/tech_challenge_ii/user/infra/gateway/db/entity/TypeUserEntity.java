package br.com.fiap.tech_challenge_ii.user.infra.gateway.db.entity;

import br.com.fiap.tech_challenge_ii.user.core.domain.User;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.Getter;


@Entity
@Data
public class TypeUserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nameType;
    private Integer category;
}
