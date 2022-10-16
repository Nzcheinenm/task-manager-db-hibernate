package ru.t1.dkononov.tm.command.user;

import lombok.Getter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.t1.dkononov.tm.dto.request.UserRemoveRequest;
import ru.t1.dkononov.tm.enumerated.Role;
import ru.t1.dkononov.tm.util.TerminalUtil;

public final class UserRemoveCommand extends AbstractUserCommand {

    @Getter
    @NotNull
    private final String DESCRIPTION = "user remove";

    @Getter
    @NotNull
    private final String NAME = "user-remove";

    @Override
    public void execute() throws Exception {
        System.out.println("[USER REMOVE]");
        System.out.println("ENTER LOGIN:");
        @NotNull final String login = TerminalUtil.inLine();
        @NotNull final UserRemoveRequest request = new UserRemoveRequest(getToken());
        request.setLogin(login);
        getUserEndpoint().removeUser(request);
    }

    @Nullable
    @Override
    public Role[] getRoles() {
        return new Role[]{Role.ADMIN};
    }

}
