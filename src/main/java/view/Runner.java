package view;

import java.util.Scanner;

public class Runner {

    private static Scanner scanner = new Scanner(System.in);

    public static String run() {

        System.out.println("Напишите имя папки куда хотите зайти?\n" +
                "writers\n" +
                "posts\n" +
                "regions");


        String console;

        do {
            console = scanner.next();

        } while (console.length() == 0);


        return console;

    }
}
