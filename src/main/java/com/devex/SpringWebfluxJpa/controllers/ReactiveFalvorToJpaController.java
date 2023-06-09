package com.devex.SpringWebfluxJpa.controllers;

import java.time.Duration;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientRequestException;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import com.devex.SpringWebfluxJpa.dto.Product;
import com.devex.SpringWebfluxJpa.dto.User;
import com.devex.SpringWebfluxJpa.entity.RecentlyViewedProducts;
import com.devex.SpringWebfluxJpa.populators.RecentlyViewedProdPopulator;
import com.devex.SpringWebfluxJpa.repository.RecentlyViewedProductsRepository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;
import reactor.util.retry.Retry;

@RestController
@RequestMapping("/rxjpa")
public class ReactiveFalvorToJpaController 
{
	@Resource(name = "productClient")
	WebClient productWebClient;
	
	@Resource(name = "userClient")
	WebClient userWebClient;
	
	@Resource(name = "recentlyViewedProductsRepository")
	RecentlyViewedProductsRepository recentlyViewedProductsRepository;

	private Flux<String> just;

	private List<Integer> of;
	
	
	@GetMapping("/info")
	public String getInfo()
	{
		return "adding reactive falvour to spring jpa";
	}
	
	@GetMapping("/recent")
	public Mono<ResponseEntity<RecentlyViewedProducts>> getRecentlyViewedProducts()
	{
		
		return getUserDetails()
						.retryWhen(Retry.fixedDelay(5, Duration.ofSeconds(1)))
						.map(u -> RecentlyViewedProdPopulator.setUserDetails(u, new RecentlyViewedProducts()))
						.flatMap(recent -> {
								
							return getProductDetails()
											.retryWhen(Retry.fixedDelay(5, Duration.ofSeconds(1)))
											.map(p -> RecentlyViewedProdPopulator.setProductDetails(p, recent));
											
						})
						.map(recentlyViewedProductsRepository::save)
						.map(l -> new ResponseEntity<>(l,HttpStatus.OK))
						.onErrorReturn(WebClientRequestException.class, ResponseEntity.status(HttpStatus.GATEWAY_TIMEOUT).build())
						.onErrorReturn(WebClientResponseException.class,ResponseEntity.badRequest().build())
						//since traditional spring jpa is blocking we should use scheduler her to have reactive nature
						//we can use subscribeOn or publishOn as per need
						.subscribeOn(Schedulers.boundedElastic());
	}
	
	
	@GetMapping("/recent/all")
	public Flux<RecentlyViewedProducts> getAllRecentlyViewedProducts()
	{
		return Flux.fromStream(() -> recentlyViewedProductsRepository.findAll().stream())
				.subscribeOn(Schedulers.boundedElastic());
						
	}
	
	
	public Mono<User> getUserDetails()
	{
		String userId = "1";
		
		return userWebClient.get().uri("/get/"+userId).retrieve().bodyToMono(User.class);
	}
	
	public Mono<Product> getProductDetails()
	{
		String prod = "00001";
		
		return productWebClient.get().uri("/get/"+prod).retrieve().bodyToMono(Product.class);
	}
	
	
}
