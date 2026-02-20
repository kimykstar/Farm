package com.Hanium.Farm.Farm.Advices;

import com.Hanium.Farm.Farm.Dto.ExpiredTokenResponse;
import com.Hanium.Farm.Farm.Dto.TokenNotExistResponse;
import com.Hanium.Farm.Farm.Enums.ErrorMessage;
import com.Hanium.Farm.Farm.Excpetion.TokenNotExistException;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.security.SignatureException;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Order(Ordered.HIGHEST_PRECEDENCE)
@RestControllerAdvice
public class AuthAdvice {

    @ExceptionHandler(ExpiredJwtException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ExpiredTokenResponse handleExpiredTokenException() {
        return new ExpiredTokenResponse(ErrorMessage.ACCESS_TOKEN_EXPIRED.getMessage());
    }

    @ExceptionHandler(SignatureException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ExpiredTokenResponse handleSignatureException() {
        return new ExpiredTokenResponse(ErrorMessage.INVALID_SIGNATURE.getMessage());
    }

    @ExceptionHandler(TokenNotExistException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public TokenNotExistResponse handleTokenNotExistException() {
        return new TokenNotExistResponse(ErrorMessage.TOKEN_IS_NOT_EXIST.getMessage());
    }

}
