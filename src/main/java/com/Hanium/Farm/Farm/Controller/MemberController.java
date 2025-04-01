package com.Hanium.Farm.Farm.Controller;

import com.Hanium.Farm.Farm.Domain.Member;
import com.Hanium.Farm.Farm.Service.MemberService;
import jakarta.servlet.ServletInputStream;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.*;

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
        ServletInputStream inputStream = request.getInputStream();

        String messageBody = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);
        String[] user = messageBody.split(" ");
        String id = user[0];
        String pw = user[1];


        boolean result = false;
        try{
            result = memberService.login(id, pw);
            if(result){
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

    @PostMapping(value = "join")
    public boolean join(HttpServletRequest request) throws IOException{
        ServletInputStream inputStream = request.getInputStream();

        String messageBody = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);
        String[] user = messageBody.split(" ");
        String id = user[0];
        String pw = user[1];

        String name = user[2];
        String phone = user[3];

        int age = Integer.parseInt(user[4]);

        Member m = new Member(id, pw, name, phone, age);

        try{
            memberService.join(m);
        }catch(Exception e){
            e.printStackTrace();
        }

        return true;
    }

    @GetMapping(value="/delete")
    public boolean delete(@RequestParam String id){
        return memberService.delete(id);
    }

}
