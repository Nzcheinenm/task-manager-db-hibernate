package ru.t1.dkononov.tm.command.system;

import ru.t1.dkononov.tm.api.model.ICommand;
import ru.t1.dkononov.tm.command.AbstractCommand;

import java.util.Collection;

public final class ApplicationHelpCommand extends AbstractSystemCommand {

    public static final String ARGUMENT = "-h";

    public static final String DESCRIPTION = "Вывести список команд";

    public static final String NAME = "help";

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
        System.out.println("[HELP]");
        final Collection<AbstractCommand> commands = getCommandService().getTerminalCommands();
        for (final ICommand command : commands) System.out.println(command);
    }

}
