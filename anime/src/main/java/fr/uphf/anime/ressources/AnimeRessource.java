package fr.uphf.anime.ressources;

import fr.uphf.anime.dto.AnimeDTO;
import fr.uphf.anime.exeptions.ProcessExeption;
import fr.uphf.anime.services.AnimeService;
import fr.uphf.anime.services.CommonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("anime")
public class AnimeRessource extends CommonService {

    @Autowired
    AnimeService animeService;

    @PostMapping("create")
    public AnimeDTO createAnime(@RequestBody AnimeDTO animeDTO) throws ProcessExeption {
        animeService.validateAnimeModel(animeDTO);
        return animeService.saveAnime(animeDTO);
    }
}