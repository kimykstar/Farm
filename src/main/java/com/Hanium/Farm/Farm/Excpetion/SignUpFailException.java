package com.Hanium.Farm.Farm.Excpetion;

import com.Hanium.Farm.Farm.Enums.ErrorMessage;

public class SignUpFailException extends RuntimeException {
    public SignUpFailException(ErrorMessage message) {
        super(message.getMessage());
    }
}
