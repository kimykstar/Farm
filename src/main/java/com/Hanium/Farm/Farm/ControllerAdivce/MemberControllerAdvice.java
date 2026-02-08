package com.Hanium.Farm.Farm.ControllerAdivce;

import com.Hanium.Farm.Farm.Dto.LoginFailResponse;
import com.Hanium.Farm.Farm.Excpetion.LoginFailException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class MemberControllerAdvice {

    @ExceptionHandler(LoginFailException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public LoginFailResponse handleLoginFail(LoginFailException e) {
        return new LoginFailResponse(e.getMessage());
    }

}
