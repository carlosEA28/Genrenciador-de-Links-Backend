package br.com.carlos.GerenciadorDeLinks.dto;

import org.springframework.web.multipart.MultipartFile;

public record ImageDTO(String name, MultipartFile file) {
}
