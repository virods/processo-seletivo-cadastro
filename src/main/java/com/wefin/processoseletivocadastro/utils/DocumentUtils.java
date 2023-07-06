package com.wefin.processoseletivocadastro.utils;

import com.wefin.processoseletivocadastro.dto.user.UserRequest;

public class DocumentUtils {

    private static final String CPF =  "Pessoa Física";
    private static final String CNPJ = "Pessoa Jurídica";

    public static String resolvePersonType(UserRequest user) {
        var document = user.getDocument();

        if (document == null) {
            throw new IllegalArgumentException();
        }

        return switch (document.length()) {
            case 11 -> isValidCPF(user);
            case 14 -> isValidCNPJ(user);
            default -> throw new IllegalArgumentException("Valor do documento deve ser preenchido");
        };
    }

    private static String isValidCPF(UserRequest user) {
        var cpf = user.getDocument();
        if (cpf.length() != 11 || cpf.chars().allMatch(n -> n == cpf.charAt(0))) {
            throw new IllegalArgumentException("CPF Inválido");
        }

        String digits = cpf.substring(0, 9);
        String verifiers = cpf.substring(9, 11);

        String calculatedVerifiers = calculateVerifierDigits(digits, new int[][]{
                {10, 9, 8, 7, 6, 5, 4, 3, 2},
                {11, 10, 9, 8, 7, 6, 5, 4, 3, 2}});
        var isValid = verifiers.equals(calculatedVerifiers);
        if(isValid){
            return CPF;
        } else {
            throw new IllegalArgumentException("CPF Inválido");
        }
    }

    private static String isValidCNPJ(UserRequest user) {
        var cnpj = user.getDocument();
        if (cnpj.length() != 14 || cnpj.chars().allMatch(n -> n == cnpj.charAt(0))) {
            throw new IllegalArgumentException("CNPJ Inválido");
        }

        String digits = cnpj.substring(0, 12);
        String verifiers = cnpj.substring(12, 14);

        String calculatedVerifiers = calculateVerifierDigits(digits, new int[][]{
                {5, 4, 3, 2, 9, 8, 7, 6, 5, 4, 3, 2},
                {6, 5, 4, 3, 2, 9, 8, 7, 6, 5, 4, 3, 2}});
        var isValid = verifiers.equals(calculatedVerifiers);
        if(isValid){
            return CNPJ;
        } else {
            throw new IllegalArgumentException("CNPJ Inválido");
        }

    }

    private static String calculateVerifierDigits(String digits, int[][] weights) {
        String firstVerifierDigit = String.valueOf(calculateVerifierDigit(digits, weights[0]));
        String secondVerifierDigit = String.valueOf(calculateVerifierDigit(digits + firstVerifierDigit, weights[1]));

        return firstVerifierDigit + secondVerifierDigit;
    }

    private static int calculateVerifierDigit(String digits, int[] weights) {
        int sum = 0;
        for (int i = 0; i < digits.length(); i++) {
            sum += Character.getNumericValue(digits.charAt(i)) * weights[i];
        }
        sum = 11 - (sum % 11);
        return sum >= 10 ? 0 : sum;
    }
}
