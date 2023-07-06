package com.wefin.processoseletivocadastro.dto.api;


import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ErrorResponse {
    private String message;
}
