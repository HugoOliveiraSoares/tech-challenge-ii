package br.com.fiap.tech_challenge_ii.menu.core.gateway;

import java.util.List;
import java.util.Optional;

import br.com.fiap.tech_challenge_ii.menu.core.domain.MenuItem;

public interface MenuItemGateway {

    MenuItem save(MenuItem menuItem);

    Optional<MenuItem> findByMenuItemNameAndRestaurantId(String name, Long restaurantId);

    List<MenuItem> findByRestaurantId(Long restaurantId);

}
