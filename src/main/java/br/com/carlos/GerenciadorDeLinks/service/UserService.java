package br.com.carlos.GerenciadorDeLinks.service;

import br.com.carlos.GerenciadorDeLinks.dto.UpdateUserDTO;
import br.com.carlos.GerenciadorDeLinks.dto.UserDTO;
import br.com.carlos.GerenciadorDeLinks.entity.LinkEntity;
import br.com.carlos.GerenciadorDeLinks.entity.UserEntity;
import br.com.carlos.GerenciadorDeLinks.exceptions.UserAlreadyExists;
import br.com.carlos.GerenciadorDeLinks.exceptions.UserNotFound;
import br.com.carlos.GerenciadorDeLinks.repository.UserRespoitory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class UserService {
    @Autowired
    private UserRespoitory userRespoitory;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public UserEntity createUser(UserDTO userDTO) {
        userRespoitory.findByEmail(userDTO.email()).ifPresent(user -> {
            throw new UserAlreadyExists();
        });

        var user = new UserEntity();

        user.setFirstName(userDTO.firstName());
        user.setSecondName(userDTO.secondName());
        user.setEmail(userDTO.email());
        user.setPassword(bCryptPasswordEncoder.encode(userDTO.password()));

        return userRespoitory.save(user);
    }

    public List<LinkEntity> getAllLinks(String userId) {
      var userExists = userRespoitory.findById(UUID.fromString(userId));
      return userExists.get().getLinks();
    }

    public void updateUser(String userId, UpdateUserDTO dto) {

        //TEMOS QUE CONVERTER O ID DE UUID PARA STRING
        var id = UUID.fromString(userId);

        var userEntity = userRespoitory.findById(id);

        if (userEntity.isPresent()) {
            var user = userEntity.get();

            if (dto.email() != null) {
                user.setEmail(dto.email());
            }

            if (dto.password() != null) {
                user.setPassword(dto.password());
            }

            userRespoitory.save(user);
        }
    }
}
