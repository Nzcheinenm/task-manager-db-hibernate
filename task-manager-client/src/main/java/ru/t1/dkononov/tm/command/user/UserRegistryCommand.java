package ru.t1.dkononov.tm.command.user;

import lombok.Getter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.t1.dkononov.tm.dto.request.UserRegistryRequest;
import ru.t1.dkononov.tm.enumerated.Role;
import ru.t1.dkononov.tm.exception.AbstractException;
import ru.t1.dkononov.tm.dto.model.UserDTO;
import ru.t1.dkononov.tm.util.TerminalUtil;

public final class UserRegistryCommand extends AbstractUserCommand {

    @Getter
    @NotNull
    private final String NAME = "user-registry";

    @Getter
    @NotNull
    private final String DESCRIPTION = "registry user";

    @Override
    public void execute() throws AbstractException {
        System.out.println("[USER REGISTRY]");
        System.out.println("ENTER LOGIN:");
        @NotNull final String login = TerminalUtil.inLine();
        System.out.println("ENTER PASSWORD:");
        @NotNull final String password = TerminalUtil.inLine();
        System.out.println("ENTER EMAIL:");
        @NotNull final String email = TerminalUtil.inLine();
        @NotNull final UserRegistryRequest request = new UserRegistryRequest(getToken());
        request.setPassword(password);
        request.setLogin(login);
        request.setEmail(email);
        @NotNull final UserDTO user = getUserEndpoint().registryUser(request).getUser();
        showUser(user);
    }

    @Nullable
    @Override
    public Role[] getRoles() {
        return null;
    }
}
