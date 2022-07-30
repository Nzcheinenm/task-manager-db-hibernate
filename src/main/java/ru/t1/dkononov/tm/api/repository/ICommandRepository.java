package ru.t1.dkononov.tm.api.repository;

import ru.t1.dkononov.tm.command.AbstractCommand;
import ru.t1.dkononov.tm.model.Command;

import java.util.Collection;

public interface ICommandRepository {

    void add(AbstractCommand command);

    AbstractCommand getCommandByArgument(String argument);

    AbstractCommand getCommandByName(String name);

    Collection<AbstractCommand> getTerminalCommands();
}
