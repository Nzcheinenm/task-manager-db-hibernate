package ru.t1.dkononov.tm.command.system;

import lombok.Getter;
import org.jetbrains.annotations.NotNull;
import ru.t1.dkononov.tm.api.model.ICommand;
import ru.t1.dkononov.tm.command.AbstractCommand;

import java.util.Collection;

public final class ApplicationHelpCommand extends AbstractSystemCommand {

    @Getter
    @NotNull
    public final String ARGUMENT = "-h";

    @Getter
    @NotNull
    public final String DESCRIPTION = "Вывести список команд";

    @Getter
    @NotNull
    public final String NAME = "help";

    @Override
    public void execute() {
        System.out.println("[HELP]");
        @NotNull final Collection<AbstractCommand> commands = getCommandService().getTerminalCommands();
        for (@NotNull final ICommand command : commands) System.out.println(command);
    }

}
