package ru.t1.dkononov.tm.command.user;

import lombok.Getter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.t1.dkononov.tm.enumerated.Role;
import ru.t1.dkononov.tm.exception.AbstractException;
import ru.t1.dkononov.tm.util.TerminalUtil;

public final class UserUpdateProfileCommand extends AbstractUserCommand {

    @Getter
    @NotNull
    private final String NAME = "update-user-profile";

    @Getter
    @NotNull
    private final String DESCRIPTION = "update profile of current user";

    @Override
    public void execute() throws AbstractException {
        @Nullable final String userId = serviceLocator.getAuthService().getUserId();
        System.out.println("[USER UPDATE PROFILE]");
        System.out.println("FIRST NAME");
        @NotNull final String firstName = TerminalUtil.inLine();
        System.out.println("LAST NAME");
        @NotNull final String lastName = TerminalUtil.inLine();
        System.out.println("MIDDLE NAME");
        @NotNull final String middleName = TerminalUtil.inLine();
        serviceLocator.getUserService().updateUser(
                userId, firstName, lastName, middleName
        );
    }

    @Nullable
    @Override
    public Role[] getRoles() {
        return Role.values();
    }

}
