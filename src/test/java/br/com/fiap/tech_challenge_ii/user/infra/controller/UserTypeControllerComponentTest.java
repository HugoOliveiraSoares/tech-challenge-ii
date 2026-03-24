package br.com.fiap.tech_challenge_ii.user.infra.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.fiap.tech_challenge_ii.user.core.domain.Owner;
import br.com.fiap.tech_challenge_ii.user.core.domain.Restaurant;
import br.com.fiap.tech_challenge_ii.user.core.gateway.RestaurantGateway;
import br.com.fiap.tech_challenge_ii.user.core.gateway.UserGateway;
import br.com.fiap.tech_challenge_ii.user.core.gateway.UserTypeGateway;
import br.com.fiap.tech_challenge_ii.user.infra.controller.json.UserType;
import br.com.fiap.tech_challenge_ii.user.infra.controller.json.UserTypeJson;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class UserTypeControllerComponentTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private RestaurantGateway restaurantGateway;

    @MockBean
    private UserGateway userGateway;

    @MockBean
    private UserTypeGateway userTypeGateway;

    @BeforeEach
    void setUp() {
        Restaurant restaurant = new Restaurant(1L, "Test Restaurant");
        Owner owner = new Owner(1L, "Test Owner", "Owner", Collections.emptyList());
        
        when(userGateway.findById(1L)).thenReturn(Optional.of(owner));
        when(restaurantGateway.getByUserId(1L)).thenReturn(List.of(restaurant));
        when(userTypeGateway.save(anyLong(), any(String.class), any(Class.class))).thenReturn(1L);
    }

    @Test
    void shouldCreateUserType_whenUserIsOwner() throws Exception {
        UserTypeJson request = new UserTypeJson(null, "any-tipo-nome", UserType.OWNER);

        mockMvc.perform(post("/restaurantes/1/tipo-usuarios")
                .header("x-user-id", 1L)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
                .andDo(print())
                .andExpect(status().isOk());
    }
}
