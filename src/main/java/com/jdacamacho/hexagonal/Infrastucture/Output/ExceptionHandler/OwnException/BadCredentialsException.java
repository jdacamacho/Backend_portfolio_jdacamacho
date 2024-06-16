package com.jdacamacho.hexagonal.Infrastucture.Output.ExceptionHandler.OwnException;

import com.jdacamacho.hexagonal.Infrastucture.Output.ExceptionHandler.ExceptionStructure.ErrorCode;

import lombok.Getter;

@Getter
public class BadCredentialsException extends RuntimeException{
    
    private final String messageKey;
    private final String code;

    public BadCredentialsException(ErrorCode code){
        super(code.getCode());
        this.messageKey = code.getMessageKey();
        this.code = code.getCode();
    }

    public BadCredentialsException(final String message){
        super(message);
        this.messageKey = ErrorCode.BAD_CREDENTIALS.getMessageKey();
        this.code = ErrorCode.BAD_CREDENTIALS.getCode();
    }
}
