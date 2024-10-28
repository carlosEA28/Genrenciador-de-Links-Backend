package br.com.carlos.GerenciadorDeLinks.repository;

import br.com.carlos.GerenciadorDeLinks.entity.LinkEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface LinkRepository extends JpaRepository<LinkEntity, UUID> {
}
