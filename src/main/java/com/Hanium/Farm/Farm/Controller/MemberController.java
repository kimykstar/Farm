package com.Hanium.Farm.Farm.Controller;

import com.Hanium.Farm.Farm.Service.MemberService;
import jakarta.servlet.ServletInputStream;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
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
    public boolean login(HttpServletRequest request, HttpSession session) throws IOException {
        // Http의 Body부분에서 데이터를 받는다.
        ServletInputStream inputStream = request.getInputStream();

        String messageBody = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);
        String[] user = messageBody.split(" ");
        String id = user[0];
        String pw = user[1];


        boolean result = false;
        // 여기서 memberService이용
        try{
            result = memberService.login(id, pw);
            if(result == true){
                session.setAttribute("user", id);
                System.out.println(session.getAttribute("user"));
            }else{
                session.invalidate();
            }
        }catch(Exception e){
            e.printStackTrace();
        }

        return result;
    }

}
