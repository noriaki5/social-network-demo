package com.study.noriaki.socialNetworkDemo;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SocialNetworkApplication {

	public static void main(String[] args) {
		SpringApplication.run(SocialNetworkApplication.class, args);
	}

	@Bean
	public OpenAPI socialNetworkAPI() {
		return new OpenAPI()
				.info(new Info().title("Social Network API")
						.description("Application for managing friendlists")
						.version("1.0")
						.contact(new Contact()
								.name("Andrew Re")));
	}
}
