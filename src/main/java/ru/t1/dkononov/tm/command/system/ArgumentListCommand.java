package ru.t1.dkononov.tm.command.system;

import ru.t1.dkononov.tm.command.AbstractCommand;

import java.util.Collection;

public final class ArgumentListCommand extends AbstractSystemCommand {

    @Override
    public String getArgument() {
        return "-args";
    }

    @Override
    public String getDescription() {
        return "Показать список аргументов";
    }

    @Override
    public String getName() {
        return "arguments";
    }

    @Override
    public void execute() {
        final Collection<AbstractCommand> commands = getCommandService().getTerminalCommands();
        for (final AbstractCommand command : commands) {
            final String argument = command.getArgument();
            if (argument == null || argument.isEmpty()) continue;
            System.out.println(argument);
        }
    }

}
