package ru.t1.dkononov.tm.command.system;

import ru.t1.dkononov.tm.api.model.ICommand;
import ru.t1.dkononov.tm.command.AbstractCommand;

import java.util.Collection;

public final class CommandListCommand extends AbstractSystemCommand {

    public static final String DESCRIPTION = "Показать список команд";

    public static final String NAME = "commands";

    public static final String ARGUMENT = "-cmd";

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
        for (final ICommand command : commands) {
            final String name = command.getName();
            if (name == null || name.isEmpty()) continue;
            System.out.println(name);
        }
    }

}
