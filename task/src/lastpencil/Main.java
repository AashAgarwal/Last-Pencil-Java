package lastpencil;

import java.util.Scanner;

public class Main {

    public static int numberOfPencil(Scanner scanner) {
        String input = scanner.nextLine();
        int pencils;
        try {
            pencils = Integer.parseInt(input);
        } catch (Exception e) {
            System.out.println("The number of pencils should be numeric");
            pencils = numberOfPencil(scanner);
        }
        if (pencils == 0) {
            System.out.println("The number of pencils should be positive");
            pencils = numberOfPencil(scanner);
        } else if (pencils < 0) {
            System.out.println("The number of pencils should be numeric");
            pencils = numberOfPencil(scanner);
        }
        return pencils;
    }

    public static String correctInputName(Scanner scanner) {
        String input = scanner.nextLine();
        if (!input.equals("John") && !input.equals("Jack")) {
            System.out.println("Choose between John and Jack");
            input = correctInputName(scanner);
        }
        return input;
    }

    public static int correctNumberOfPencils(Scanner scanner, int remainingPencils) {
        String input = scanner.nextLine();
        int inputNumberOfPencils;
        try {
            inputNumberOfPencils = Integer.parseInt(input);
        } catch (Exception e) {
            System.out.println("Possible values: '1', '2' or '3'");
            inputNumberOfPencils = correctNumberOfPencils(scanner, remainingPencils);
        }
        if (inputNumberOfPencils != 1 && inputNumberOfPencils != 2 && inputNumberOfPencils != 3) {
            System.out.println("Possible values: '1', '2' or '3'");
            inputNumberOfPencils = correctNumberOfPencils(scanner, remainingPencils);
        } else if (inputNumberOfPencils > remainingPencils) {
            System.out.println("Too many pencils were taken");
            inputNumberOfPencils = correctNumberOfPencils(scanner, remainingPencils);
        }
        return inputNumberOfPencils;
    }

    public static int botTurn(int remainingPencils) {
        if (remainingPencils % 4 == 0) {
            return 3;
        } else if (remainingPencils % 4 == 3) {
            return 2;
        } else if (remainingPencils % 4 == 2) {
            return 1;
        } else {
            if (remainingPencils >= 3) {
                return (int) Math.floor(Math.random() * (3 - 1 + 1) + 1);
            } else if (remainingPencils == 2) {
                return (int) Math.floor(Math.random() * (2 - 1 + 1) + 1);
            } else {
                return 1;
            }
        }
    }

    public static void printPencils(int pencils) {
        for (int i = 0; i < pencils; i++) {
            System.out.print("|");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("How many pencils would you like to use:");
        int pencils = numberOfPencil(scanner);
        System.out.println("Who will be the first (John, Jack):");
        String first = correctInputName(scanner);
        String second = first.equals("John") ? "Jack" : "John";
        boolean playerFirst = first.equals("John");
        int remainingPencils = pencils;
        printPencils(remainingPencils);
        String winner = "";
        while (remainingPencils > 0) {
            if (playerFirst) {
                System.out.printf("%s's turn!\n", first);
                int pencilForFirst = correctNumberOfPencils(scanner, remainingPencils);
                remainingPencils -= pencilForFirst;
                printPencils(remainingPencils);
                winner = second;
                if (remainingPencils > 0) {
                    System.out.printf("%s's turn!\n", second);
                    int pencilForSecond = botTurn(remainingPencils);
                    System.out.println(pencilForSecond);
                    remainingPencils -= pencilForSecond;
                    printPencils(remainingPencils);
                    winner = first;
                }
            } else {
                System.out.printf("%s's turn!\n", first);
                int pencilForFirst = botTurn(remainingPencils);
                System.out.println(pencilForFirst);
                remainingPencils -= pencilForFirst;
                printPencils(remainingPencils);
                winner = second;
                if (remainingPencils > 0) {
                    System.out.printf("%s's turn!\n", second);
                    int pencilForSecond = correctNumberOfPencils(scanner, remainingPencils);
                    remainingPencils -= pencilForSecond;
                    printPencils(remainingPencils);
                    winner = first;
                }
            }
        }
        System.out.printf("%s won!\n", winner);
    }
}
