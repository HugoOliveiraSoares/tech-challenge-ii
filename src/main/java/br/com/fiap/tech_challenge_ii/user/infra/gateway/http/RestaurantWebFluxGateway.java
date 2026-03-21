package br.com.fiap.tech_challenge_ii.user.infra.gateway.http;

import java.util.List;

import br.com.fiap.tech_challenge_ii.user.core.domain.Restaurant;
import br.com.fiap.tech_challenge_ii.user.core.gateway.RestaurantGateway;
import br.com.fiap.tech_challenge_ii.user.infra.gateway.http.json.RestaurantJson;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.reactive.function.client.WebClient;

@Slf4j
@Component
@RequiredArgsConstructor
public class RestaurantWebFluxGateway implements RestaurantGateway {

	@Value("${fiap.restaurante.base-url}")
	private String baseUrl;

	private final WebClient.Builder webClientBuilder;

	@Override
	public List<Restaurant> getByUserId(Long loggedInUserId) {
		final var url = baseUrl + "/user/" + loggedInUserId + "/restaurants";
		List<RestaurantJson> incidentesJson = callService(url);
		return incidentesJson.stream().map(this::map).toList();
	}

	private List<RestaurantJson> callService(String url) {
		var webClient = webClientBuilder.baseUrl(url).build();

		return webClient
				.get()
				.retrieve()
				.bodyToMono(new ParameterizedTypeReference<List<RestaurantJson>>() {})
				.block();

	}

	private Restaurant map(RestaurantJson restaurantJson) {
		return new Restaurant(restaurantJson.getId(), restaurantJson.getName());
	}

}
