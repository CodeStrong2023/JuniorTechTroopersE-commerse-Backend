package junior_tech_troopers.backendtroopers;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class BackendtroopersApplication {

	public static void main(String[] args) {
		SpringApplication.run(BackendtroopersApplication.class, args);
	}

}
