package com.hussard.hsauth.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hussard.hsauth.application.service.UserService;
import com.hussard.hsauth.domain.entity.User;
import com.hussard.hsauth.domain.repository.UserRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@AutoConfigureMockMvc
class UserControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @DisplayName("유저 한명 조회 테스트")
    void testGetUser() throws Exception {
        // given
        long userId = 1;
        User user = User.builder()
                .id(1)
                .username("켄트 벡")
                .password("1234")
                .build();
        userRepository.save(user);
        // when // then
        mvc.perform(get("/users/{id}", userId)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    @DisplayName("유저 한명 저장 테스트")
    void testSaveUser() throws Exception {
        // given
        User user = User.builder()
                .username("켄트 벡")
                .password("1234")
                .build();
        // when // then
        mvc.perform(post("/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(user)))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    @DisplayName("유저 한명 저장 실패 테스트")
    void testSaveUserException() throws Exception {
        // given
        long userId = 99999999L;

        // when // then
        mvc.perform(get("/users/{id}", userId)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is4xxClientError())
                .andDo(print());
    }
}