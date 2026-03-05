package br.com.fiap.tech_challenge_ii.menu.core.usecase.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.nullable;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import br.com.fiap.tech_challenge_ii.menu.core.domain.MenuItem;
import br.com.fiap.tech_challenge_ii.menu.core.dto.MenuItemDTO;
import br.com.fiap.tech_challenge_ii.menu.core.exception.ExistingMenuItemException;
import br.com.fiap.tech_challenge_ii.menu.core.gateway.MenuItemGateway;

@ExtendWith(MockitoExtension.class)
class CreateMenuItemUseCaseImplTest {

    @Mock
    private MenuItemGateway menuItemGateway;

    @InjectMocks
    private CreateMenuItemUseCaseImpl createMenuItemUseCase;

    @Test
    void save_shouldCreateMenuItem_whenItemDoesNotExist() {
        MenuItemDTO dto = new MenuItemDTO(
                "Burger",
                "Delicious burger",
                new BigDecimal("25.90"),
                false,
                "/photos/burger.jpg",
                1L);

        MenuItem savedMenuItem = new MenuItem(
                1L,
                "Burger",
                "Delicious burger",
                new BigDecimal("25.90"),
                false,
                "/photos/burger.jpg",
                1L);

        when(menuItemGateway.findByMenuItemNameAndRestaurantId(eq("Burger"), eq(1L)))
                .thenReturn(Optional.empty());
        when(menuItemGateway.save(any(MenuItem.class))).thenReturn(savedMenuItem);

        List<Long> result = createMenuItemUseCase.save(List.of(dto));

        assertEquals(1, result.size());
        assertEquals(1L, result.get(0));
        verify(menuItemGateway, times(1)).save(any(MenuItem.class));
    }

    @Test
    void save_shouldThrowExistingMenuItemException_whenItemAlreadyExists() {
        MenuItemDTO dto = new MenuItemDTO(
                "Burger",
                "Delicious burger",
                new BigDecimal("25.90"),
                false,
                "/photos/burger.jpg",
                1L);

        MenuItem existingItem = new MenuItem(
                1L,
                "Burger",
                "Delicious burger",
                new BigDecimal("25.90"),
                false,
                "/photos/burger.jpg",
                1L);

        when(menuItemGateway.findByMenuItemNameAndRestaurantId(eq("Burger"), eq(1L)))
                .thenReturn(Optional.of(existingItem));

        ExistingMenuItemException exception = assertThrows(
                ExistingMenuItemException.class,
                () -> createMenuItemUseCase.save(List.of(dto)));

        assertEquals("Item with name 'Burger' already exists", exception.getMessage());
        verify(menuItemGateway, never()).save(any(MenuItem.class));
    }

    @Test
    void save_shouldCreateMultipleMenuItems_whenListHasMultipleValidItems() {
        MenuItemDTO dto1 = new MenuItemDTO(
                "Burger",
                "Delicious burger",
                new BigDecimal("25.90"),
                false,
                "/photos/burger.jpg",
                1L);

        MenuItemDTO dto2 = new MenuItemDTO(
                "Pizza",
                "Cheesy pizza",
                new BigDecimal("45.90"),
                false,
                "/photos/pizza.jpg",
                1L);

        MenuItem savedItem1 = new MenuItem(1L, "Burger", "Delicious burger",
                new BigDecimal("25.90"), false, "/photos/burger.jpg", 1L);
        MenuItem savedItem2 = new MenuItem(2L, "Pizza", "Cheesy pizza",
                new BigDecimal("45.90"), false, "/photos/pizza.jpg", 1L);

        when(menuItemGateway.findByMenuItemNameAndRestaurantId(eq("Burger"), eq(1L)))
                .thenReturn(Optional.empty());
        when(menuItemGateway.findByMenuItemNameAndRestaurantId(eq("Pizza"), eq(1L)))
                .thenReturn(Optional.empty());
        when(menuItemGateway.save(any(MenuItem.class)))
                .thenReturn(savedItem1)
                .thenReturn(savedItem2);

        List<Long> result = createMenuItemUseCase.save(List.of(dto1, dto2));

        assertEquals(2, result.size());
        assertEquals(1L, result.get(0));
        assertEquals(2L, result.get(1));
        verify(menuItemGateway, times(2)).save(any(MenuItem.class));
    }

    @Test
    void save_shouldReturnEmptyList_whenInputListIsEmpty() {
        List<Long> result = createMenuItemUseCase.save(List.of());

        assertEquals(0, result.size());
        verify(menuItemGateway, never()).findByMenuItemNameAndRestaurantId(any(), any());
        verify(menuItemGateway, never()).save(any(MenuItem.class));
    }

    @Test
    void save_shouldThrowIllegalArgumentException_whenRestaurantIdIsNull() {
        MenuItemDTO dto = new MenuItemDTO(
                "Burger",
                "Delicious burger",
                new BigDecimal("25.90"),
                false,
                "/photos/burger.jpg",
                null);

        when(menuItemGateway.findByMenuItemNameAndRestaurantId(anyString(), nullable(Long.class)))
                .thenReturn(Optional.empty());

        assertThrows(IllegalArgumentException.class,
                () -> createMenuItemUseCase.save(List.of(dto)));
        verify(menuItemGateway, never()).save(any(MenuItem.class));
    }
}
