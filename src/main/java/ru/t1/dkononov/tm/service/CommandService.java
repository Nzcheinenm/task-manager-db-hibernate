package ru.t1.dkononov.tm.service;

import ru.t1.dkononov.tm.api.repository.ICommandRepository;
import ru.t1.dkononov.tm.api.services.ICommandService;
import ru.t1.dkononov.tm.model.Command;

public final class CommandService implements ICommandService {

    private final ICommandRepository commandRepository;

    public CommandService(final ICommandRepository commandRepository) {
        this.commandRepository = commandRepository;
    }

    @Override
    public Command[] getCommands() {
        return commandRepository.getCommands();
    }

}
