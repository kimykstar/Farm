package com.Hanium.Farm.Farm.Service;

import com.Hanium.Farm.Farm.Components.JwtProvider;
import com.Hanium.Farm.Farm.Dto.Auth.AuthTokens;
import com.Hanium.Farm.Farm.Dto.Auth.LoginRequest;
import com.Hanium.Farm.Farm.Dto.Auth.SignUpRequest;
import com.Hanium.Farm.Farm.Enums.ErrorMessage;
import com.Hanium.Farm.Farm.Excpetion.LoginFailException;
import com.Hanium.Farm.Farm.Repository.MemberRepositoryInterface;
import com.Hanium.Farm.Farm.Vo.Member;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;


@Service
public class MemberService {
    MemberRepositoryInterface memberRepository;
    JwtProvider jwtProvider;
    RedisTemplate<String, String> redisTemplate;

    public MemberService(MemberRepositoryInterface memberRepository, JwtProvider jwtProvider, RedisTemplate<String, String> redisTemplate){
        this.memberRepository = memberRepository;
        this.jwtProvider = jwtProvider;
        this.redisTemplate = redisTemplate;
    }

    public AuthTokens login(LoginRequest request) {
        Member member = memberRepository.getMember(request.id());

        if (!request.pw().equals(member.pw())) throw new LoginFailException(ErrorMessage.LOGIN_FAIL);

        String accessToken = jwtProvider.createAccessToken(request.id());
        String refreshToken = jwtProvider.createRefreshToken(request.id());

        redisTemplate.opsForValue().set(request.id(), refreshToken);
        
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
