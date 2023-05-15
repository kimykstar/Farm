package com.Hanium.Farm.Farm.Controller;

import com.Hanium.Farm.Farm.Service.MemberService;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RestController
public class TestController {
    private final MemberService memberService;
    public TestController(MemberService memberService){
        this.memberService = memberService;
    }

    @GetMapping(value = "/")
    public String home(){
        return "home";
    }
    @RequestMapping(value = "/test")
    public String getPWTest(@RequestParam String id, Model model){
        String result = memberService.getPw(id);
        return result;
    }
}
