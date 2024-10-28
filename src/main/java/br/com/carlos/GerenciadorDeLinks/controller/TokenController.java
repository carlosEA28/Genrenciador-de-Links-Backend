package br.com.carlos.GerenciadorDeLinks.controller;

import br.com.carlos.GerenciadorDeLinks.dto.LoginDTO;
import br.com.carlos.GerenciadorDeLinks.dto.LoginResponseDTO;
import br.com.carlos.GerenciadorDeLinks.repository.UserRespoitory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;

@RestController
@RequestMapping("/login")
public class TokenController {
    @Autowired
    private JwtEncoder jwtEncoder;

    @Autowired
    private UserRespoitory userRespoitory;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @PostMapping
    public ResponseEntity<LoginResponseDTO> login(@RequestBody LoginDTO loginDTO) {
        var user = userRespoitory.findByEmail(loginDTO.email());

        if (user.isEmpty() || !user.get().isLoginCorrect(loginDTO, bCryptPasswordEncoder)) {
            throw new BadCredentialsException("Email/Password is invalid");
        }

        var now = Instant.now();
        var expiresIn = 300L;
        var claims = JwtClaimsSet.builder()
                .issuer("user")
                .subject(user.get().getUserId().toString())
                .expiresAt(now.plusSeconds(expiresIn))
                .issuedAt(now)
                .build();

        var jwtValue = jwtEncoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();

        return ResponseEntity.ok(new LoginResponseDTO(jwtValue, expiresIn));
    }
}
