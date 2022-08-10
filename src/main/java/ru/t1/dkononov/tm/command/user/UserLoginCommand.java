package ru.t1.dkononov.tm.command.user;

import ru.t1.dkononov.tm.enumerated.Role;
import ru.t1.dkononov.tm.exception.AbstractException;
import ru.t1.dkononov.tm.util.TerminalUtil;

public final class UserLoginCommand extends AbstractUserCommand {

    private final String NAME = "login";

    private final String DESCRIPTION = "user login";

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
        System.out.println("[USER LOGIN]");
        System.out.println("ENTER LOGIN:");
        final String login = TerminalUtil.inLine();
        System.out.println("ENTER PASSWORD");
        final String password = TerminalUtil.inLine();
        serviceLocator.getAuthService().login(login, password);
    }

    @Override
    public Role[] getRoles() {
        return null;
    }
}
