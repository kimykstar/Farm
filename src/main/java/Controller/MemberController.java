package Controller;

import Domain.Member;
import Service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MemberController {
    MemberService memberService;
    @Autowired
    public MemberController(MemberService memberService){
        this.memberService = memberService;
    }
    @GetMapping(value = "login")
    public boolean login(String id, String password){
        boolean result = false;
        // 여기서 memberService이용
        return result;
    }

}
