package be.rnb.test;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

@SpringBootApplication
public class TestApplication {

	public static void main(String[] args) {
		SpringApplication.run(TestApplication.class, args);
	}

	@RestController
	public class TestController {
		@GetMapping(value = "/test", produces = "application/pdf")
		public Flux<DataBuffer> test() {
			return downloadFile();
		}
	}

	Flux<DataBuffer> downloadFile() {
		return WebClient.builder()
				.baseUrl("https://docs.aws.amazon.com/whitepapers/latest/how-aws-pricing-works/how-aws-pricing-works.pdf")
				.build()
				.get()
				.retrieve()
				.bodyToFlux(DataBuffer.class);
	}
}
