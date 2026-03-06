package br.com.fiap.tech_challenge_ii.menu.infra.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.math.BigDecimal;
import java.util.List;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.jdbc.SqlConfig.TransactionMode;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.fiap.tech_challenge_ii.menu.core.dto.MenuItemRequestDTO;

@SpringBootTest
@AutoConfigureMockMvc
@Sql(scripts = "/sql/menu.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_CLASS, config = @SqlConfig(transactionMode = TransactionMode.ISOLATED))
public class MenuItemControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void shouldGetMenuFromRestaurant() throws Exception {
        mockMvc.perform(get("/menu/1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", Matchers.hasSize(2)))
                .andExpect(jsonPath("$[0].name").value("Feijoada"))
                .andExpect(jsonPath("$[0].price").value(45.90))
                .andExpect(jsonPath("$[0].description").value("Traditional Brazilian black bean stew with pork"))
                .andExpect(jsonPath("$[0].isOnlyLocalConsuption").value(false))
                .andExpect(jsonPath("$[0].photoPath").value("/images/feijoada.jpg"))
                .andExpect(jsonPath("$[0].restaurantId").value(1));

    }

    @Test
    void shouldCreateMenuItems() throws Exception {
        List<MenuItemRequestDTO> items = List.of(
                new MenuItemRequestDTO("Picanha", "Delicious beef", new BigDecimal("59.90"), false,
                        "/images/picanha.jpg", 1L),
                new MenuItemRequestDTO("Suco de Laranja", "Fresh orange juice", new BigDecimal("12.00"), false,
                        "/images/suco.jpg", 1L));

        mockMvc.perform(post("/menu")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(items)))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.ids", Matchers.hasSize(2)));
    }

    @Test
    void shouldReturnBadRequestForMissingName() throws Exception {
        List<MenuItemRequestDTO> items = List.of(
                new MenuItemRequestDTO("", "Description", new BigDecimal("10.00"), false, null, 1L));

        mockMvc.perform(post("/menu")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(items)))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    void shouldReturnBadRequestForMissingPrice() throws Exception {
        List<MenuItemRequestDTO> items = List.of(
                new MenuItemRequestDTO("Test Item", "Description", null, false, null, 1L));

        mockMvc.perform(post("/menu")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(items)))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    void shouldReturnBadRequestForNegativePrice() throws Exception {
        List<MenuItemRequestDTO> items = List.of(
                new MenuItemRequestDTO("Test Item", "Description", new BigDecimal("-10.00"), false, null, 1L));

        mockMvc.perform(post("/menu")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(items)))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    void shouldReturnBadRequestForDuplicateItem() throws Exception {
        List<MenuItemRequestDTO> items = List.of(
                new MenuItemRequestDTO("Feijoada", "Traditional dish", new BigDecimal("45.90"), false, null, 1L));

        mockMvc.perform(post("/menu")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(items)))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.title").value("Existing Menu Item"))
                .andExpect(jsonPath("$.detail").value("Item with name 'Feijoada' already exists"));
    }

}
