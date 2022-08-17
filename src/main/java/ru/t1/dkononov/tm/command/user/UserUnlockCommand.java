package ru.t1.dkononov.tm.command.user;

import ru.t1.dkononov.tm.enumerated.Role;
import ru.t1.dkononov.tm.exception.AbstractException;
import ru.t1.dkononov.tm.util.TerminalUtil;

public final class UserUnlockCommand extends AbstractUserCommand{

    private final String DESCRIPTION = "user unlock";

    private final String NAME = "user-unlock";

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
        System.out.println("[USER UNLOCK]");
        System.out.println("ENTER LOGIN:");
        final String login = TerminalUtil.inLine();
        serviceLocator.getUserService().unlockUserByLogin(login);
    }

    @Override
    public Role[] getRoles() {
        return new Role[] { Role.ADMIN };
    }

}
