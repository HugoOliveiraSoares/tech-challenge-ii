package br.com.fiap.tech_challenge_ii.restaurant.infra.controller;

import br.com.fiap.tech_challenge_ii.restaurant.helper.RestaurantHelper;
import br.com.fiap.tech_challenge_ii.restaurant.infra.controller.json.CreateRestaurantRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
@SqlConfig(transactionMode = SqlConfig.TransactionMode.ISOLATED)
class RestaurantControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @Sql(scripts = {"/sql/restaurant/clean-up.sql",
            "/sql/restaurant/create-restaurant-setup.sql"},
            executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    void create_shouldCreateRestaurant_whenUserTypeIsOwner() throws Exception {
        CreateRestaurantRequest request = RestaurantHelper.buildCreateRestaurantRequest();

        mockMvc.perform(post("/restaurants")
                    .header("x-user-id", 200L)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(request)))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").isNumber())
                .andExpect(jsonPath("$.name").value("any-name"))
                .andExpect(jsonPath("$.kitchenType").value("ITALIAN"))
                .andExpect(header().string("Location", Matchers.matchesPattern("/restaurants/\\d+")));
    }

    @Test
    @Sql(scripts = "/sql/restaurant/clean-up.sql",
            executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    void create_shouldThrowUserNotFoundException_whenUserNotFound() throws Exception {
        CreateRestaurantRequest request = RestaurantHelper.buildCreateRestaurantRequest();

        mockMvc.perform(post("/restaurants")
                    .header("x-user-id", 900L)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(request)))
                .andDo(print())
                .andExpect(status().isNotFound());
    }

    @Test
    @Sql(scripts = {"/sql/restaurant/clean-up.sql",
                    "/sql/restaurant/create-restaurant-setup.sql"},
                    executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    void create_shouldThrowUnauthorizedOperationException_whenUserIsNotTypeOwner() throws Exception {
        CreateRestaurantRequest request = RestaurantHelper.buildCreateRestaurantRequest();

        mockMvc.perform(post("/restaurants")
                        .header("x-user-id", 201L)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andDo(print())
                .andExpect(status().isForbidden());
    }

    @Test
    @Sql(scripts = {"/sql/restaurant/clean-up.sql",
            "/sql/restaurant/create-restaurant-setup.sql"},
            executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    void create_shouldThrowDomainException_whenNameIsMissing() throws Exception{
        CreateRestaurantRequest invalidRequest = new CreateRestaurantRequest(
                "",
                RestaurantHelper.buildCreateRestaurantRequest().address(),
                "Italian",
                "any-opening-hours"
        );

        mockMvc.perform(post("/restaurants")
                .header("x-user-id", 200L)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(invalidRequest)))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }
}

