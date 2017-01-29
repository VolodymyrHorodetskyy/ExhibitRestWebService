package ua.vhor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.SecurityAutoConfiguration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class })
@EnableScheduling
@EnableJpaRepositories
public class ExhibitRestWebServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ExhibitRestWebServiceApplication.class, args);
	}
}
