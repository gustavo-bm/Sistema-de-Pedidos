package com.example;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Agendador {

    public static LocalDateTime agendar(Scanner scanner) {
        LocalDateTime dataHoraAgendada = null;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

        while (dataHoraAgendada == null) {
            try {
                System.out.print("\nDigite a data do agendamento (dd/MM/yyyy): ");
                String data = scanner.nextLine(); 
                
                System.out.print("\nDigite o horário do agendamento (HH:mm): ");
                String hora = scanner.nextLine(); 

                String dataHoraStr = data + " " + hora;
                dataHoraAgendada = LocalDateTime.parse(dataHoraStr, formatter);
            } catch (InputMismatchException | java.time.format.DateTimeParseException e) {
                System.out.println("Data ou hora inválida. Por favor, tente novamente.");
                scanner.nextLine(); // Limpa o buffer
            }
        }

        return dataHoraAgendada;
    }
}
