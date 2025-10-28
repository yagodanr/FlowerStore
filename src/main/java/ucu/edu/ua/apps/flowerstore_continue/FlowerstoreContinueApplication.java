package flowerstore_continue;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "flowerstore_continue")
@EntityScan(basePackages = {"flowers", "flowerstore_continue"})
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
