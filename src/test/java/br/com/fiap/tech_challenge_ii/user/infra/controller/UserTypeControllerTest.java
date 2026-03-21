package br.com.fiap.tech_challenge_ii.user.infra.controller;

import br.com.fiap.tech_challenge_ii.user.core.usecase.CreateUserTypeUseCase;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;




@ExtendWith(MockitoExtension.class)
class UserTypeControllerTest {

	@InjectMocks
    private UserTypeController userTypeController;
	
	@Mock
    private CreateUserTypeUseCase createUserTypeUseCase;
	
	@Test
	void test() {
		//TODO: implementar
		
		
		
	}

}
