package ru.t1.dkononov.tm.api.repository;

import ru.t1.dkononov.tm.command.AbstractCommand;

import java.util.Collection;

public interface ICommandRepository {

    void add(AbstractCommand command);

    AbstractCommand getCommandByArgument(String argument);

    AbstractCommand getCommandByName(String name);

    Collection<AbstractCommand> getTerminalCommands();
}
