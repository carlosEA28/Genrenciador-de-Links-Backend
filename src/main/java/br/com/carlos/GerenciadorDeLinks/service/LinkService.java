package br.com.carlos.GerenciadorDeLinks.service;

import br.com.carlos.GerenciadorDeLinks.dto.LinkDTO;
import br.com.carlos.GerenciadorDeLinks.dto.UserDTO;
import br.com.carlos.GerenciadorDeLinks.entity.ImageEntity;
import br.com.carlos.GerenciadorDeLinks.entity.LinkEntity;
import br.com.carlos.GerenciadorDeLinks.entity.UserEntity;
import br.com.carlos.GerenciadorDeLinks.exceptions.LinkNotFound;
import br.com.carlos.GerenciadorDeLinks.exceptions.UserNotFound;
import br.com.carlos.GerenciadorDeLinks.repository.ImageRepository;
import br.com.carlos.GerenciadorDeLinks.repository.LinkRepository;
import br.com.carlos.GerenciadorDeLinks.repository.UserRespoitory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class LinkService {
    @Autowired
    private ImageService imageService;

    @Autowired
    private UserRespoitory userRespoitory;

    @Autowired
    private ImageRepository imageRepository;

    @Autowired
    private LinkRepository linkRepository;

    public LinkEntity createLink(LinkDTO linkDTO) {
        // Usar o userId do linkDTO
        var userExists = userRespoitory.findById(UUID.fromString(linkDTO.userId()));

        if (userExists.isEmpty()) {
            throw new UserNotFound();
        }

        var link = new LinkEntity();
        var image = new ImageEntity();

        link.setTitle(linkDTO.title());
        link.setStreaming(linkDTO.streaming());
        link.setUrl(linkDTO.url());
        link.setImgLink(image);
        link.setUsuario(userExists.get());
        image.setUrl(linkDTO.imgUrl());

        imageRepository.save(image);
        linkRepository.save(link);

        System.out.println("Received link DTO: " + linkDTO);


        return link;
    }

    public void deleteLink(String linkId) {
        var id = UUID.fromString(linkId);

        var userExists = linkRepository.existsById(id);

        if (userExists) {
            linkRepository.deleteById(id);
        }
    }
}


