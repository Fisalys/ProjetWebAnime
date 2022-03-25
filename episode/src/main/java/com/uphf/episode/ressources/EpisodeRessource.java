package com.uphf.episode.ressources;

import com.uphf.episode.dto.EpisodeDTO;
import com.uphf.episode.exeptions.ProcessExeption;
import com.uphf.episode.services.CommonService;
import com.uphf.episode.services.EpisodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("episode")
public class EpisodeRessource extends CommonService {

    @Autowired
    EpisodeService episodeService;

    @PostMapping("create")
    public EpisodeDTO createEpisode(@RequestBody EpisodeDTO episodeDTO) throws ProcessExeption {
        episodeService.validateEpisodeModel(episodeDTO);
        return episodeService.saveEpisode(episodeDTO);
    }
}
