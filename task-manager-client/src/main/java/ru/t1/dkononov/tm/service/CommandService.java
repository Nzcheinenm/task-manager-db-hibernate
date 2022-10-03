package ru.t1.dkononov.tm.service;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.t1.dkononov.tm.api.repository.ICommandRepository;
import ru.t1.dkononov.tm.api.services.ICommandService;
import ru.t1.dkononov.tm.command.AbstractCommand;

import java.util.Collection;

public final class CommandService implements ICommandService {

    @NotNull
    private final ICommandRepository commandRepository;

    public CommandService(@NotNull final ICommandRepository commandRepository) {
        this.commandRepository = commandRepository;
    }

    @Override
    public void add(@Nullable final AbstractCommand command) {
        if (command == null) return;
        commandRepository.add(command);
    }

    @Nullable
    @Override
    public AbstractCommand getCommandByArgument(@Nullable final String argument) {
        if (argument == null || argument.isEmpty()) return null;
        return commandRepository.getCommandByArgument(argument);
    }

    @Nullable
    @Override
    public AbstractCommand getCommandByName(@Nullable final String name) {
        if (name == null || name.isEmpty()) return null;
        return commandRepository.getCommandByName(name);
    }

    @Nullable
    @Override
    public Collection<AbstractCommand> getTerminalCommands() {
        return commandRepository.getTerminalCommands();
    }

    @Nullable
    @Override
    public Iterable<AbstractCommand> getCommandsByArgument() {
        return commandRepository.getCommandsByArgument();
    }

}
