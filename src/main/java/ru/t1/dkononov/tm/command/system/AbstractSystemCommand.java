package ru.t1.dkononov.tm.command.system;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.t1.dkononov.tm.api.services.ICommandService;
import ru.t1.dkononov.tm.command.AbstractCommand;
import ru.t1.dkononov.tm.enumerated.Role;

public abstract class AbstractSystemCommand extends AbstractCommand {

    @NotNull
    protected ICommandService getCommandService() {
        return serviceLocator.getCommandService();
    }

    @Nullable
    public Role[] getRoles() {
        return null;
    }
}
