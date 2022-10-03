package ru.t1.dkononov.tm.command.user;

import lombok.Getter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.t1.dkononov.tm.enumerated.Role;
import ru.t1.dkononov.tm.exception.AbstractException;
import ru.t1.dkononov.tm.util.TerminalUtil;

public final class UserRemoveCommand extends AbstractUserCommand {

    @Getter
    @NotNull
    private final String DESCRIPTION = "user remove";

    @Getter
    @NotNull
    private final String NAME = "user-remove";

    @Override
    public void execute() throws AbstractException {
        System.out.println("[USER REMOVE]");
        System.out.println("ENTER LOGIN:");
        @NotNull final String login = TerminalUtil.inLine();
        serviceLocator.getUserService().removeByLogin(login);
    }

    @Nullable
    @Override
    public Role[] getRoles() {
        return new Role[]{Role.ADMIN};
    }

}
