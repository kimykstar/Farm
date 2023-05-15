package com.Hanium.Farm.Farm;

import com.Hanium.Farm.Farm.Repository.MemberRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.assertj.core.api.Assertions.assertThat;

public class ServiceTest {
    @Autowired MemberRepository memberRepository;

    @Test
    void login(){
        String id = "user";
        String pw = "user";

        String result = memberRepository.getPwHash(id);

        assertThat(result).isEqualTo(pw);

    }
}
