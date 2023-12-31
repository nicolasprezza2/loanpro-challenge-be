package com.loanpro.challengebe;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class ChallengeBeApplication {

	public static void main(String[] args) {
		SpringApplication.run(ChallengeBeApplication.class, args);
	}

	@Bean
	public WebMvcConfigurer corsConfig() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/**")
						.allowedOrigins("http://localhost:3000")
						.allowedOrigins("https://loanpro-challenge-fe-production.up.railway.app/")
						.allowedMethods("GET", "POST","PUT", "DELETE");
			}
		};
	}

}
