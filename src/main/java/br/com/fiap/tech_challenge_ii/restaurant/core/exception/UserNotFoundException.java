package br.com.fiap.tech_challenge_ii.restaurant.core.exception;

public class UserNotFoundException extends SystemBaseException {
    public UserNotFoundException(String formatted) {
        super(formatted);
    }
}
