package com.uphf.episode;

import com.uphf.episode.dto.EpisodeDTO;
import com.uphf.episode.exeptions.ProcessExeption;
import com.uphf.episode.ressources.AdminRessource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
@EnableEurekaClient
public class EpisodeApplication {


	public static void main(String[] args) {
		SpringApplication.run(EpisodeApplication.class, args);
		EpisodeApplication e = new EpisodeApplication();


	}

}
