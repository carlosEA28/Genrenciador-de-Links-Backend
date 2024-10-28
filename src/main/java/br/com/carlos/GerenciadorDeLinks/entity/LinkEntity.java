package br.com.carlos.GerenciadorDeLinks.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "links")
public class LinkEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID linkId;

    private String title;

    private String streaming;

    private String url;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    @JsonBackReference
    private UserEntity usuario;



    @OneToOne
    @JoinColumn(name = "imagem_id", referencedColumnName = "id")
    private ImageEntity imgLink;
}
