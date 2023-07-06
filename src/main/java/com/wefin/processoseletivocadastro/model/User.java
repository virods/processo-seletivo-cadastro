package com.wefin.processoseletivocadastro.model;

import lombok.Builder;
import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "users")
@Getter
@Builder
public class User {
    @Id
    private String id;
    private String document;
    private String personType;
    private String name;
    private int age;
    private String email;
    private String phone;
    private String gender;
}
