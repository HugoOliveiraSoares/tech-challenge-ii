package br.com.fiap.tech_challenge_ii.menu.infra.controller;

import java.net.URI;
import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.fiap.tech_challenge_ii.menu.core.dto.MenuItemDTO;
import br.com.fiap.tech_challenge_ii.menu.core.usecase.CreateMenuItemUseCase;
import br.com.fiap.tech_challenge_ii.menu.core.usecase.FindMenuItemUseCase;
import br.com.fiap.tech_challenge_ii.menu.infra.controller.dto.MenuItemRequestDTO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/menu")
public class MenuItemController {

    private final CreateMenuItemUseCase createMenuItemUseCase;
    private final FindMenuItemUseCase findMenuItemUseCase;

    @PostMapping
    public ResponseEntity<Map<String, List<Long>>> createMenuItems(
            @RequestHeader("x-user-id") Long userId,
            @Valid @RequestBody List<MenuItemRequestDTO> menuItems) {

        List<MenuItemDTO> menuItemDTOs = menuItems.stream()
                .map(req -> new MenuItemDTO(
                        req.name(),
                        req.description(),
                        req.price(),
                        req.isOnlyLocalConsuption(),
                        req.photoPath(),
                        req.restaurantId()))
                .toList();

        List<Long> ids = createMenuItemUseCase.save(menuItemDTOs, userId);

        URI uri = URI.create("/menu");
        return ResponseEntity.created(uri).body(Map.of("ids", ids));
    }

    @GetMapping("/{restaurantId}")
    public ResponseEntity<List<MenuItemDTO>> getMenuByRestaurant(
            @PathVariable Long restaurantId) {

        List<MenuItemDTO> byRestaurantId = findMenuItemUseCase.findByRestaurantId(restaurantId);

        return ResponseEntity.ok(byRestaurantId);

    }

    @DeleteMapping("/{restaurantId}/{menuItemId}")
    public ResponseEntity<?> deleteMenuItem(
            @RequestHeader("x-user-id") Long userId,
            @PathVariable Long menuItemId) {

        return ResponseEntity.noContent().build();
    }

}
