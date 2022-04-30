package com.uphf.episode.config;

import com.uphf.episode.dto.EpisodeDTO;
import com.uphf.episode.exeptions.ProcessExeption;
import com.uphf.episode.ressources.AdminRessource;
import com.uphf.episode.services.EpisodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;
import java.util.logging.Logger;

@Configuration
public class Config {

    @Autowired
    public AdminRessource adminRessource;
    private static final Logger LOG = Logger.getLogger(String.valueOf(Config.class));


    @EventListener(ApplicationReadyEvent.class)
    public void afterStartup() {
        List<EpisodeDTO> temp = new ArrayList<>();
        String fileName = "C:\\Users\\still\\Desktop\\ProjetWEB\\ProjetWebAnime\\episode\\src\\main\\resources\\data.txt";

        try {
            Scanner scan = new Scanner(new File(fileName));
            while(scan.hasNextLine()){
                for(int i = 0; i<8; i++)
                {
                    temp.add(EpisodeDTO.builder()
                            .titre(scan.nextLine())
                            .idAnime(Integer.parseInt(scan.nextLine()))
                            .numEp(Integer.parseInt(scan.nextLine()))
                            .saison(scan.nextLine())
                            .lien(scan.nextLine())
                            .build());
                    scan.nextLine();
                }
            }
            for(EpisodeDTO t:temp)
                adminRessource.createEpisode(t);

        }catch ( FileNotFoundException | ProcessExeption e1)
        {
            e1.printStackTrace();
        }

    }

}
