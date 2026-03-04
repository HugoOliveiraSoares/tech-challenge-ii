package br.com.fiap.tech_challenge_ii.user.core.exception;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public class SystemException extends RuntimeException{
    private static final long serialVersionUID = 443911183945646720L;

    private final String code;
    private final String message;
    private final Integer httpStatus;

}
