package pl.filipiak.jakub.training.springtestcontainers;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
@PropertySource("classpath:db.properties")
public class SpringTestContainersApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringTestContainersApplication.class, args);
	}

}
