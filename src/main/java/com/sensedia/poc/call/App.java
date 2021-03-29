package com.sensedia.poc.call;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = "com.sensedia.poc.call")
@EnableJpaRepositories
public class App {

	public static void main(String[] args) throws Exception {
		SpringApplication.run(App.class);
	}
}
