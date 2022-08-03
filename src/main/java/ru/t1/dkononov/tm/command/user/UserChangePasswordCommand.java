package ru.t1.dkononov.tm.command.user;

import ru.t1.dkononov.tm.exception.AbstractException;
import ru.t1.dkononov.tm.util.TerminalUtil;

public final class UserChangePasswordCommand extends AbstractUserCommand {

    private final String NAME = "change-user-password";

    private final String DESCRIPTION = "change password of current user";

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
        final String userId = serviceLocator.getAuthService().getUserId();
        System.out.println("[USER CHANGE PASSWORD]");
        System.out.println("ENTER NEW PASSWORD:");
        final String password = TerminalUtil.inLine();
        serviceLocator.getUserService().setPassword(userId, password);
    }
}
