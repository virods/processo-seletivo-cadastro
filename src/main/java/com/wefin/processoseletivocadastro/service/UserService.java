package com.wefin.processoseletivocadastro.service;

import com.wefin.processoseletivocadastro.dto.UserRequest;
import com.wefin.processoseletivocadastro.dto.UserResponse;
import com.wefin.processoseletivocadastro.model.User;
import com.wefin.processoseletivocadastro.repository.UserRepository;
import com.wefin.processoseletivocadastro.utils.DocumentUtils;
import com.wefin.processoseletivocadastro.utils.constants.Messages;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public UserResponse createUser(UserRequest userRequest) throws IllegalArgumentException {
       var personType = DocumentUtils.resolvePersonType(userRequest);
       User user = userRepository.save(User.builder()
                .age(userRequest.getAge())
                .document(userRequest.getDocument())
                .email(userRequest.getEmail())
                .gender(userRequest.getGender())
                .name(userRequest.getGender())
                .phone(userRequest.getPhone())
                .personType(personType)
                .build());

       return UserResponse.builder().message(Messages.SUCCESS_CREATE).personType(user.getPersonType()).build();
    }
}