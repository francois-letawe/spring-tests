package be.rnb.test;

import be.rnb.test.proxy.PreFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ProxyApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProxyApplication.class, args);
	}

	@Bean("PreFilter")
	public PreFilter preFilter() {
		return new PreFilter();
	}

}
