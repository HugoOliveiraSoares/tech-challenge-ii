package br.com.fiap.tech_challenge_ii.restaurant.core.exception;

public class UnauthorizedOperationException extends SystemBaseException {
    public UnauthorizedOperationException(String message) {
        super(message);
    }
}
