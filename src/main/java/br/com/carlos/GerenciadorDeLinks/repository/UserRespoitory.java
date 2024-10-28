package br.com.carlos.GerenciadorDeLinks.repository;

import br.com.carlos.GerenciadorDeLinks.entity.UserEntity;
import org.apache.catalina.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRespoitory extends JpaRepository< UserEntity,UUID> {
    Optional<UserEntity> findByEmail(String email);
}
