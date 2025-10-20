package ucu.edu.ua.apps.flowerstore_continue;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class FlowerstoreContinueApplication {

	public static void main(String[] args) {
		SpringApplication.run(FlowerstoreContinueApplication.class, args);
	}

	@GetMapping("/")
	public String hello() {
		return "Hello, world!";
	}

}
