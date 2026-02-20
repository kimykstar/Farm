package com.Hanium.Farm.Farm.Advices;

import com.Hanium.Farm.Farm.Controller.MemberController;
import com.Hanium.Farm.Farm.Dto.LoginFailResponse;
import com.Hanium.Farm.Farm.Excpetion.LoginFailException;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Order(Ordered.HIGHEST_PRECEDENCE)
@RestControllerAdvice(assignableTypes = {MemberController.class})
public class MemberControllerAdvice {

    @ExceptionHandler(LoginFailException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public LoginFailResponse handleLoginFail(LoginFailException e) {
        return new LoginFailResponse(e.getMessage());
    }
}
