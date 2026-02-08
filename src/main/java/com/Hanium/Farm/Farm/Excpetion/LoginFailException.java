package com.Hanium.Farm.Farm.Excpetion;

import lombok.Getter;

@Getter
public class LoginFailException extends RuntimeException{
    private final String message = "아이디 혹은 비밀번호가 올바르지 않습니다.";
}
