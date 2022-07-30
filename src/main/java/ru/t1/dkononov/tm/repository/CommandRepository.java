package ru.t1.dkononov.tm.repository;

import ru.t1.dkononov.tm.api.repository.ICommandRepository;
import ru.t1.dkononov.tm.command.AbstractCommand;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

public class CommandRepository implements ICommandRepository {

    private final Map<String, AbstractCommand> mapByArgument = new LinkedHashMap<>();

    private final Map<String,AbstractCommand> mapByName = new LinkedHashMap<>();

    @Override
    public void add(final AbstractCommand command) {
        if (command == null) return;
        final String name = command.getName();
        if (name != null && !name.isEmpty()) mapByName.put(name,command);
        final String argument = command.getArgument();
        if (argument != null && !argument.isEmpty()) mapByArgument.put(argument,command);
    }

    @Override
    public AbstractCommand getCommandByArgument(final String argument) {
        if (argument == null || argument.isEmpty()) return null;
        return mapByArgument.get(argument);
    }

    @Override
    public AbstractCommand getCommandByName(final String name) {
        if (name == null || name.isEmpty()) return null;
        return mapByName.get(name);
    }

    @Override
    public Collection<AbstractCommand> getTerminalCommands() {
        return mapByName.values();
    }

}
