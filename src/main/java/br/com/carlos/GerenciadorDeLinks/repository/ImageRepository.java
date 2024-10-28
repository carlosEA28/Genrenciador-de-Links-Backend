package br.com.carlos.GerenciadorDeLinks.repository;

import br.com.carlos.GerenciadorDeLinks.entity.ImageEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ImageRepository extends JpaRepository<ImageEntity, UUID> {
}
