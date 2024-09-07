package com.example;

import java.util.Scanner;

public class Validador {
    public static long validarCpf(Scanner scanner) {
        long cpf;
        do {
            System.out.println("\n\n======================================");
            System.out.println(" Por gentileza, informe alguns dados.");
            System.out.println("======================================\n");
            System.out.print("CPF: ");
            cpf = scanner.nextLong();
            if (!cpfValido(cpf)) {
                System.out.println("CPF inválido. Tente novamente.");
            }
        } while (!cpfValido(cpf));
        return cpf;
    }

    public static String validarEmail(Scanner scanner) {
        String email;
        do {
            System.out.print("Email: ");
            email = scanner.nextLine();
            if (!emailValido(email)) {
                System.out.println("Email inválido. Tente novamente.");
            }
        } while (!emailValido(email));
        return email;
    }

    private static boolean cpfValido(long cpf) {
        String cpfStr = String.format("%011d", cpf);
        return cpfStr.matches("\\d{11}");
    }

    private static boolean emailValido(String email) {
        return email.matches("^[\\w\\.-]+@[\\w\\.-]+\\.\\w+$");
    }
}
