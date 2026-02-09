package com.Hanium.Farm.Farm.Controller;

import com.Hanium.Farm.Farm.Dto.LoginRequest;
import com.Hanium.Farm.Farm.Service.MemberService;
import com.Hanium.Farm.Farm.Dto.AuthTokens;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;
import java.util.stream.Stream;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(MemberController.class)
public class MemberControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private MemberService memberService;

    @Test
    void 유저_로그인_성공() throws Exception {
        given(memberService.login(any(), any()))
                .willReturn(Optional.of(new AuthTokens("access", "refresh")));

        mockMvc.perform(post("/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content("""
                  {
                    "id": "kimyogun78@naver.com",
                    "pw": "dffd6021bb2bd5b0af676290809ec3a53191dd81c7f70a4b28688a362182986f"
                  }
                """))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.accessToken").exists())
                .andExpect(jsonPath("$.refreshToken").exists())
                .andExpect(jsonPath("$.accessToken").isNotEmpty())
                .andExpect(jsonPath("$.refreshToken").isNotEmpty()
                );
    }

    @Test
    void 유저_로그인_실패() throws Exception {
        given(memberService.login(any(), any()))
                .willReturn(Optional.empty());

        mockMvc.perform(post("/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content("""
                  {
                      "id": "kimyogun78",
                      "pw": "dffd6021bb2bd5b0af676290809ec3a53191dd81c7f70a4b28688a362182986f"
                  }
                """))
                .andExpect(status().isUnauthorized())
                .andExpect(jsonPath("$.message").exists())
                .andExpect(jsonPath("$.message").value("아이디 혹은 비밀번호가 올바르지 않습니다."));
    }

    @ParameterizedTest
    @MethodSource("invalidLoginParams")
    void 유저_로그인_파라미터_실패(String id, String pw) throws Exception {
        LoginRequest request = new LoginRequest(id, pw);

        String jsonRequest = objectMapper.writeValueAsString(request);

        mockMvc.perform(post("/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonRequest))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message").exists());
    }

    private static Stream<Arguments> invalidLoginParams() {
        return Stream.of(
                Arguments.of("kimyogun78@naver.com", null),
                Arguments.of(null, "dffd6021bb2bd5b0af676290809ec3a53191dd81c7f70a4b28688a362182986f"),
                Arguments.of("", "dffd6021bb2bd5b0af676290809ec3a53191dd81c7f70a4b28688a362182986f"),
                Arguments.of("kimyogun78@naver.com", "")
        );
    }


}