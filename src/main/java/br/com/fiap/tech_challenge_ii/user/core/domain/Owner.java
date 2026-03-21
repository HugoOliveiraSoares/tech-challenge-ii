package br.com.fiap.tech_challenge_ii.user.core.domain;

import java.util.List;

import lombok.Getter;

@Getter
public class Owner extends User {

	private List<Restaurant> restaurants;

	public Owner(Long id, String nameType, List<Restaurant> restaurants) {
		super(id, nameType);
		this.restaurants = restaurants;
	}

	public boolean isOwner(Long idRestaurant) {
		return restaurants.stream().anyMatch(r -> r.getId().equals(idRestaurant));
	}

	public void addRestaurants(List<Restaurant> restaurants) {
		this.restaurants = restaurants;
	}

}
