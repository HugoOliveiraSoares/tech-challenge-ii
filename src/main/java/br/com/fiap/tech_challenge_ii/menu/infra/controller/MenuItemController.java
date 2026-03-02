package br.com.fiap.tech_challenge_ii.menu.infra.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.fiap.tech_challenge_ii.menu.core.dto.MenuItemDTO;
import br.com.fiap.tech_challenge_ii.menu.core.usecase.FindMenuItemUseCase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/menu")
public class MenuItemController {

    // private final CreateMenuItemUseCase createMenuItemUseCase;
    private final FindMenuItemUseCase findMenuItemUseCase;

    @GetMapping("/{restaurantId}")
    public ResponseEntity<List<MenuItemDTO>> getMenuByRestaurant(
            @PathVariable Long restaurantId) {

        List<MenuItemDTO> byRestaurantId = findMenuItemUseCase.findByRestaurantId(restaurantId);

        return ResponseEntity.ok(byRestaurantId);

    }

}
