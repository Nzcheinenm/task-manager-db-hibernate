package ru.t1.dkononov.tm.service;

import ru.t1.dkononov.tm.api.repository.ICommandRepository;
import ru.t1.dkononov.tm.api.services.ICommandService;
import ru.t1.dkononov.tm.command.AbstractCommand;

import java.util.Collection;

public final class CommandService implements ICommandService {

    private final ICommandRepository commandRepository;

    public CommandService(final ICommandRepository commandRepository) {
        this.commandRepository = commandRepository;
    }

    @Override
    public void add(final AbstractCommand command) {
        if (command == null) return;
        commandRepository.add(command);
    }

    @Override
    public AbstractCommand getCommandByArgument(final String argument) {
        if (argument == null || argument.isEmpty()) return null;
        return commandRepository.getCommandByArgument(argument);
    }

    @Override
    public AbstractCommand getCommandByName(final String name) {
        if (name == null || name.isEmpty()) return null;
        return commandRepository.getCommandByName(name);
    }

    @Override
    public Collection<AbstractCommand> getTerminalCommands() {
        return commandRepository.getTerminalCommands();
    }

}
