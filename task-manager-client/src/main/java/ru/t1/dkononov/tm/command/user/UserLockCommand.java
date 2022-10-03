package ru.t1.dkononov.tm.command.user;

import lombok.Getter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.t1.dkononov.tm.enumerated.Role;
import ru.t1.dkononov.tm.exception.AbstractException;
import ru.t1.dkononov.tm.util.TerminalUtil;

public final class UserLockCommand extends AbstractUserCommand {

    @Getter
    @NotNull
    private final String DESCRIPTION = "user lock";

    @Getter
    @NotNull
    private final String NAME = "user-lock";

    @Override
    public void execute() throws AbstractException {
        System.out.println("[USER LOCK]");
        System.out.println("ENTER LOGIN:");
        @NotNull final String login = TerminalUtil.inLine();
        serviceLocator.getUserService().lockUserByLogin(login);
    }

    @Nullable
    @Override
    public Role[] getRoles() {
        return new Role[]{Role.ADMIN};
    }

}
