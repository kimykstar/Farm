package com.Hanium.Farm.Farm.Controller;

import com.Hanium.Farm.Farm.Components.JwtProvider;
import com.Hanium.Farm.Farm.Service.FruitService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import java.security.Key;
import java.util.Date;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
class FruitControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private FruitService fruitService;
    @Value("${spring.jwt.token.secret.key}")
    private String privateKey;
    @Autowired
    JwtProvider jwtProvider;

    private Key key;

    @BeforeEach
    void setUp() {
        this.key = Keys.hmacShaKeyFor(privateKey.getBytes());
    }

    @Test
    void 제외된_경로는_인터셉터_경유하지않는다() throws Exception {
        mockMvc.perform(get("/"))
                .andExpect(status().isNotFound());
    }

    @Test
    void 토큰이_없는_경우_테스트() throws Exception {
        mockMvc.perform(get("/api/search"))
                .andExpect(status().isUnauthorized())
                .andExpect(jsonPath("$.message").value("로그인이 필요합니다."));
    }

    @Test
    void 토큰_만료_테스트() throws Exception {
        Date now = new Date();
        Claims claims = Jwts.claims()
                        .setSubject("farm")
                        .setIssuedAt(new Date(now.getTime() - 1000))
                        .setExpiration(new Date(now.getTime() - 500));
        String expiredAccessToken = Jwts.builder().setClaims(claims)
                            .claim("userId", "111")
                            .signWith(key, SignatureAlgorithm.HS256)
                            .compact();

        mockMvc.perform(get("/api/search")
                .header("Authorization", expiredAccessToken))
                .andExpect(status().isUnauthorized())
                .andExpect(jsonPath("$.message").value("엑세스 토큰이 만료되었습니다."));
    }

    @Test
    void 토큰_무결성_손상_테스트() throws Exception {
        String accessToken = jwtProvider.createAccessToken("user");
        String tamperedToken = accessToken + "hacked";

        mockMvc.perform(get("/api/search")
                .header("Authorization", tamperedToken))
                .andExpect(status().isUnauthorized())
                .andExpect(jsonPath("$.message").value("시그니쳐 무결성 위반"));
    }
}