package tn.esprit.healthcloud.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfiguration implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**") // Update this with the appropriate endpoint path
                .allowedOrigins("*") // Update this with the appropriate domain or '*' to allow any domain
                .allowedMethods("GET", "POST", "PUT", "DELETE") // Update this with the allowed HTTP methods
                .allowedHeaders("*") // Update this with the allowed headers or '*' to allow any header
                .maxAge(3600); // Update this with the max age of the CORS preflight request
    }
}
