package ru.t1.dkononov.tm;

import ru.t1.dkononov.tm.api.ICommandRepository;
import ru.t1.dkononov.tm.constant.ArgumentConst;
import ru.t1.dkononov.tm.constant.CommandConst;
import ru.t1.dkononov.tm.model.Command;
import ru.t1.dkononov.tm.repository.CommandRepository;
import ru.t1.dkononov.tm.util.FormatUtil;

import java.util.Scanner;

public class Application {
    private static final ICommandRepository COMMAND_REPOSITORY = new CommandRepository();

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
            case ArgumentConst.INFO:
                showSystemInfo();
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
            case CommandConst.INFO:
                showSystemInfo();
                break;
            case CommandConst.EXIT:
                showExit();
                break;
            default:
                showErrorCommand();
                break;
        }
    }

    private static void showSystemInfo() {
        final Runtime runtime = Runtime.getRuntime();
        final long availableProcessors = runtime.availableProcessors();
        final long freeMemory = runtime.freeMemory();
        final String freeMemoryFormat = FormatUtil.format(freeMemory);
        final long maxMemory = runtime.maxMemory();
        final boolean maxMemoryCheck = maxMemory == Long.MAX_VALUE;
        final String maxMemoryFormat = maxMemoryCheck ? "no limit" : FormatUtil.format(maxMemory);
        final long totalMemory = runtime.totalMemory();
        final String totalMemoryFormat = FormatUtil.format(totalMemory);

        System.out.println("Available processors (cores): " + availableProcessors);
        System.out.println("Free memory (bytes): " + freeMemoryFormat);
        System.out.println("Maximum memory (bytes): " + maxMemoryFormat);
        System.out.println("Total memory (bytes): " + totalMemoryFormat);
    }

    private static void showHelp() {
        System.out.println("[HELP]");
        for (Command command : COMMAND_REPOSITORY.getCommands()) {
            System.out.println(command.toString());
        }
    }

    private static void showVersion() {
        System.out.println("[VERSION]");
        System.out.println("1.7.0");
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

