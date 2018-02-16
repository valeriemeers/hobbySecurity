package be.qnh.hobby;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class HobbyApplication {

	public static void main(String[] args) {

		SpringApplication.run(HobbyApplication.class, args);
	}
}
