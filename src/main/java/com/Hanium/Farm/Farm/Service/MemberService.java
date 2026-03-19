package com.Hanium.Farm.Farm.Service;

import com.Hanium.Farm.Farm.Components.JwtProvider;
import com.Hanium.Farm.Farm.Dto.AuthTokens;
import com.Hanium.Farm.Farm.Dto.LoginRequest;
import com.Hanium.Farm.Farm.Dto.SignUpRequest;
import com.Hanium.Farm.Farm.Enums.ErrorMessage;
import com.Hanium.Farm.Farm.Excpetion.LoginFailException;
import com.Hanium.Farm.Farm.Repository.MemberRepositoryInterface;
import com.Hanium.Farm.Farm.Vo.Member;
import org.springframework.stereotype.Service;


@Service
public class MemberService {
    MemberRepositoryInterface memberRepository;
    JwtProvider jwtProvider;

    public MemberService(MemberRepositoryInterface memberRepository, JwtProvider jwtProvider){
        this.memberRepository = memberRepository;
        this.jwtProvider = jwtProvider;
    }

    public AuthTokens login(LoginRequest request) {
        Member member = memberRepository.getMember(request.id());

        if (!request.pw().equals(member.pw())) throw new LoginFailException(ErrorMessage.LOGIN_FAIL);

        String accessToken = jwtProvider.createAccessToken(request.id());
        String refreshToken = jwtProvider.createRefreshToken(request.id());
        // Redis저장 로직 넣어야 함
        
        return new AuthTokens(accessToken, refreshToken);
    }

    public boolean join(SignUpRequest request){
        Member member = new Member(request.id(), request.pw(), request.name(), request.phone(), request.age());
        return memberRepository.join(member);
    }

    public boolean delete(String id){
        boolean result = memberRepository.delete(id);
        return result;
    }

    public AuthTokens refreshToken(String refreshToken) {
        if (!jwtProvider.validateToken(refreshToken)) {
            throw new LoginFailException(ErrorMessage.LOGIN_FAIL);
        }
        String userId = jwtProvider.getUserId(refreshToken);
        String newAccessToken = jwtProvider.createAccessToken(userId);

        return new AuthTokens(newAccessToken, refreshToken);
    }
}
