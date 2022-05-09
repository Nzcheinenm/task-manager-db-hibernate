package ru.t1.dkononov.tm;

import static ru.t1.dkononov.tm.constant.TerminalConst.*;

public class Application {

    public static void main(String[] args) {
        processArguments(args);
    }

    public static void processArguments(final String[] args) {
        if (args == null || args.length == 0) return;
        final String argument = args[0];
        processArgument(argument);
    }

    private static void processArgument(final String argument) {
        if (argument == null || argument.isEmpty()) return;
        switch (argument) {
            case HELP:
                showHelp();
                break;
            case ABOUT:
                showAbout();
                break;
            case VERSION:
                showVersion();
                break;
            default:
                showError();
                break;
        }
    }

    private static void showHelp() {
        System.out.println("[HELP]");
        System.out.printf("%s - Показать информацию о разработчике.\n",ABOUT);
        System.out.printf("%s - Показать версию приложения.\n",VERSION);
        System.out.printf("%s - Показать справку о командах.\n",HELP);
    }

    private static void showVersion() {
        System.out.println("[VERSION]");
        System.out.println("1.2.0");
    }

    private static void showAbout() {
        System.out.println("[ABOUT]");
        System.out.println("имя: Дмитрий Кононов");
        System.out.println("email: dkononov@t1-consulting.com");
    }

    private static void showError() {
        System.err.println("[Error]");
        System.err.println("Этот аргумент не поддерживается...");
    }

}

