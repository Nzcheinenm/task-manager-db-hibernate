package ru.t1.dkononov.tm.controller;

import ru.t1.dkononov.tm.api.controllers.ICommandController;
import ru.t1.dkononov.tm.api.services.ICommandService;
import ru.t1.dkononov.tm.model.Command;
import ru.t1.dkononov.tm.util.FormatUtil;

public final class CommandController implements ICommandController {

    private final ICommandService commandService;

    public CommandController(final ICommandService commandService) {
        this.commandService = commandService;
    }

    @Override
    public void showSystemInfo() {
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

    @Override
    public void showHelp() {
        System.out.println("[HELP]");
        for (final Command command : commandService.getCommands()) {
            System.out.println(command.toString());
        }
    }

    @Override
    public void showVersion() {
        System.out.println("[VERSION]");
        System.out.println("1.13.0");
    }

    @Override
    public void showExit() {
        System.out.println("[EXIT]");
        System.exit(0);
    }

    @Override
    public void showAbout() {
        System.out.println("[ABOUT]");
        System.out.println("имя: Дмитрий Кононов");
        System.out.println("email: dkononov@t1-consulting.com");
    }

    @Override
    public void showErrorCommand() {
        System.err.println("[Error]");
        System.err.println("Этот аргумент не поддерживается...");
    }

    @Override
    public void showErrorArgument() {
        System.err.println("[Error]");
        System.err.println("Этот аргумент не поддерживается...");
        System.exit(2);
    }

}
