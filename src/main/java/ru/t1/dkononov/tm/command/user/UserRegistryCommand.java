package ru.t1.dkononov.tm.command.user;

import ru.t1.dkononov.tm.api.services.IAuthService;
import ru.t1.dkononov.tm.enumerated.Role;
import ru.t1.dkononov.tm.exception.AbstractException;
import ru.t1.dkononov.tm.model.User;
import ru.t1.dkononov.tm.util.TerminalUtil;

public final class UserRegistryCommand extends AbstractUserCommand {

    private final String NAME = "user-registry";

    private final String DESCRIPTION = "registry user";

    @Override
    public String getDescription() {
        return null;
    }

    @Override
    public String getName() {
        return null;
    }

    @Override
    public void execute() throws AbstractException {
        System.out.println("[USER REGISTRY]");
        System.out.println("ENTER LOGIN:");
        final String login = TerminalUtil.inLine();
        System.out.println("ENTER PASSWORD:");
        final String password = TerminalUtil.inLine();
        System.out.println("ENTER EMAIL:");
        final String email = TerminalUtil.inLine();
        final IAuthService authService = serviceLocator.getAuthService();
        final User user = authService.registry(login, password, email);
        showUser(user);
    }

    @Override
    public Role[] getRoles() {
        return null;
    }
}
