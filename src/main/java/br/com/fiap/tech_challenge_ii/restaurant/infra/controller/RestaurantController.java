package br.com.fiap.tech_challenge_ii.restaurant.infra.controller;

import br.com.fiap.tech_challenge_ii.restaurant.core.usecase.CreateRestaurant;
import br.com.fiap.tech_challenge_ii.restaurant.infra.controller.json.CreateRestaurantRequest;
import br.com.fiap.tech_challenge_ii.restaurant.infra.controller.json.CreateRestaurantResponse;
import br.com.fiap.tech_challenge_ii.restaurant.infra.controller.mapper.RestaurantMapper;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
@RequestMapping("/restaurants")
@AllArgsConstructor
public class RestaurantController {
    private final CreateRestaurant createRestaurant;
    private final RestaurantMapper mapper;

    @PostMapping
    public ResponseEntity<CreateRestaurantResponse> create(@Valid @RequestBody CreateRestaurantRequest request){
        var output = createRestaurant.create(mapper.toInput(request));
        URI uri = URI.create("/restaurants/" + output.id());
        return ResponseEntity.created(uri).body(mapper.toResponse(output));
    }
}

