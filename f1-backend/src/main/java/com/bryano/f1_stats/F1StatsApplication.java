package com.bryano.f1_stats;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class F1StatsApplication {

	public static void main(String[] args) {
		SpringApplication.run(F1StatsApplication.class, args);
	}

	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/api/**")
						.allowedOrigins("https://bryano-f1-stats.onrender.com", "http://localhost:3000")
						.allowedMethods("GET");
				WebMvcConfigurer.super.addCorsMappings(registry);
			}
		};
	}

}
