package fr.uphf.anime.ressources;

import fr.uphf.anime.dto.AnimeDTO;
import fr.uphf.anime.exeptions.ProcessExeption;
import fr.uphf.anime.services.AnimeService;
import fr.uphf.anime.services.CommonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("anime")
public class AnimeRessource extends CommonService {
    @Autowired
    AnimeService animeService;

    @GetMapping("animes")
    public List<AnimeDTO> getAllAnime(){ return this.animeService.getAllAnime(); }

}
