package com.wefin.processoseletivocadastro.controller;

import com.wefin.processoseletivocadastro.dto.api.ErrorResponse;
import com.wefin.processoseletivocadastro.dto.api.UserCreateResponse;
import com.wefin.processoseletivocadastro.dto.user.UserRequest;
import com.wefin.processoseletivocadastro.dto.user.UserResponse;
import com.wefin.processoseletivocadastro.service.UserService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
@Tag(name = "Cadastro de usuários", description = "API de cadastro de usuários PF ou PJ")
public class UserController {

    private final UserService userService;

    @PostMapping
    public ResponseEntity<UserCreateResponse> createUser(@RequestBody UserRequest user) {
        try {
            UserResponse userResponse = userService.createUser(user);
            UserCreateResponse userCreateResponse = UserCreateResponse.builder().data(userResponse).build();
            return ResponseEntity.ok(userCreateResponse);
        }
        catch (IllegalArgumentException e){
            ErrorResponse errorResponse = ErrorResponse.builder().message(e.getMessage()).build();
            return new ResponseEntity<>( UserCreateResponse.builder().data(errorResponse).build(), HttpStatusCode.valueOf(400));
        }
        catch (RuntimeException e) {
            ErrorResponse errorResponse = ErrorResponse.builder().message(e.getMessage()).build();
            return new ResponseEntity<>( UserCreateResponse.builder().data(errorResponse).build(), HttpStatusCode.valueOf(500));
        }
    }
}