package com.Hanium.Farm.Farm.Controller;

import com.Hanium.Farm.Farm.Service.MemberService;
import jakarta.servlet.ServletInputStream;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.tags.Param;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

@RestController
public class MemberController {
    MemberService memberService;
    @Autowired
    public MemberController(MemberService memberService){
        this.memberService = memberService;
    }
    @PostMapping(value = "login")
    public boolean login(HttpServletRequest request) throws IOException {
        // Http의 Body부분에서 데이터를 받는다.
        ServletInputStream inputStream = request.getInputStream();

        String messageBody = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);
        String[] user = messageBody.split(" ");
        System.out.println(messageBody);
        System.out.println(request.getProtocol());
        System.out.println(request.getMethod());
        String id = user[0];
        String pw = user[1];
        System.out.println(id);
        System.out.println(pw);

        boolean result = false;
        // 여기서 memberService이용
        try{
            result = memberService.login(id, pw);
        }catch(Exception e){
            e.printStackTrace();
        }

        return result;
    }

}
