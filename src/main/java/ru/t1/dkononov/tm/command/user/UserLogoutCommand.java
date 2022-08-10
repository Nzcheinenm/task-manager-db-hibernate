package ru.t1.dkononov.tm.command.user;

import ru.t1.dkononov.tm.enumerated.Role;
import ru.t1.dkononov.tm.exception.AbstractException;

public final class UserLogoutCommand extends AbstractUserCommand {

    private final String NAME = "logout";

    private final String DESCRIPTION = "logout current user";

    @Override
    public String getDescription() {
        return DESCRIPTION;
    }

    @Override
    public String getName() {
        return NAME;
    }

    @Override
    public void execute() throws AbstractException {
        System.out.println("[USER LOGOUT]");
        serviceLocator.getAuthService().logout();
    }

    @Override
    public Role[] getRoles() {
        return Role.values();
    }
}
