package com.uphf.episode.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@Builder
public class EpisodeDTO implements Serializable {
    private Integer idEpisode;
    private String titre;
    private Integer idAnime;
    private int numEp;
    private String saison;
    private String lien;
}
