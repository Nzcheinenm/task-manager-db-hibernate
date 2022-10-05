package ru.t1.dkononov.tm.command.user;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.t1.dkononov.tm.api.client.IUserEndpointClient;
import ru.t1.dkononov.tm.command.AbstractCommand;
import ru.t1.dkononov.tm.exception.field.UserNotFoundException;
import ru.t1.dkononov.tm.model.User;

public abstract class AbstractUserCommand extends AbstractCommand {

    @NotNull
    protected IUserEndpointClient getUserEndpoint() {
        return serviceLocator.getUserEndpointClient();
    }

    protected void showUser(@Nullable final User user) throws UserNotFoundException {
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
