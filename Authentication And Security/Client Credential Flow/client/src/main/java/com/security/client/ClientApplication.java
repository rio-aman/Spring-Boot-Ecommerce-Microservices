package com.security.client;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.oauth2.client.*;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class ClientApplication {

	public static void main(String[] args) {
		SpringApplication.run(ClientApplication.class, args);
	}

	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}

	@Bean
	public OAuth2AuthorizedClientService oAuth2AuthorizedClientService(ClientRegistrationRepository clientRegistrationRepository) {
		return new InMemoryOAuth2AuthorizedClientService(clientRegistrationRepository);
	} // this method keeps the track of authorized clients and their token inmemory

	@Bean
	public OAuth2AuthorizedClientManager oAuth2AuthorizedClientManager(
			ClientRegistrationRepository repos,
			OAuth2AuthorizedClientService clientService
	) {
		var manager = new AuthorizedClientServiceOAuth2AuthorizedClientManager(
				repos, clientService
		);

		OAuth2AuthorizedClientProvider provider = OAuth2AuthorizedClientProviderBuilder.builder()
				.clientCredentials()
				.build();

		manager.setAuthorizedClientProvider(provider);
		return manager;
	}

}
