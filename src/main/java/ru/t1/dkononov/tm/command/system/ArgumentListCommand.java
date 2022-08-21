package ru.t1.dkononov.tm.command.system;

import lombok.Getter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.t1.dkononov.tm.command.AbstractCommand;

import java.util.Collection;

public final class ArgumentListCommand extends AbstractSystemCommand {

    @Getter
    @NotNull
    public final String DESCRIPTION = "Показать список аргументов";

    @Getter
    @NotNull
    public final String NAME = "arguments";

    @Getter
    @NotNull
    public final String ARGUMENT = "-args";

    @Override
    public void execute() {
        @NotNull final Collection<AbstractCommand> commands = getCommandService().getTerminalCommands();
        for (final AbstractCommand command : commands) {
            @Nullable final String argument = command.getARGUMENT();
            if (argument == null || argument.isEmpty()) continue;
            System.out.println(argument);
        }
    }

}
