package ru.t1.dkononov.tm.repository;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.t1.dkononov.tm.api.repository.ICommandRepository;
import ru.t1.dkononov.tm.command.AbstractCommand;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.TreeMap;

public class CommandRepository implements ICommandRepository {

    @NotNull
    private final Map<String, AbstractCommand> mapByArgument = new TreeMap<>();

    @NotNull
    private final Map<String, AbstractCommand> mapByName = new TreeMap<>();

    @Override
    public void add(@Nullable final AbstractCommand command) {
        if (command == null) return;
        @Nullable final String name = command.getNAME();
        if (name != null && !name.isEmpty()) mapByName.put(name, command);
        @Nullable final String argument = command.getARGUMENT();
        if (argument != null && !argument.isEmpty()) mapByArgument.put(argument, command);
    }

    @Nullable
    @Override
    public AbstractCommand getCommandByArgument(@Nullable final String argument) {
        if (argument == null || argument.isEmpty()) return null;
        return mapByArgument.get(argument);
    }

    @Nullable
    @Override
    public AbstractCommand getCommandByName(@Nullable final String name) {
        if (name == null || name.isEmpty()) return null;
        return mapByName.get(name);
    }

    @Nullable
    @Override
    public Collection<AbstractCommand> getTerminalCommands() {
        return mapByName.values();
    }

}
