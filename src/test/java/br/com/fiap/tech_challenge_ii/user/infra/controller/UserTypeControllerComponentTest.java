package br.com.fiap.tech_challenge_ii.user.infra.controller;

import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.okJson;
import static com.github.tomakehurst.wiremock.client.WireMock.stubFor;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.util.ReflectionTestUtils.setField;

import br.com.fiap.tech_challenge_ii.user.infra.controller.json.UserType;
import br.com.fiap.tech_challenge_ii.user.infra.controller.json.UserTypeJson;
import br.com.fiap.tech_challenge_ii.user.infra.gateway.db.entity.UserEntity;
import br.com.fiap.tech_challenge_ii.user.infra.gateway.db.entity.UserTypeEntity;
import br.com.fiap.tech_challenge_ii.user.infra.gateway.db.repository.UserEntityRepository;
import br.com.fiap.tech_challenge_ii.user.infra.gateway.db.repository.UserTypeEntityRepository;
import br.com.fiap.tech_challenge_ii.user.infra.gateway.http.RestaurantWebFluxGateway;
import br.com.fiap.tech_challenge_ii.util.container.AbstractContainer;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import com.github.tomakehurst.wiremock.junit5.WireMockRuntimeInfo;
import com.github.tomakehurst.wiremock.junit5.WireMockTest;


import jakarta.transaction.Transactional;

@WireMockTest
@ActiveProfiles("comp-test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class UserTypeControllerComponentTest extends AbstractContainer {
	
	@Autowired
	private UserTypeController userTypeController;
	
	@Autowired
	private RestaurantWebFluxGateway restauranteWebFluxGateway;
	
	@Autowired
	private UserEntityRepository userEntityRepository;
	
	@Autowired
	private UserTypeEntityRepository userTypeEntityRepository;
	
	@Test
	@DisplayName("Deve criar 'Dono 'com sucesso")
	@Transactional
	void test1(WireMockRuntimeInfo wireMockRuntimeInfo) {
		
		final var idRestaurante = 1L;
		final var tipoUsuarioJson = new UserTypeJson(null, "any-tipo-nome", UserType.OWNER);
		
		final var baseUrl = wireMockRuntimeInfo.getHttpBaseUrl();
		
		setField(restauranteWebFluxGateway, "baseUrl", baseUrl);
		
		var tipoDono = new UserTypeEntity(null, "any-tipo-dono-nome", 0);
		var usuarioExistente = new UserEntity(null, "any-usuario-nome", tipoDono);
		var idUsuarioLogado = userEntityRepository.save(usuarioExistente).getId();

		final var restaurantesResponseBodyMockStr = getRestaurantesResponse();
		stubFor(get("/usuarios/" + idUsuarioLogado + "/restaurantes").willReturn(okJson(restaurantesResponseBodyMockStr)));

		
		assertEquals(0, userTypeEntityRepository.count());
		
		var novoTipoId = userTypeController.criar(idUsuarioLogado, idRestaurante, tipoUsuarioJson);
		
		assertEquals(1, userTypeEntityRepository.count());
		assertEquals(idUsuarioLogado, novoTipoId);

	}

	private String getRestaurantesResponse() {
		return """
				[
					{
						"id": 1,
						"nome": "any-restaurante-name"
					}
				]
				""";
	}
}
