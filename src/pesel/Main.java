package pesel;

import pesel.PeselValidator;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {
    public static void main(String args[]) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Podaj pesel: ");
        PeselValidator pesel = new PeselValidator(scanner.nextLine());

        System.out.println("Czy pesel poprawny: " + pesel.isValid());
        System.out.println("Płeć: " + pesel.getGender());
        System.out.println("Dzień " + pesel.getDayOfBirth());
        System.out.println("Miesiac " + pesel.getMonthOfBirth());
        System.out.println("Rok " + pesel.getYearOfBirth());
    }
}
























