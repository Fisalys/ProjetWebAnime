package fr.uphf.personnages.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@Builder
public class PersonnageDTO implements Serializable {
    private Integer idPersonnage;
    private String nom;
    private String prenom;
    private String genre;
    private Integer idAnime;
    private String voice;
}
