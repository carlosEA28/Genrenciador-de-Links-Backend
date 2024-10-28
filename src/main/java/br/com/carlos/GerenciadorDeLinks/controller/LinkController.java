package br.com.carlos.GerenciadorDeLinks.controller;

import br.com.carlos.GerenciadorDeLinks.dto.LinkDTO;
import br.com.carlos.GerenciadorDeLinks.entity.LinkEntity;
import br.com.carlos.GerenciadorDeLinks.service.ImageService;
import br.com.carlos.GerenciadorDeLinks.service.LinkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class LinkController {

    @Autowired
    private LinkService linkService;

    @PostMapping("/link")
    public ResponseEntity<LinkEntity> createLink(@RequestBody LinkDTO linkDTO) {
        var link = linkService.createLink(linkDTO);
        return ResponseEntity.ok().body(link);
    }

    @DeleteMapping("/link/{linkId}")
    public ResponseEntity<Void> deleteLink(@PathVariable("linkId") String linkId){
        linkService.deleteLink(linkId);

        return ResponseEntity.noContent().build();
    }
}
