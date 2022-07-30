package ru.t1.dkononov.tm.command.system;

import ru.t1.dkononov.tm.command.AbstractCommand;

import java.util.Collection;

public final class ArgumentListCommand extends AbstractSystemCommand {

    public static final String DESCRIPTION = "Показать список аргументов";

    public static final String NAME = "arguments";

    public static final String ARGUMENT = "-args";

    @Override
    public String getArgument() {
        return ARGUMENT;
    }

    @Override
    public String getDescription() {
        return DESCRIPTION;
    }

    @Override
    public String getName() {
        return NAME;
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
