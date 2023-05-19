package com.Hanium.Farm.Farm;

import com.Hanium.Farm.Farm.Repository.MemberRepository;
import com.Hanium.Farm.Farm.Repository.MemberRepositoryInterface;
import com.Hanium.Farm.Farm.Service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class SpringConfig {
    private final DataSource dataSource;

    @Autowired
    public SpringConfig(DataSource dataSource){
        // dataSource를 spring bean으로 등록
        this.dataSource = dataSource;
    }

    @Bean
    public MemberRepositoryInterface memberRepositoryInteface(){
        return new MemberRepository(dataSource);
    }

    @Bean
    public MemberService memberService(){
        return new MemberService(memberRepositoryInteface());
    }

}
