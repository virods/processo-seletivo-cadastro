package com.wefin.processoseletivocadastro.utils;

import com.wefin.processoseletivocadastro.dto.user.UserRequest;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class DocumentUtilsTest {

    private static final String PF = "Pessoa Física";
    private static final String PJ = "Pessoa Jurídica";

    private UserRequest createUserDto(String document, String name, Integer age, String email, String phone, String gender) {
        return UserRequest.builder()
                .document(document)
                .name(name)
                .age(age)
                .email(email)
                .phone(phone)
                .gender(gender)
                .build();
    }

    @Test
    public void testValidCPF() {
        UserRequest user = createUserDto("46125848809","John Doe", 30, "john.doe@gmail.com", "11111111111", "Male");
        assertEquals(PF, DocumentUtils.resolvePersonType(user));
    }

    @Test
    public void testInvalidCPF() {
        UserRequest user = createUserDto("11111111111",  "John Doe", 30, "john.doe@gmail.com", "11111111111", "Male");
        assertThrows(IllegalArgumentException.class, () -> DocumentUtils.resolvePersonType(user));
    }

    @Test
    public void testValidCNPJ() {
        UserRequest user = createUserDto("12345678000195",  "Doe Inc.", 1, "doe.inc@gmail.com", "22222222222", "");
        assertEquals(PJ, DocumentUtils.resolvePersonType(user));
    }

    @Test
    public void testInvalidCNPJ() {
        UserRequest user = createUserDto("11111111000111",  "Doe Inc.", 1, "doe.inc@gmail.com", "22222222222", "");
        assertThrows(IllegalArgumentException.class, () -> DocumentUtils.resolvePersonType(user));
    }

    @Test
    public void testEmptyDocument() {
        UserRequest user = createUserDto("",  "Doe Inc.", 1, "doe.inc@gmail.com", "22222222222", "");
        assertThrows(IllegalArgumentException.class, () -> DocumentUtils.resolvePersonType(user));
    }

    @Test
    public void testNullDocument() {
        UserRequest user = createUserDto(null,  "Doe Inc.", 1, "doe.inc@gmail.com", "22222222222", "");
        assertThrows(IllegalArgumentException.class, () -> DocumentUtils.resolvePersonType(user));
    }
}

