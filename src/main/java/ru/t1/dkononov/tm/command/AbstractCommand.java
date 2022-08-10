package ru.t1.dkononov.tm.command;

import ru.t1.dkononov.tm.api.model.ICommand;
import ru.t1.dkononov.tm.api.services.IAuthService;
import ru.t1.dkononov.tm.api.services.IServiceLocator;
import ru.t1.dkononov.tm.enumerated.Role;
import ru.t1.dkononov.tm.exception.field.AccessDeniedException;

public abstract class AbstractCommand implements ICommand {

    protected IServiceLocator serviceLocator;

    public abstract Role[] getRoles();

    public IAuthService getAuthService() {
        return serviceLocator.getAuthService();
    }

    public String getUserId() throws AccessDeniedException {
        return getAuthService().getUserId();
    }

    public IServiceLocator getServiceLocator() {
        return serviceLocator;
    }

    public void setServiceLocator(final IServiceLocator serviceLocator) {
        this.serviceLocator = serviceLocator;
    }

    @Override
    public String toString() {
        final String name = getName();
        final String argument = getArgument();
        final String description = getDescription();
        String result = "";
        if (name != null && !name.isEmpty()) result += name + " : ";
        if (argument != null && !argument.isEmpty()) result += argument + " : ";
        if (description != null && !description.isEmpty()) result += description;
        return result;
    }

}
