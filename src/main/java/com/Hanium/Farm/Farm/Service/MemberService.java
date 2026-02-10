package com.Hanium.Farm.Farm.Service;

import com.Hanium.Farm.Farm.Dto.AuthTokens;
import com.Hanium.Farm.Farm.Dto.LoginRequest;
import com.Hanium.Farm.Farm.Dto.SignUpRequest;
import com.Hanium.Farm.Farm.Repository.MemberRepositoryInterface;
import com.Hanium.Farm.Farm.Vo.Member;

import java.util.Optional;

public class MemberService {
    MemberRepositoryInterface memberRepository;

    public MemberService(MemberRepositoryInterface memberRepository){
        this.memberRepository = memberRepository;
    }

    public Optional<AuthTokens> login(LoginRequest request) {
        Member member = memberRepository.getMember(request.id());
        String hash_pw = member.pw();

        if(request.pw().equals(hash_pw)) {
            String accessToken = "access";
            String refreshToken = "refresh";
            return Optional.of(new AuthTokens(accessToken, refreshToken));
        }

        return Optional.empty();
    }

    public boolean join(SignUpRequest request){
        Member member = new Member(request.id(), request.pw(), request.name(), request.phone(), request.age());
        return memberRepository.join(member);
    }

    public boolean delete(String id){
        boolean result = memberRepository.delete(id);
        return result;
    }
}
