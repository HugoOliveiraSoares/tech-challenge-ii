package br.com.fiap.tech_challenge_ii.restaurant.infra.controller.json;

public record AddressJson(Long id,
                          String street,
                          String number,
                          String neighborhood,
                          String city,
                          String zipCode) {
}
