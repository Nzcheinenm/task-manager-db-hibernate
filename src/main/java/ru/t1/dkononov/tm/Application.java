package ru.t1.dkononov.tm;

import ru.t1.dkononov.tm.constant.ArgumentConst;
import ru.t1.dkononov.tm.constant.CommandConst;

import java.util.Scanner;

public class Application {

    public static void main(String[] args) {
        processArguments(args);
        processCommands();
    }

    private static void processArguments(final String[] args) {
        if (args == null || args.length == 0) return;
        final String argument = args[0];
        processArgument(argument);
    }

    private static void processCommands() {
        final Scanner scanner = new Scanner(System.in);
        System.out.println("** WELCOME TO TASK MANAGER **");
        while (!Thread.currentThread().isInterrupted()) {
            System.out.println("ENTER COMMAND: ");
            final String command = scanner.nextLine();
            processCommand(command);
        }
    }

    private static void processArgument(final String argument) {
        if (argument == null || argument.isEmpty()) return;
        switch (argument) {
            case ArgumentConst.HELP:
                showHelp();
                break;
            case ArgumentConst.ABOUT:
                showAbout();
                break;
            case ArgumentConst.VERSION:
                showVersion();
                break;
            default:
                showErrorArgument();
                break;
        }
        System.exit(0);
    }

    private static void processCommand(final String argument) {
        if (argument == null || argument.isEmpty()) return;
        switch (argument) {
            case CommandConst.HELP:
                showHelp();
                break;
            case CommandConst.ABOUT:
                showAbout();
                break;
            case CommandConst.VERSION:
                showVersion();
                break;
            case CommandConst.EXIT:
                showExit();
                break;
            default:
                showErrorCommand();
                break;
        }
    }

    private static void showHelp() {
        System.out.println("[HELP]");
        System.out.printf("%s, %s - Показать информацию о разработчике.\n",ArgumentConst.ABOUT,CommandConst.ABOUT);
        System.out.printf("%s, %s - Показать версию приложения.\n",ArgumentConst.VERSION,CommandConst.VERSION);
        System.out.printf("%s, %s - Показать справку о командах.\n",ArgumentConst.HELP,CommandConst.HELP);
        System.out.printf("%s - Выйти из программы.\n",CommandConst.EXIT);
    }

    private static void showVersion() {
        System.out.println("[VERSION]");
        System.out.println("1.6.0");
    }

    private static void showExit() {
        System.out.println("[EXIT]");
        System.exit(0);
    }

    private static void showAbout() {
        System.out.println("[ABOUT]");
        System.out.println("имя: Дмитрий Кононов");
        System.out.println("email: dkononov@t1-consulting.com");
    }

    private static void showErrorCommand() {
        System.err.println("[Error]");
        System.err.println("Этот аргумент не поддерживается...");
    }

    private static void showErrorArgument() {
        System.err.println("[Error]");
        System.err.println("Этот аргумент не поддерживается...");
        System.exit(2);
    }

}

