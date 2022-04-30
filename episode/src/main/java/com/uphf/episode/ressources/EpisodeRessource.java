package com.uphf.episode.ressources;

import com.uphf.episode.dto.EpisodeDTO;
import com.uphf.episode.exeptions.ProcessExeption;
import com.uphf.episode.services.CommonService;
import com.uphf.episode.services.EpisodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("episode")
public class EpisodeRessource extends CommonService {
    @Autowired
    EpisodeService episodeService;

    @GetMapping("episodes")
    public List<EpisodeDTO> getAllEpisode(){ return this.episodeService.getAllEpisode(); }

    @GetMapping("episodes/{id}")
    public EpisodeDTO getEpisodeById(@PathVariable("id") Integer idEpisode) throws ProcessExeption {
       return episodeService.getEpisodeById(idEpisode);
    }

    @GetMapping("episode")
    public List<EpisodeDTO> getEpisodeByIdAnime(@RequestParam("idAnime") Integer idAnime) throws ProcessExeption {
        return episodeService.getEpisodeByIdAnime(idAnime);
    }
}
