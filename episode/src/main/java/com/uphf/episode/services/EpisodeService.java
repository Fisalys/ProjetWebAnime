package com.uphf.episode.services;

import com.uphf.episode.dto.AnimeDTO;
import com.uphf.episode.dto.EpisodeDTO;
import com.uphf.episode.exeptions.NotValidExeption;
import com.uphf.episode.exeptions.ProcessExeption;
import com.uphf.episode.models.Episode;
import com.uphf.episode.repositories.EpisodeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class EpisodeService extends CommonService {

    private static final String EPISODE_NOT_FOUND = "Episode non trouvé avec l'id : %s";

    @Autowired
    private EpisodeRepository episodeRepository;

    @Autowired
    private RestTemplate restTemplate;

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
                .titre(episodeToCreate.getTitre())
                .idAnime(episodeToCreate.getIdAnime())
                .numEp(episodeToCreate.getNumEp())
                .saison(episodeToCreate.getSaison())
                .lien(episodeToCreate.getLien())
                .build();
        Episode ep = episodeRepository.save(e);

        restTemplate.put("http://anime-service/animeservice/admin/addEpisode?idAnime=" + episodeToCreate.getIdAnime() + "&idEpisode=" + ep.getIdEpisode(), null);

        return episodeToCreate;
    }
    public EpisodeDTO getEpisodeById(Integer idEpisode) throws ProcessExeption {
        Episode e = episodeRepository.findById(idEpisode).orElseThrow(()->new ProcessExeption(String.format(EPISODE_NOT_FOUND, idEpisode)));

        return EpisodeDTO.builder()
                .idEpisode(e.getIdEpisode())
                .titre(e.getTitre())
                .idAnime(e.getIdAnime())
                .numEp(e.getNumEp())
                .saison(e.getSaison())
                .lien(e.getLien())
                .build();
    }

    public EpisodeDTO modifierEpisode(EpisodeDTO episodeDTO)
    {
        Episode episode = episodeRepository.getById(episodeDTO.getIdEpisode());
        if(episodeDTO.getTitre() != null)
            episode.setTitre(episodeDTO.getTitre());
        if(episodeDTO.getIdAnime() != null)
            episode.setIdAnime(episodeDTO.getIdAnime());
        if(episodeDTO.getNumEp() != 0)
            episode.setNumEp(episodeDTO.getNumEp());
        if(episodeDTO.getSaison() != null)
            episode.setSaison(episodeDTO.getSaison());
        if(episodeDTO.getLien() != null)
            episode.setLien(episodeDTO.getLien());

        episodeRepository.save(episode);
        return episodeDTO;
    }

    public List<EpisodeDTO> getAllEpisode()
    {
        List<Episode> list = this.episodeRepository
                .findAll();
        List<EpisodeDTO> dto = new ArrayList<>();
        for(Episode e :list){
            EpisodeDTO d = EpisodeDTO.builder()
                    .idEpisode(e.getIdEpisode())
                    .titre(e.getTitre())
                    .idAnime(e.getIdAnime())
                    .numEp(e.getNumEp())
                    .saison(e.getSaison())
                    .lien(e.getLien())
                    .build();
            dto.add(d);
        }
        return dto;
    }

    public List<EpisodeDTO> getEpisodeByIdAnime(Integer idAnime) throws ProcessExeption {
        List<Episode> list = episodeRepository.findEpisodesByIdAnime(idAnime);
        if(list.size() == 0)
            throw new ProcessExeption("L'anime ne contient pas d'episode");

        List<EpisodeDTO> dto = new ArrayList<>();
        for(Episode e :list){
            EpisodeDTO d = EpisodeDTO.builder()
                    .idEpisode(e.getIdEpisode())
                    .titre(e.getTitre())
                    .idAnime(e.getIdAnime())
                    .numEp(e.getNumEp())
                    .saison(e.getSaison())
                    .lien(e.getLien())
                    .build();
            dto.add(d);
        }
        return dto;
    }
}
