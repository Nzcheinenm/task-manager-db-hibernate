package ru.t1.dkononov.tm.command.user;

import ru.t1.dkononov.tm.enumerated.Role;
import ru.t1.dkononov.tm.exception.AbstractException;
import ru.t1.dkononov.tm.util.TerminalUtil;

public final class UserLockCommand extends AbstractUserCommand {

    private final String DESCRIPTION = "user lock";

    private final String NAME = "user-lock";

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
        System.out.println("[USER LOCK]");
        System.out.println("ENTER LOGIN:");
        final String login = TerminalUtil.inLine();
        serviceLocator.getUserService().lockUserByLogin(login);
    }

    @Override
    public Role[] getRoles() {
        return new Role[] { Role.ADMIN };
    }

}
