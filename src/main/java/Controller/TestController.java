package Controller;

import Service.MemberService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
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
        model.addAttribute("result : ", result);
        return "result";
    }
}
