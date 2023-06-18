package com.Hanium.Farm.Farm;

import com.Hanium.Farm.Farm.Repository.*;
import com.Hanium.Farm.Farm.Service.FruitService;
import com.Hanium.Farm.Farm.Service.MemberService;
import com.Hanium.Farm.Farm.Service.NutritionService;
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

    @Bean
    public FruitRepositoryInterface fruitRepositoryInterface(){return new FruitRepository(dataSource);}

    @Bean
    public FruitService fruitService (){return new FruitService(fruitRepositoryInterface());}

    @Bean
    public NutritionInterface nutritionInterface(){return new NutritionRepository(dataSource);}

    @Bean
    public NutritionService nutritionService(){return new NutritionService(nutritionInterface());}
}
