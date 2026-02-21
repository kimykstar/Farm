package com.Hanium.Farm.Farm.Interceptors;

import com.Hanium.Farm.Farm.Components.JwtProvider;
import com.Hanium.Farm.Farm.Enums.ErrorMessage;
import com.Hanium.Farm.Farm.Excpetion.TokenNotExistException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
@RequiredArgsConstructor
public class TokenInterceptor implements HandlerInterceptor {

    private final JwtProvider jwtProvider;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        String accessToken = request.getHeader("Authorization");

        if (accessToken == null) throw new TokenNotExistException(ErrorMessage.TOKEN_IS_NOT_EXIST);

        return jwtProvider.validateToken(accessToken);
    }

}
