package fr.uphf.anime.ressources;

import fr.uphf.anime.dto.AnimeDTO;
import fr.uphf.anime.exeptions.ProcessExeption;
import fr.uphf.anime.services.AnimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("admin")
public class AdminRessource {
    @Autowired
    AnimeService animeService;

    @PostMapping("create")
    public AnimeDTO createAnime(@RequestBody AnimeDTO animeDTO) throws ProcessExeption {
        animeService.validateAnimeModel(animeDTO);
        return animeService.saveAnime(animeDTO);
    }

    @PutMapping
    AnimeDTO modifierAnime(@RequestBody AnimeDTO animeDTO)
    {
        return animeService.modifierAnime(animeDTO);
    }

    @PutMapping("addEpisode")
    AnimeDTO addEpisode(@RequestParam Integer idAnime, @RequestParam  Integer idEpisode) throws ProcessExeption {
        return animeService.ajouterEpisode(idAnime, idEpisode);
    }

    @PutMapping("addPersonnage")
    AnimeDTO addPersonnage(@RequestParam Integer idAnime, @RequestParam  Integer idPersonnage) throws ProcessExeption {
        return animeService.ajouterPersonnage(idAnime, idPersonnage);
    }
}
