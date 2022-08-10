package ru.t1.dkononov.tm.command.system;

import ru.t1.dkononov.tm.api.services.ICommandService;
import ru.t1.dkononov.tm.command.AbstractCommand;
import ru.t1.dkononov.tm.enumerated.Role;

public abstract class AbstractSystemCommand extends AbstractCommand {

    protected ICommandService getCommandService() {
        return serviceLocator.getCommandService();
    }

    public Role[] getRoles() {
        return null;
    }
}
