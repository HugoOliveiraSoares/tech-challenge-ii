package br.com.fiap.tech_challenge_ii.user.infra.controller;

import br.com.fiap.tech_challenge_ii.user.core.domain.User;
import br.com.fiap.tech_challenge_ii.user.core.usecase.CreateTypeUserUsecase;
import br.com.fiap.tech_challenge_ii.user.infra.controller.json.TypeUserJson;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/type")
@RequiredArgsConstructor
public class TypeUserController {

    @Autowired
    private CreateTypeUserUsecase createTypeUserUsecase;

    @PostMapping
    public ResponseEntity<Long> create(@RequestHeader Long loggedInUserId, Long restaurantId,
                                 @RequestBody TypeUserJson typeUserJson) {
        User newUser = new User();
        newUser.setId(typeUserJson.getId());
        newUser.setName(typeUserJson.getName());
        newUser.setUserType(typeUserJson.getType());

     return ResponseEntity.status(HttpStatus.CREATED).body(createTypeUserUsecase.create(loggedInUserId, restaurantId, newUser));


    }


}
