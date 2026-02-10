package com.Hanium.Farm.Farm.Excpetion;

import com.Hanium.Farm.Farm.Enums.ErrorMessage;
import lombok.Getter;

@Getter
public class LoginFailException extends RuntimeException{
    public LoginFailException(ErrorMessage message) {
        super(message.getMessage());
    }
}
