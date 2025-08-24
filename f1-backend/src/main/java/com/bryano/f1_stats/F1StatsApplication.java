package com.bryano.f1_stats;

import com.bryano.f1_stats.http.DataScraperHttpClient;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.support.RestClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;
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
                        .allowedOrigins(System.getenv("CORS_ALLOWED_ORIGINS"))
                        .allowedMethods("GET");
                WebMvcConfigurer.super.addCorsMappings(registry);
            }
        };
    }

    @Bean
    DataScraperHttpClient dataScraperHttpClient() {
        RestClient client = RestClient.create(System.getenv("DATA_SCRAPER_API_BASE_URL"));
        HttpServiceProxyFactory factory = HttpServiceProxyFactory
                .builderFor(RestClientAdapter.create(client))
                .build();
        return factory.createClient(DataScraperHttpClient.class);
    }

}
