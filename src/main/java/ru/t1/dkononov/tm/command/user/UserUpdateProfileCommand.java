package ru.t1.dkononov.tm.command.user;

import ru.t1.dkononov.tm.exception.AbstractException;
import ru.t1.dkononov.tm.util.TerminalUtil;

public final class UserUpdateProfileCommand extends AbstractUserCommand {

    private final String NAME = "update-user-profile";

    private final String DESCRIPTION = "update profile of current user";

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
        System.out.println("[USER UPDATE PROFILE]");
        System.out.println("FIRST NAME");
        final String firstName = TerminalUtil.inLine();
        System.out.println("LAST NAME");
        final String lastName = TerminalUtil.inLine();
        System.out.println("MIDDLE NAME");
        final String middleName = TerminalUtil.inLine();
        serviceLocator.getUserService().updateUser(
                userId, firstName, lastName, middleName
        );
    }

}
