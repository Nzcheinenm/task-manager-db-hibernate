package ru.t1.dkononov.tm.command.user;

import lombok.Getter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.t1.dkononov.tm.dto.request.UserLockRequest;
import ru.t1.dkononov.tm.enumerated.Role;
import ru.t1.dkononov.tm.util.TerminalUtil;

public final class UserLockCommand extends AbstractUserCommand {

    @Getter
    @NotNull
    private final String DESCRIPTION = "user lock";

    @Getter
    @NotNull
    private final String NAME = "user-lock";

    @Override
    public void execute() throws Exception {
        System.out.println("[USER LOCK]");
        System.out.println("ENTER LOGIN:");
        @NotNull final String login = TerminalUtil.inLine();
        @NotNull final UserLockRequest request = new UserLockRequest(getToken());
        request.setLogin(login);
        getUserEndpoint().lockUser(request);
    }

    @Nullable
    @Override
    public Role[] getRoles() {
        return new Role[]{Role.ADMIN};
    }

}
