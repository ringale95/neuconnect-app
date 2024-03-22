package edu.neu.neuconnect;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class NeuconnectApplication {

	public static void main(String[] args) {
		System.out.println("Env: " + System.getenv("SPRING_DATASOURCE_URL"));
		SpringApplication.run(NeuconnectApplication.class, args);
	}

}
