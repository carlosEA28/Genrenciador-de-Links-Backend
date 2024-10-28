package br.com.carlos.GerenciadorDeLinks.entity;

import br.com.carlos.GerenciadorDeLinks.dto.LoginDTO;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.List;
import java.util.UUID;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "users")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID userId;

    @NotBlank(message = "The field [name] must not be empty")
    private String firstName;

    @NotBlank(message = "The field [name] must not be empty")
    private String email;

    @NotBlank(message = "The field [secondName] must not be empty")
    private String secondName;

    @NotBlank(message = "The field [password] must not be empty")
    private String password;

    @OneToMany(mappedBy = "usuario")
    @JsonManagedReference
    private List<LinkEntity> links;

    public boolean isLoginCorrect(LoginDTO loginDTO, BCryptPasswordEncoder bCryptPasswordEncoder) {
        return bCryptPasswordEncoder.matches(loginDTO.password(), this.password);
    }
}
