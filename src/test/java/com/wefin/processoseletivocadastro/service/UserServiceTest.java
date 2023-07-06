package com.wefin.processoseletivocadastro.service;

import com.wefin.processoseletivocadastro.dto.user.UserRequest;
import com.wefin.processoseletivocadastro.model.User;
import com.wefin.processoseletivocadastro.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.mockito.BDDMockito.given;

public class UserServiceTest {

    private UserService userService;

    @MockBean
    private UserRepository userRepository;

    private User user;
    private UserRequest userRequest;

    @BeforeEach
    public void setUp() {
        userRepository = Mockito.mock(UserRepository.class);
        userService = new UserService(userRepository);

        userRequest = UserRequest.builder()
                .age(1)
                .document("46125848809")
                .email("teste@email.com")
                .gender("Masculino")
                .name("Teste")
                .phone("11973361299")
                .build();
        user = User.builder()
                .age(1)
                .document("46125848809")
                .email("teste@email.com")
                .gender("Masculino")
                .name("Teste")
                .phone("11973361299")
                .personType("Pessoa Física")
                .build();
    }

    @Test
    public void testCreateUser() {
        given(userRepository.save(Mockito.any(User.class))).willReturn(user);

        Assertions.assertDoesNotThrow(
                () -> {
                    userService.createUser(userRequest);
                }
        );

    }
}
