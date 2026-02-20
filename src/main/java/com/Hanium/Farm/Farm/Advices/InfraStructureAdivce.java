package com.Hanium.Farm.Farm.Advices;

import com.Hanium.Farm.Farm.Dto.SignUpFailResponse;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Order(Ordered.HIGHEST_PRECEDENCE)
@RestControllerAdvice(basePackages="com.Hanium.Farm.Farm.Repository")
public class InfraStructureAdivce {

    @ExceptionHandler(DataAccessException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public SignUpFailResponse databaseProcessHandler(DataAccessException e) {
        return new SignUpFailResponse(e.getMessage());
    }
}
