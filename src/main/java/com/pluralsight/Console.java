package com.pluralsight;

import java.util.Scanner;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class Console {

    // add a scanner
    Scanner scanner = new Scanner(System.in);

    // takes input and returns result
    public int PromptforInt(String prompt){
        boolean hasResult = false;
        int result = -1;
        while(!hasResult){
            try {

                System.out.print(prompt);
                result = scanner.nextInt();
                scanner.nextLine();
                hasResult = true;
            } catch (Exception e){
                System.out.println("NOT A VALID OPTION, PLEASE TRY AGAIN");
                scanner.next();
            }
        }

        return  result;
    }

    public float promptForFloat(String prompt){
        boolean hasResult = false;
        float result = -1;

        while (!hasResult) {
            try {
                System.out.print(prompt);
                result = scanner.nextFloat();
                return result;
            } catch (Exception e) {
                System.out.println("NOT A VALID INPUT, PLEASE TRY AGAIN.");
                scanner.nextLine();
            }
        }
        return result;
    }


    public double promptForDouble(String prompt) {
        boolean hasResult = false;
        double result = -1;

        while (!hasResult) {
            try {
                System.out.print(prompt);
                result = scanner.nextDouble();
                scanner.nextLine();
                return result;

            } catch (Exception e) {
                System.out.println("NOT A VALID INPUT, PLEASE TRY AGAIN.");
                scanner.nextLine();
            }
        }
        return result;
    }

    public String promptForString(String prompt){
        System.out.print(prompt);
        return scanner.nextLine().trim();


    }


    public LocalDate promptForDate(String prompt) {
        LocalDate result = null;
        boolean hasResult = false;

        while (!hasResult) {
            try {
                System.out.print(prompt);
                String input = scanner.nextLine().trim();

                if (!input.isEmpty()) {
                    result = LocalDate.parse(input);
                }
                hasResult = true;
            } catch (DateTimeParseException e) {
                System.out.println("INCORRECT DATE FORMAT, PLEASE USE (YYYY), OR LEAVE BLANK.");
            }
        }

        return result;
    }
    }




