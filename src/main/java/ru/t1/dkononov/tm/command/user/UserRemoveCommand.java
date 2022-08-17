package ru.t1.dkononov.tm.command.user;

import ru.t1.dkononov.tm.enumerated.Role;
import ru.t1.dkononov.tm.exception.AbstractException;
import ru.t1.dkononov.tm.util.TerminalUtil;

public final class UserRemoveCommand extends AbstractUserCommand {

    private final String DESCRIPTION = "user remove";

    private final String NAME = "user-remove";

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
        System.out.println("[USER REMOVE]");
        System.out.println("ENTER LOGIN:");
        final String login = TerminalUtil.inLine();
        serviceLocator.getUserService().removeByLogin(login);
    }

    @Override
    public Role[] getRoles() {
        return new Role[] { Role.ADMIN };
    }

}
