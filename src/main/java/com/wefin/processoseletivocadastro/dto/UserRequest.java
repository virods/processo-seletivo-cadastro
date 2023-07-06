package com.wefin.processoseletivocadastro.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserRequest {
    private String document;
    private String name;
    private int age;
    private String email;
    private String phone;
    private String gender;
}
