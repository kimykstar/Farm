package com.Hanium.Farm.Farm.Configurations;

import com.Hanium.Farm.Farm.Repository.*;
import com.Hanium.Farm.Farm.Service.CommunityService;
import com.Hanium.Farm.Farm.Service.FruitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import javax.sql.DataSource;

@Configuration
public class SpringConfig {
    private final DataSource dataSource;
    @Value("${spring.data.redis.host}")
    private String redisHost;
    @Value("${spring.data.redis.port}")
    private int redisPort;

    @Autowired
    public SpringConfig(DataSource dataSource){
        this.dataSource = dataSource;
    }

    @Bean
    public MemberRepositoryInterface memberRepositoryInterface(){
        return new MemberRepository(dataSource);
    }

    @Bean
    public FruitRepositoryInterface fruitRepositoryInterface(){return new FruitRepository(dataSource);}

    @Bean
    public FruitService fruitService (){return new FruitService(fruitRepositoryInterface());}

    @Bean
    public CommunityRepositoryInterface communityRepositoryInterface(){return new CommunityRepository(dataSource);}
    @Bean
    public CommunityService communityService(){return new CommunityService(communityRepositoryInterface());}

    @Bean
    LettuceConnectionFactory connectionFactory() {
        return new LettuceConnectionFactory(this.redisHost, this.redisPort);
    }

    @Bean
    RedisTemplate<String, String> redisTemplate(RedisConnectionFactory connectionFactory) {
        RedisTemplate<String, String> template = new RedisTemplate<>();
        template.setConnectionFactory(connectionFactory);

        template.setKeySerializer(new StringRedisSerializer());
        template.setValueSerializer(new GenericJackson2JsonRedisSerializer());

        return template;
    }
}
