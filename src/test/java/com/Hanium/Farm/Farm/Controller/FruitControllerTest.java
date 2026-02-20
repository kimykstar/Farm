package com.Hanium.Farm.Farm.Controller;

import com.Hanium.Farm.Farm.Advices.AuthAdvice;
import com.Hanium.Farm.Farm.Components.JwtProvider;
import com.Hanium.Farm.Farm.Interceptors.TokenInterceptor;
import com.Hanium.Farm.Farm.Service.FruitService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
class FruitControllerTest {
    private MockMvc mockMvc;
    @Mock
    private JwtProvider jwtProvider;
    @Mock
    private FruitService fruitService;

    @BeforeEach
    void setUp() {
        FruitController fruitController = new FruitController(fruitService);

        mockMvc = MockMvcBuilders.standaloneSetup(fruitController)
                .setControllerAdvice(new AuthAdvice())
                .addInterceptors(new TokenInterceptor(jwtProvider))
                .build();
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
}