package com.devex.SpringWebfluxJpa;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebclientConfigurations 
{	
	
	@Bean
	public WebClient productClient()
	{
		return WebClient.builder()
						.baseUrl("http://localhost:7039/prod")
						.build();
	}
	
	@Bean
	public WebClient userClient()
	{
		return WebClient.builder()
						.baseUrl("http://localhost:7040/users")
						.build();
	}
}
