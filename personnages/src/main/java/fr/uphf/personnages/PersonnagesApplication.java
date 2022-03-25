package fr.uphf.personnages;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class PersonnagesApplication {

	public static void main(String[] args) {
		SpringApplication.run(PersonnagesApplication.class, args);
	}

}
