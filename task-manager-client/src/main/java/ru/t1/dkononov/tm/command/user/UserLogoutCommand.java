package ru.t1.dkononov.tm.command.user;

import lombok.Getter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.t1.dkononov.tm.dto.request.UserLogoutRequest;
import ru.t1.dkononov.tm.enumerated.Role;

public final class UserLogoutCommand extends AbstractUserCommand {

    @Getter
    @NotNull
    private final String NAME = "logout";

    @Getter
    @NotNull
    private final String DESCRIPTION = "logout current user";

    @Override
    public void execute() throws Exception {
        System.out.println("[USER LOGOUT]");
        @NotNull final UserLogoutRequest request = new UserLogoutRequest(getToken());
        request.setToken(getToken());
        getAuthEndpoint().logout(request);
        setToken(null);
    }

    @Nullable
    @Override
    public Role[] getRoles() {
        return Role.values();
    }

}
