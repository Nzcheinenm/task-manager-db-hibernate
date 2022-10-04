package ru.t1.dkononov.tm.api.repository;

import org.jetbrains.annotations.Nullable;
import ru.t1.dkononov.tm.command.AbstractCommand;

import java.util.Collection;

public interface ICommandRepository {

    void add(@Nullable AbstractCommand command);

    @Nullable
    AbstractCommand getCommandByArgument(@Nullable String argument);

    @Nullable
    AbstractCommand getCommandByName(@Nullable String name);

    @Nullable
    Collection<AbstractCommand> getTerminalCommands();

    @Nullable
    Iterable<AbstractCommand> getCommandsByArgument();

}
