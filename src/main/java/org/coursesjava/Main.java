package org.coursesjava;

import org.coursesjava.enums.MenuItem;
import org.coursesjava.services.ApplicationMenuService;

import java.util.Scanner;

public class Main {
    private static boolean exit = false;
    private static final Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) {
        ApplicationMenuService menu = new ApplicationMenuService();
        while (!exit) {
            System.out.println(MenuItem.TITLE);
            System.out.println(MenuItem.START);
            System.out.println(MenuItem.SERVER_CONFIG);
            System.out.println(MenuItem.EXIT);
            System.out.print(MenuItem.ACTION);

            switch (scanner.nextLine()) {
                case "1" -> menu.start();
                case "2" -> menu.showConfig();
                case "3" -> {
                    exit = true;
                    scanner.close();
                }
                default -> System.err.println(MenuItem.NO_EXISTS_ITEM);
            }
        }
    }
}