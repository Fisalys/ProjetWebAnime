package com.uphf.episode.services;

import com.uphf.episode.dto.EpisodeDTO;
import com.uphf.episode.exeptions.NotValidExeption;
import com.uphf.episode.exeptions.ProcessExeption;
import com.uphf.episode.models.Episode;
import com.uphf.episode.repositories.EpisodeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EpisodeService extends CommonService {

    private static final String EPISODE_NOT_FOUND = "Episode non trouvé avec l'id : %s";

    @Autowired
    private EpisodeRepository episodeRepository;

    public void validateEpisodeModel(EpisodeDTO episodeToCreate) throws NotValidExeption
    {
        NotValidExeption e = new NotValidExeption();

        if(episodeToCreate == null)
            e.getMessages().add("EpisodeModel : null");
        else{
            if(episodeToCreate.getTitre() == null || episodeToCreate.getTitre().isBlank())
                e.getMessages().add("Titre est vide");
        }

        if(!e.getMessages().isEmpty())
            throw e;
    }

    public EpisodeDTO saveEpisode(EpisodeDTO episodeToCreate) throws ProcessExeption
    {
        Episode e = Episode.builder()
                .idEpisode(episodeToCreate.getIdEpisode())
                .titre(episodeToCreate.getTitre())
                .idAnime(episodeToCreate.getIdAnime())
                .numEp(episodeToCreate.getNumEp())
                .saison(episodeToCreate.getSaison())
                .lien(episodeToCreate.getLien())
                .build();

        episodeRepository.save(e);
        return episodeToCreate;
    }
}
