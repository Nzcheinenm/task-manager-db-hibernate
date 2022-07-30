package ru.t1.dkononov.tm.command.system;

import ru.t1.dkononov.tm.api.model.ICommand;
import ru.t1.dkononov.tm.command.AbstractCommand;

import java.util.Collection;

public final class CommandListCommand extends AbstractSystemCommand {

    @Override
    public String getArgument() {
        return "-cmd";
    }

    @Override
    public String getDescription() {
        return "Показать список команд";
    }

    @Override
    public String getName() {
        return "commands";
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
