package com.Hanium.Farm.Farm.Excpetion;

import com.Hanium.Farm.Farm.Enums.ErrorMessage;

public class TokenNotExistException extends RuntimeException{
    public TokenNotExistException(ErrorMessage message) {
        super(message.getMessage());
    }
}
