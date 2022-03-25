package com.uphf.episode;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class EpisodeApplication {

	public static void main(String[] args) {
		SpringApplication.run(EpisodeApplication.class, args);
	}

}
