package br.com.fiap.tech_challenge_ii.restaurant.infra.controller;

import br.com.fiap.tech_challenge_ii.restaurant.core.gateway.filter.RestaurantSearchFilter;
import br.com.fiap.tech_challenge_ii.restaurant.core.usecase.CreateRestaurant;
import br.com.fiap.tech_challenge_ii.restaurant.core.usecase.DeleteRestaurantById;
import br.com.fiap.tech_challenge_ii.restaurant.core.usecase.GetRestaurantById;
import br.com.fiap.tech_challenge_ii.restaurant.core.usecase.ListRestaurants;
import br.com.fiap.tech_challenge_ii.restaurant.infra.controller.json.CreateRestaurantRequest;
import br.com.fiap.tech_challenge_ii.restaurant.infra.controller.json.CreateRestaurantResponse;
import br.com.fiap.tech_challenge_ii.restaurant.infra.controller.json.GetRestaurantResponse;
import br.com.fiap.tech_challenge_ii.restaurant.infra.controller.json.ListRestaurantResponse;
import br.com.fiap.tech_challenge_ii.restaurant.infra.controller.mapper.RestaurantMapper;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/restaurants")
@AllArgsConstructor
public class RestaurantController {
    private final CreateRestaurant createRestaurant;
    private final GetRestaurantById getRestaurantById;
    private final ListRestaurants listRestaurants;
    private final DeleteRestaurantById deleteRestaurantById;
    private final RestaurantMapper mapper;

    @GetMapping("/{id}")
    public ResponseEntity<GetRestaurantResponse> getById(@PathVariable Long id){
        var restaurant = getRestaurantById.getById(id);

        return ResponseEntity.ok(mapper.toResponse(restaurant));
    }

    @GetMapping
    public ResponseEntity<List<ListRestaurantResponse>> listAll(@RequestParam(name = "name", required = false) String name){
        var filter = new RestaurantSearchFilter(name != null ? name : "");
        var response = listRestaurants.list(filter).stream()
                .map(mapper::toResponse)
                .toList();

        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<CreateRestaurantResponse> create(@Valid @RequestBody CreateRestaurantRequest request){
        var output = createRestaurant.create(mapper.toInput(request));
        URI uri = URI.create("/restaurants/" + output.id());
        return ResponseEntity.created(uri).body(mapper.toResponse(output));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long restaurantId){
        //TODO: Get loggedUserId from JWT token
        Long loggedUserId = 1L; //temporary
        deleteRestaurantById.deleteById(loggedUserId, restaurantId);

        return ResponseEntity.noContent().build();
    }
}

