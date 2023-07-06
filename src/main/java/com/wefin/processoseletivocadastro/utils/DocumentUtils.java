package com.wefin.processoseletivocadastro.utils;

import com.wefin.processoseletivocadastro.dto.user.UserRequest;
import com.wefin.processoseletivocadastro.utils.constants.Messages;

public class DocumentUtils {

    public static String resolvePersonType(final UserRequest user) {
        var document = user.getDocument();

        if (document == null) {
            throw new IllegalArgumentException();
        }

        return switch (document.length()) {
            case 11 -> isValidCPF(user);
            case 14 -> isValidCNPJ(user);
            default -> throw new IllegalArgumentException(Messages.INVALID_DOCUMENT);
        };
    }

    private static String isValidCPF(final UserRequest user) {
        var cpf = user.getDocument();
        if (cpf.length() != 11 || cpf.chars().allMatch(n -> n == cpf.charAt(0))) {
            throw new IllegalArgumentException(Messages.INVALID_CPF);
        }

        String digits = cpf.substring(0, 9);
        String verifiers = cpf.substring(9, 11);

        String calculatedVerifiers = calculateVerifierDigits(digits, new int[][]{
                {10, 9, 8, 7, 6, 5, 4, 3, 2},
                {11, 10, 9, 8, 7, 6, 5, 4, 3, 2}});
        var isValid = verifiers.equals(calculatedVerifiers);
        if(isValid){
            return Messages.CPF;
        } else {
            throw new IllegalArgumentException(Messages.INVALID_CPF);
        }
    }

    private static String isValidCNPJ(final UserRequest user) {
        var cnpj = user.getDocument();
        if (cnpj.length() != 14 || cnpj.chars().allMatch(n -> n == cnpj.charAt(0))) {
            throw new IllegalArgumentException(Messages.INVALID_CNPJ);
        }

        String digits = cnpj.substring(0, 12);
        String verifiers = cnpj.substring(12, 14);

        String calculatedVerifiers = calculateVerifierDigits(digits, new int[][]{
                {5, 4, 3, 2, 9, 8, 7, 6, 5, 4, 3, 2},
                {6, 5, 4, 3, 2, 9, 8, 7, 6, 5, 4, 3, 2}});
        var isValid = verifiers.equals(calculatedVerifiers);
        if(isValid){
            return Messages.CNPJ;
        } else {
            throw new IllegalArgumentException(Messages.INVALID_CNPJ);
        }

    }

    private static String calculateVerifierDigits(final String digits, final int[][] weights) {
        String firstVerifierDigit = String.valueOf(calculateVerifierDigit(digits, weights[0]));
        String secondVerifierDigit = String.valueOf(calculateVerifierDigit(digits + firstVerifierDigit, weights[1]));

        return firstVerifierDigit + secondVerifierDigit;
    }

    private static int calculateVerifierDigit(final String digits, final int[] weights) {
        int sum = 0;
        for (int i = 0; i < digits.length(); i++) {
            sum += Character.getNumericValue(digits.charAt(i)) * weights[i];
        }
        sum = 11 - (sum % 11);
        return sum >= 10 ? 0 : sum;
    }
}
