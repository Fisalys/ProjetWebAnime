package com.uphf.episode.ressources;

import com.uphf.episode.dto.EpisodeDTO;
import com.uphf.episode.exeptions.ProcessExeption;
import com.uphf.episode.services.EpisodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("admin")
public class AdminRessource {
    @Autowired
    EpisodeService episodeService;

    @PostMapping("create")
    public EpisodeDTO createEpisode(@RequestBody EpisodeDTO episodeDTO) throws ProcessExeption {
        episodeService.validateEpisodeModel(episodeDTO);
        return episodeService.saveEpisode(episodeDTO);
    }

    @PutMapping
    EpisodeDTO modifierEpisode(@RequestBody EpisodeDTO episodeDTO)
    {
        return episodeService.modifierEpisode(episodeDTO);
    }
}
