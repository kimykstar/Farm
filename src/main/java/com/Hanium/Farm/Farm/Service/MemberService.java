package com.Hanium.Farm.Farm.Service;

import com.Hanium.Farm.Farm.Domain.Member;
import com.Hanium.Farm.Farm.Dto.AuthTokens;
import com.Hanium.Farm.Farm.Repository.MemberRepositoryInterface;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Optional;

public class MemberService {
    MemberRepositoryInterface memberRepository;

    @Autowired
    public MemberService(MemberRepositoryInterface memberRepository){
        this.memberRepository = memberRepository;
    }

    public Optional<AuthTokens> login(String id, String pw) {
        Member member = memberRepository.getMember(id);
        String hash_pw = member.getPw();

        if(pw.equals(hash_pw)) {
            String accessToken = "access";
            String refreshToken = "refresh";
            return Optional.of(new AuthTokens(accessToken, refreshToken));
        }

        return Optional.empty();
    }

    public void join(Member member){
//        boolean result = false;
        memberRepository.join(member);
//        return result;
    }

    public String getPw(String id){
        String pw;
        Member member = memberRepository.getMember(id);
        pw = member.getPw();
        return pw;
    }

    public boolean delete(String id){
        boolean result = memberRepository.delete(id);
        return result;
    }
}
