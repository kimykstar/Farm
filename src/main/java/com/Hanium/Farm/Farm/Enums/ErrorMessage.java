package com.Hanium.Farm.Farm.Enums;

public enum ErrorMessage {
    LOGIN_FAIL("아이디 혹은 비밀번호가 올바르지 않습니다."),
    SIGNUP_FAIL("회원가입에 실패했습니다.");

    private final String message;

    ErrorMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
