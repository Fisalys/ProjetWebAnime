package fr.uphf.anime.services;

import fr.uphf.anime.dto.AnimeDTO;
import fr.uphf.anime.exeptions.NotValidExeption;
import fr.uphf.anime.exeptions.ProcessExeption;
import fr.uphf.anime.models.Anime;
import fr.uphf.anime.repositories.AnimeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
        animeToCreate.setIdAnime(a.getIdAnime());
        return animeToCreate;
    }

    public AnimeDTO modifierAnime(AnimeDTO animeDTO)
    {
        Anime anime = animeRepository.getById(animeDTO.getIdAnime());
        if(animeDTO.getNom() != null)
            anime.setNom(animeDTO.getNom());
        if(animeDTO.getGenre() != null)
            anime.setGenre(anime.getGenre());
        if(animeDTO.getDescription() != null)
            anime.setDescription(animeDTO.getDescription());
        if(animeDTO.getNbEp() != 0)
            anime.setNbEp(animeDTO.getNbEp());
        if(animeDTO.getListEp() != null)
            anime.setListEp(animeDTO.getListEp());
        if(animeDTO.getListPers() != null)
            anime.setListPers(animeDTO.getListPers());

        animeRepository.save(anime);
        return animeDTO;
    }

    public List<AnimeDTO> getAllAnime()
    {
        List<Anime> list = this.animeRepository
                .findAll();
        List<AnimeDTO> dto = new ArrayList<>();
        for(Anime a :list){
            AnimeDTO d = AnimeDTO.builder()
                    .idAnime(a.getIdAnime())
                    .nom(a.getNom())
                    .genre(a.getGenre())
                    .description(a.getDescription())
                    .nbEp(a.getNbEp())
                    .listEp(a.getListEp())
                    .listPers(a.getListPers())
                    .build();
            dto.add(d);
        }
        return dto;
    }

    public AnimeDTO getAnime(Integer id) throws ProcessExeption {
        Anime a = animeRepository.findById(id).orElseThrow(()-> new ProcessExeption(String.format(ANIME_NOT_FOUND, id)));
        return AnimeDTO.builder()
                .idAnime(a.getIdAnime())
                .nom(a.getNom())
                .genre(a.getGenre())
                .description(a.getDescription())
                .nbEp(a.getNbEp())
                .listEp(a.getListEp())
                .listPers(a.getListPers())
                .build();
    }

    public List<AnimeDTO> getAnimesById(List<Integer> listId) throws ProcessExeption {
        List<Anime> list =  new ArrayList<>();
        for(Integer i:listId)
        {
            list.add(animeRepository.findById(i).orElseThrow(()->new ProcessExeption(String.format(ANIME_NOT_FOUND, i))));
        }
        List<AnimeDTO> dto = new ArrayList<>();
        for(Anime a :list){
            AnimeDTO d = AnimeDTO.builder()
                    .idAnime(a.getIdAnime())
                    .nom(a.getNom())
                    .genre(a.getGenre())
                    .description(a.getDescription())
                    .nbEp(a.getNbEp())
                    .listEp(a.getListEp())
                    .listPers(a.getListPers())
                    .build();
            dto.add(d);
        }
        return dto;
    }

    public AnimeDTO ajouterEpisode(Integer idAnime,Integer idEpisode) throws ProcessExeption {
        Anime a = animeRepository.findById(idAnime).orElseThrow(()-> new ProcessExeption(String.format(ANIME_NOT_FOUND, idAnime)));

        a.getListEp().add(idEpisode);
        a.setNbEp(a.getNbEp()+1);
        animeRepository.save(a);

        return AnimeDTO.builder()
                .idAnime(a.getIdAnime())
                .nom(a.getNom())
                .genre(a.getGenre())
                .description(a.getDescription())
                .nbEp(a.getNbEp())
                .listEp(a.getListEp())
                .listPers(a.getListPers())
                .build();
    }
    public AnimeDTO ajouterPersonnage(Integer idAnime,Integer idPersonnage) throws ProcessExeption {
        Anime a = animeRepository.findById(idAnime).orElseThrow(()-> new ProcessExeption(String.format(ANIME_NOT_FOUND, idAnime)));

        a.getListPers().add(idPersonnage);
        animeRepository.save(a);

        return AnimeDTO.builder()
                .idAnime(a.getIdAnime())
                .nom(a.getNom())
                .genre(a.getGenre())
                .description(a.getDescription())
                .nbEp(a.getNbEp())
                .listEp(a.getListEp())
                .listPers(a.getListPers())
                .build();
    }
}
