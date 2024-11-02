package br.com.carlos.GerenciadorDeLinks.controller;

import br.com.carlos.GerenciadorDeLinks.dto.UpdateUserDTO;
import br.com.carlos.GerenciadorDeLinks.dto.UserDTO;
import br.com.carlos.GerenciadorDeLinks.entity.LinkEntity;
import br.com.carlos.GerenciadorDeLinks.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/user")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<Object> create(@Valid @RequestBody UserDTO userDTO) {
        var newUser = userService.createUser(userDTO);
        return ResponseEntity.ok().body(newUser);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<Object> getUserById(@PathVariable("userId") String userId) {
        var user = userService.getUserById(userId);

        return ResponseEntity.ok().body(user);
    }

    @GetMapping("/links/{userId}")
    public ResponseEntity<List<LinkEntity>> getAllLinks(@Valid @PathVariable("userId") String userId) {
        var links = userService.getAllLinks(userId);

        return ResponseEntity.ok().body(links);
    }

    @PutMapping("/{userId}")
    public ResponseEntity<Void> updateUser(@PathVariable("userId") String userId, @RequestBody UpdateUserDTO updateUserDTO) {
        userService.updateUser(userId, updateUserDTO);
        return ResponseEntity.noContent().build();
    }
}
