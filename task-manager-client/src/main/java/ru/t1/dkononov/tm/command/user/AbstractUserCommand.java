package ru.t1.dkononov.tm.command.user;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.t1.dkononov.tm.api.endpoint.IAuthEndpoint;
import ru.t1.dkononov.tm.api.endpoint.IUserEndpoint;
import ru.t1.dkononov.tm.command.AbstractCommand;
import ru.t1.dkononov.tm.dto.model.UserDTO;
import ru.t1.dkononov.tm.exception.field.UserNotFoundException;

public abstract class AbstractUserCommand extends AbstractCommand {

    @NotNull
    protected IUserEndpoint getUserEndpoint() {
        return serviceLocator.getUserEndpointClient();
    }

    @NotNull
    protected IAuthEndpoint getAuthEndpoint() {
        return serviceLocator.getAuthEndpoint();
    }

    protected void showUser(@Nullable final UserDTO user) throws UserNotFoundException {
        if (user == null) throw new UserNotFoundException();
        System.out.println("ID: " + user.getId());
        System.out.println("LOGIN: " + user.getLogin());
    }

    @Nullable
    @Override
    public String getARGUMENT() {
        return null;
    }
}
