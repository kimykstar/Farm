package com.Hanium.Farm.Farm.Controller;

import com.Hanium.Farm.Farm.Service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MemberController {
    MemberService memberService;
    @Autowired
    public MemberController(MemberService memberService){
        this.memberService = memberService;
    }
    @GetMapping(value = "login")
    @ResponseBody
    public boolean login(@RequestParam("id") String id, @RequestParam("pw") String pw){
        boolean result = false;
        // 여기서 memberService이용
        try{
            memberService.login(id, pw);
        }catch(Exception e){
            e.printStackTrace();
        }

        return result;
    }

}
