package com.Hanium.Farm.Farm.Controller;

import com.Hanium.Farm.Farm.Dto.*;
import com.Hanium.Farm.Farm.Enums.ErrorMessage;
import com.Hanium.Farm.Farm.Excpetion.LoginFailException;
import com.Hanium.Farm.Farm.Excpetion.SignUpFailException;
import com.Hanium.Farm.Farm.Service.MemberService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
public class MemberController {
    MemberService memberService;

    public MemberController(MemberService memberService){
        this.memberService = memberService;
    }

    @PostMapping(value = "/login")
    public ResponseEntity<LoginResponse> login(@Valid @RequestBody LoginRequest request) {
        AuthTokens tokens = memberService.login(request)
                .orElseThrow(() -> new LoginFailException(ErrorMessage.LOGIN_FAIL));

        return ResponseEntity.ok(
                new LoginResponse(
                        tokens.accessToken(),
                        tokens.refreshToken()
                )
        );
    }

    @PostMapping(value = "/join")
    public ResponseEntity<SignUpResponse> join(@Valid @RequestBody SignUpRequest request) {
        boolean result = memberService.join(request);

        if (result) {
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(new SignUpResponse("회원가입에 성공했습니다."));
        }

        throw new SignUpFailException(ErrorMessage.SIGNUP_FAIL);
    }

    @GetMapping(value="/delete")
    public boolean delete(@RequestParam String id){
        return memberService.delete(id);
    }

}
