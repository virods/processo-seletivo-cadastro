package com.wefin.processoseletivocadastro.dto;


import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ErrorResponse {
    private String message;
}
