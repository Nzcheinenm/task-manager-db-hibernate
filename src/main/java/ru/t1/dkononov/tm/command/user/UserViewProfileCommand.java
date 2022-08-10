package ru.t1.dkononov.tm.command.user;

import ru.t1.dkononov.tm.enumerated.Role;
import ru.t1.dkononov.tm.exception.AbstractException;
import ru.t1.dkononov.tm.model.User;

public final class UserViewProfileCommand extends AbstractUserCommand {

    private final String NAME = "view-user-profile";

    private final String DESCRIPTION = "view profile of current user";

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
        final User user = serviceLocator.getAuthService().getUser();
        System.out.println("[USER VIEW PROFILE]");
        System.out.println("ID: " + user.getId());
        System.out.println("LOGIN: " + user.getId());
        System.out.println("FIRST NAME: " + user.getFirstName());
        System.out.println("MIDDLE NAME: " + user.getMiddleName());
        System.out.println("LAST NAME: " + user.getLastName());
        System.out.println("E-MAIL: " + user.getEmail());
        System.out.println("ROLE: " + user.getRole().getDisplayName());
    }

    @Override
    public Role[] getRoles() {
        return Role.values();
    }

}
