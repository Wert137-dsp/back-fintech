package br.com.fiap.fintech;

import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class FintechApplication {

	public static void main(String[] args) {

        Dotenv dotenv = Dotenv.configure()
                .directory("./") // Local do arquivo .env
                .filename(".env")
                .load();

        dotenv.entries().forEach(entry -> {
            System.setProperty(entry.getKey(), entry.getValue());
        });

		SpringApplication.run(FintechApplication.class, args);
	}

}
