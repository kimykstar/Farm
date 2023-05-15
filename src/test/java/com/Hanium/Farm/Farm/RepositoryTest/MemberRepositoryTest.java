package com.Hanium.Farm.Farm.RepositoryTest;

import Repository.MemberRepository;
import Repository.MemberRepositoryInterface;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.assertj.core.api.Assertions.assertThat;

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
