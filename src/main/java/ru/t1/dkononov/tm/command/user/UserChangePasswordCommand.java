package ru.t1.dkononov.tm.command.user;

import lombok.Getter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.t1.dkononov.tm.enumerated.Role;
import ru.t1.dkononov.tm.exception.AbstractException;
import ru.t1.dkononov.tm.util.TerminalUtil;

public final class UserChangePasswordCommand extends AbstractUserCommand {

    @Getter
    @NotNull
    private final String NAME = "change-user-password";

    @Getter
    @NotNull
    private final String DESCRIPTION = "change password of current user";

    @Override
    public void execute() throws AbstractException {
        @Nullable final String userId = serviceLocator.getAuthService().getUserId();
        System.out.println("[USER CHANGE PASSWORD]");
        System.out.println("ENTER NEW PASSWORD:");
        @NotNull final String password = TerminalUtil.inLine();
        serviceLocator.getUserService().setPassword(userId, password);
    }

    @Nullable
    @Override
    public Role[] getRoles() {
        return Role.values();
    }

}
