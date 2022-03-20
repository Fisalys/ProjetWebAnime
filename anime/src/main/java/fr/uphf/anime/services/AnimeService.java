package fr.uphf.anime.services;

import fr.uphf.anime.dto.AnimeDTO;
import fr.uphf.anime.exeptions.NotValidExeption;
import fr.uphf.anime.exeptions.ProcessExeption;
import fr.uphf.anime.models.Anime;
import fr.uphf.anime.repositories.AnimeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AnimeService extends CommonService {

    private static final String ANIME_NOT_FOUND = "Anime non trouvé avec l'id : %s";

    @Autowired
    private AnimeRepository animeRepository;

    public void validateAnimeModel(AnimeDTO animeToCreate) throws NotValidExeption
    {
        NotValidExeption e = new NotValidExeption();

        if(animeToCreate == null)
            e.getMessages().add("AnimeModel : null");
        else{
            if(animeToCreate.getNom() == null || animeToCreate.getNom().isBlank())
                e.getMessages().add("Nom est vide");
        }

        if(!e.getMessages().isEmpty())
            throw e;
    }

    public AnimeDTO saveAnime(AnimeDTO animeToCreate) throws ProcessExeption
    {
        Anime a = Anime.builder()
                .nom(animeToCreate.getNom())
                .nbEp(animeToCreate.getNbEp())
                .genre(animeToCreate.getGenre())
                .description(animeToCreate.getDescription())
                .build();

        animeRepository.save(a);
        return animeToCreate;
    }
}
