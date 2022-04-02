package fr.uphf.anime.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.ElementCollection;
import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@Builder
public class AnimeDTO implements Serializable {
    private Integer idAnime;
    private String nom;
    private String genre;
    private String description;
    private int nbEp;
    private List<Integer> listEp;
    private List<Integer> listPers;
}
