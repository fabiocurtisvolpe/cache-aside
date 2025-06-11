package br.com.fabio;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class FabioApplication {

	public static void main(String[] args) {
		SpringApplication.run(FabioApplication.class, args);
	}

}
