package ru.t1.dkononov.tm.context;

import ru.t1.dkononov.tm.api.ICommandController;
import ru.t1.dkononov.tm.api.ICommandRepository;
import ru.t1.dkononov.tm.api.ICommandService;
import ru.t1.dkononov.tm.constant.ArgumentConst;
import ru.t1.dkononov.tm.constant.CommandConst;
import ru.t1.dkononov.tm.controller.CommandController;
import ru.t1.dkononov.tm.repository.CommandRepository;
import ru.t1.dkononov.tm.service.CommandService;

import java.util.Scanner;

public class Bootstrap {

    private final ICommandRepository commandRepository = new CommandRepository();

    private final ICommandService commandService = new CommandService(commandRepository);

    private final ICommandController commandController = new CommandController(commandService);

    public void run(String[] args) {
        processArguments(args);
        processCommands();
    }

    private void processArguments(final String[] args) {
        if (args == null || args.length == 0) return;
        final String argument = args[0];
        processArgument(argument);
    }

    private void processCommands() {
        final Scanner scanner = new Scanner(System.in);
        System.out.println("** WELCOME TO TASK MANAGER **");
        while (!Thread.currentThread().isInterrupted()) {
            System.out.println("ENTER COMMAND: ");
            final String command = scanner.nextLine();
            processCommand(command);
        }
    }

    private void processArgument(final String argument) {
        if (argument == null || argument.isEmpty()) return;
        switch (argument) {
            case ArgumentConst.HELP:
                commandController.showHelp();
                break;
            case ArgumentConst.ABOUT:
                commandController.showAbout();
                break;
            case ArgumentConst.VERSION:
                commandController.showVersion();
                break;
            case ArgumentConst.INFO:
                commandController.showSystemInfo();
                break;
            default:
                commandController.showErrorArgument();
                break;
        }
        System.exit(0);
    }

    private void processCommand(final String argument) {
        if (argument == null || argument.isEmpty()) return;
        switch (argument) {
            case CommandConst.HELP:
                commandController.showHelp();
                break;
            case CommandConst.ABOUT:
                commandController.showAbout();
                break;
            case CommandConst.VERSION:
                commandController.showVersion();
                break;
            case CommandConst.INFO:
                commandController.showSystemInfo();
                break;
            case CommandConst.EXIT:
                commandController.showExit();
                break;
            default:
                commandController.showErrorCommand();
                break;
        }
    }

}
