package ru.t1.dkononov.tm.command.user;

import lombok.Getter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.t1.dkononov.tm.dto.request.UserLoginRequest;
import ru.t1.dkononov.tm.dto.request.UserLogoutRequest;
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
        @NotNull final UserLogoutRequest request = new UserLogoutRequest();
        getAuthEndpointClient().logout(request);
    }

    @Nullable
    @Override
    public Role[] getRoles() {
        return Role.values();
    }
}
