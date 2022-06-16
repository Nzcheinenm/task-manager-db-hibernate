package ru.t1.dkononov.tm.service;

import ru.t1.dkononov.tm.api.ICommandRepository;
import ru.t1.dkononov.tm.api.ICommandService;
import ru.t1.dkononov.tm.model.Command;

public final class CommandService implements ICommandService {

    private final ICommandRepository commandRepository;

    public CommandService(ICommandRepository commandRepository) {
        this.commandRepository = commandRepository;
    }

    @Override
    public Command[] getCommands() {
        return commandRepository.getCommands();
    }

}
