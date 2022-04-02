package fr.uphf.anime.models;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Anime {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idAnime;
    private String nom;
    private String genre;
    private String description;
    private int nbEp;
    @ElementCollection
    private List<Integer> listEp;
    @ElementCollection
    private List<Integer> listPers;
}
