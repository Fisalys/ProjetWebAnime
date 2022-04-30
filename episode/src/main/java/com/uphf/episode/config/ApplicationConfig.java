package com.uphf.episode.config;

import com.uphf.episode.dto.EpisodeDTO;
import com.uphf.episode.exeptions.ProcessExeption;
import com.uphf.episode.models.Episode;
import com.uphf.episode.repositories.EpisodeRepository;
import com.uphf.episode.ressources.AdminRessource;
import com.uphf.episode.services.EpisodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class ApplicationConfig {
    @Bean
    @LoadBalanced
    public RestTemplate restTemplate()
    {
        return new RestTemplate();
    }

}

