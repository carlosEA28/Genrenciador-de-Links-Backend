package br.com.carlos.GerenciadorDeLinks.service;

import br.com.carlos.GerenciadorDeLinks.dto.ImageDTO;
import br.com.carlos.GerenciadorDeLinks.entity.ImageEntity;
import br.com.carlos.GerenciadorDeLinks.repository.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.awt.*;
import java.util.Map;

@Service
public class ImageService {

    @Autowired
    private CloudinaryService cloudinaryService;
    @Autowired
    private ImageRepository imageRepository;


    public ResponseEntity<Map> uploadImage(ImageDTO imageModel) {
        try {
            if (imageModel.name().isEmpty()) {
                return ResponseEntity.badRequest().build();
            }
            if (imageModel.file().isEmpty()) {
                return ResponseEntity.badRequest().build();
            }
            var image = new ImageEntity();
            image.setName(imageModel.name());
            image.setUrl(cloudinaryService.uploadFile(imageModel.file(), "folder_1"));
            if (image.getUrl() == null) {
                return ResponseEntity.badRequest().build();
            }
            imageRepository.save(image);
            return ResponseEntity.ok().body(Map.of("url", image.getUrl()));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }


    }
}
