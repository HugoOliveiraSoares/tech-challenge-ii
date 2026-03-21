package br.com.fiap.tech_challenge_ii.user.infra.controller;


import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import br.com.fiap.tech_challenge_ii.user.core.domain.Client;
import br.com.fiap.tech_challenge_ii.user.core.usecase.CreateUserTypeUseCase;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;



@ActiveProfiles("controller-test")
@WebMvcTest(UserTypeController.class)
@AutoConfigureMockMvc(addFilters = false)
class TipoUserControllerIntTest {
	
	@Autowired
	private MockMvc mockMvc;

	@MockitoBean
	private CreateUserTypeUseCase createUserTypeUseCase;
	
	@Test
	void deveCriarClienteComSucesso() throws Exception {
		
		var idUsuarioLogado = 1L; 
		var idRestaurante = 2L;
		
		doReturn(1L).when(createUserTypeUseCase).create(eq(idUsuarioLogado), eq(idRestaurante), any(String.class), any());
		
		this.mockMvc.perform(
				post("/restaurantes/2/tipo-usuarios")
					.content("""
						{
						    "nome": "any name",
						    "tipo": "CLIENT"
						}""")
					.header("x-user-id", idUsuarioLogado)
					.contentType(MediaType.APPLICATION_JSON))
		.andDo(print())
		.andExpect(status().isOk())
		.andExpect(content().string("1"));
		
		verify(createUserTypeUseCase).create(eq(1L), eq(2L), eq("any name"), eq(Client.class));
	}
}
