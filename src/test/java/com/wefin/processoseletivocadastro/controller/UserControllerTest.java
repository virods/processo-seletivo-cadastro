package com.wefin.processoseletivocadastro.controller;


import com.wefin.processoseletivocadastro.dto.UserRequest;
import com.wefin.processoseletivocadastro.dto.UserResponse;
import com.wefin.processoseletivocadastro.json.JsonHelper;
import com.wefin.processoseletivocadastro.model.User;
import com.wefin.processoseletivocadastro.service.UserService;
import com.wefin.processoseletivocadastro.utils.constants.Messages;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.beans.factory.annotation.Autowired;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.mockito.BDDMockito.given;

@WebMvcTest(UserController.class)
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    private UserResponse user;
    private UserRequest userDTO;

    @BeforeEach
    public void setUp() {
        userDTO = UserRequest.builder()
                .age(1)
                .document("787.880.260-27")
                .email("teste@email.com")
                .gender("Masculino")
                .name("Teste")
                .phone("11973361299")
                .build();
        user = UserResponse.builder()
                .message(Messages.SUCCESS_CREATE)
                .personType("Pessoa Física")
                .build();

        given(userService.createUser(Mockito.any(UserRequest.class))).willReturn(user);
    }

    @Test
    public void testCreateUserSuccess() throws Exception {
        mockMvc.perform(post("/api/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(JsonHelper.asJsonString(userDTO)))
                .andExpect(status().isOk());
    }

    @Test
    public void testCreateUserFailure() throws Exception {
        given(userService.createUser(Mockito.any(UserRequest.class))).willThrow(new RuntimeException());

        mockMvc.perform(post("/api/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(JsonHelper.asJsonString(userDTO)))
                .andExpect(status().isInternalServerError());
    }
    @Test
    public void testCreateUserIllegalArgument() throws Exception {
        given(userService.createUser(Mockito.any(UserRequest.class))).willThrow(new IllegalArgumentException());

        mockMvc.perform(post("/api/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(JsonHelper.asJsonString(userDTO)))
                .andExpect(status().is4xxClientError());
    }
}
