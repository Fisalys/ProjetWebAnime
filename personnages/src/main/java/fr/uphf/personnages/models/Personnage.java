package fr.uphf.personnages.models;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Personnage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idPersonnage;
    private String nom;
    private String prenom;
    private String genre;
    private Integer idAnime;
    private String voice;
}
