package ru.t1.dkononov.tm.command.user;

import lombok.Getter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.t1.dkononov.tm.enumerated.Role;
import ru.t1.dkononov.tm.exception.AbstractException;

public final class UserLogoutCommand extends AbstractUserCommand {

    @Getter
    @NotNull
    private final String NAME = "logout";

    @Getter
    @NotNull
    private final String DESCRIPTION = "logout current user";

    @Override
    public void execute() throws AbstractException {
        System.out.println("[USER LOGOUT]");
        serviceLocator.getAuthService().logout();
    }

    @Nullable
    @Override
    public Role[] getRoles() {
        return Role.values();
    }
}
