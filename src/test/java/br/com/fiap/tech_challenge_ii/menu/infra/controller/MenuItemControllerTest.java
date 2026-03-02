package br.com.fiap.tech_challenge_ii.menu.infra.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
public class MenuItemControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @Sql(scripts = "/sql/menu.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
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

}
