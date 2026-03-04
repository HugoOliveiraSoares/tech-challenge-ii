package br.com.fiap.tech_challenge_ii.user.core.exception;

public class ActionNotPermittedException extends SystemException{

    private static final String code = "usuario.acaoNaoPermitida";
    private static final String message = "Ação não permitida";
    private static final Integer httpStatus = 403;

    public ActionNotPermittedException() {
        super(code, message, httpStatus);
    }
}
