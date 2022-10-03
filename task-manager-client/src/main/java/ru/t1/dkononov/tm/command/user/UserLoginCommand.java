package ru.t1.dkononov.tm.command.user;

import lombok.Getter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.t1.dkononov.tm.enumerated.Role;
import ru.t1.dkononov.tm.exception.AbstractException;
import ru.t1.dkononov.tm.util.TerminalUtil;

public final class UserLoginCommand extends AbstractUserCommand {

    @Getter
    @NotNull
    private final String NAME = "login";

    @Getter
    @NotNull
    private final String DESCRIPTION = "user login";


    @Override
    public void execute() throws AbstractException {
        System.out.println("[USER LOGIN]");
        System.out.println("ENTER LOGIN:");
        @NotNull final String login = TerminalUtil.inLine();
        System.out.println("ENTER PASSWORD");
        @NotNull final String password = TerminalUtil.inLine();
        serviceLocator.getAuthService().login(login, password);
    }

    @Nullable
    @Override
    public Role[] getRoles() {
        return null;
    }
}
