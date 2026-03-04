package br.com.fiap.tech_challenge_ii.user.core.exception;

public class UserNotFoundException extends SystemException{

    private static final String code = "usuario.usuarioNaoEncontrado";
    private static final String message = "Usuário não foi encontrado";
    private static final Integer httpStatus = 404;

    public UserNotFoundException(){
        super(code, message, httpStatus);
    }
}
