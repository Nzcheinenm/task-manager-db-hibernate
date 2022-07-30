package ru.t1.dkononov.tm.command.system;

import ru.t1.dkononov.tm.api.services.ICommandService;
import ru.t1.dkononov.tm.command.AbstractCommand;

public abstract class AbstractSystemCommand extends AbstractCommand {

    protected ICommandService getCommandService() {
        return serviceLocator.getCommandService();
    }

}
