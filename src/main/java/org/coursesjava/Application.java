package org.coursesjava;

import org.coursesjava.services.ApplicationMenuService;
import org.myLib.Console;

public class Application {
    public static void run() {
        boolean isExit = false;
        ApplicationMenuService menu = new ApplicationMenuService();
        while (!isExit) {
            System.out.println("+ Moskal connector v1.0 +");
            System.out.println("1. Start");
            System.out.println("2. Show server config");
            System.out.println("3. Exit");
            System.out.print("Choose an action: ");

            switch (Console.write()) {
                case "1" -> menu.start();
                case "2" -> menu.showConfig();
                case "3" -> isExit = menu.exit();
                default -> System.err.println("This menu item does not exist!");
            }
        }
    }
}
