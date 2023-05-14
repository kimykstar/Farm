package Service;

import Repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class MemberService {
    MemberRepository memberRepository;

    @Autowired
    public MemberService(MemberRepository memberRepository){
        this.memberRepository = memberRepository;
    }

    public boolean login(String id, String pw){
        boolean result = false;
        // 여기 memberRepository이용
        return result;
    }

}
