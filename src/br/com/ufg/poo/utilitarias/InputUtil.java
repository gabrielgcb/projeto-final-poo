package br.com.ufg.poo.utilitarias;

import javax.swing.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class InputUtil {

     private static final Scanner scanner = new Scanner(System.in);

     public static String lerString(String mensagem) {
         return JOptionPane.showInputDialog(null, mensagem, "Título", JOptionPane.INFORMATION_MESSAGE);
     }

     public static int lerInt(String mensagem) {
         while(true) {
             String entrada = JOptionPane.showInputDialog(null, mensagem, "Título", JOptionPane.INFORMATION_MESSAGE);
             try {
                 return Integer.parseInt(entrada);
             } catch (NumberFormatException e) {
                 JOptionPane.showMessageDialog(null, "Entrada inválida. Por favor, digite um número inteiro.", "Título", JOptionPane.ERROR_MESSAGE);
             }
         }
     }

     public static LocalDate lerData(String mensagem) {
         DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
         while(true) {
             String entrada = JOptionPane.showInputDialog(null, mensagem, "Título", JOptionPane.INFORMATION_MESSAGE);
             if(entrada != null) {
                 if (entrada.trim().isEmpty()) {
                     return null;
                 }
                 try {
                     return LocalDate.parse(entrada, formatter);
                 } catch (DateTimeParseException e) {
                     JOptionPane.showMessageDialog(null, "Entrada inválida. Por favor, digite uma data no formato dd/MM/yyyy.", "Título", JOptionPane.ERROR_MESSAGE);
                 }
             }
         }
     }

     public static double lerDouble(String mensagem) {
         while(true) {
             String entrada = JOptionPane.showInputDialog(null, mensagem, "Título", JOptionPane.INFORMATION_MESSAGE);
             try {
                 return Double.parseDouble(entrada);
             } catch (NumberFormatException e) {
                 JOptionPane.showMessageDialog(null, "Entrada inválida. Por favor, digite um número decimal.", "Título", JOptionPane.ERROR_MESSAGE);
             }
         }
     }

}
