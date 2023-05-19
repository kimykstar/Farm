package com.Hanium.Farm.Farm.RepositoryTest;

import com.Hanium.Farm.Farm.Repository.MemberRepository;
import com.Hanium.Farm.Farm.Repository.MemberRepositoryInterface;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class MemberRepositoryTest {

    @Autowired MemberRepositoryInterface memberRepository;
    @Test
    void 비번가져오기(){
        String id="user";
        String pw="user";
        String result = memberRepository.getPwHash(id);

        assertThat(result).isEqualTo(pw);
    }
}
