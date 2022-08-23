package ru.t1.dkononov.tm.command.system;

import lombok.Getter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.t1.dkononov.tm.api.model.ICommand;
import ru.t1.dkononov.tm.command.AbstractCommand;

import java.util.Collection;

public final class CommandListCommand extends AbstractSystemCommand {

    @Getter
    @NotNull
    public final String DESCRIPTION = "Показать список команд";

    @Getter
    @NotNull
    public final String NAME = "commands";

    @Getter
    @NotNull
    public final String ARGUMENT = "-cmd";

    @Override
    public void execute() {
        @Nullable final Collection<AbstractCommand> commands = getCommandService().getTerminalCommands();
        for (@NotNull final ICommand command : commands) {
            @NotNull final String name = command.getNAME();
            if (name == null || name.isEmpty()) continue;
            System.out.println(name);
        }
    }

}
