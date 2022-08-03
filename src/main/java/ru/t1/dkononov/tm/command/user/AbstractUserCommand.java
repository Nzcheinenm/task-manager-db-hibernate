package ru.t1.dkononov.tm.command.user;

import ru.t1.dkononov.tm.command.AbstractCommand;
import ru.t1.dkononov.tm.exception.field.UserNotFoundException;
import ru.t1.dkononov.tm.model.User;

public abstract class AbstractUserCommand extends AbstractCommand {

    protected void showUser(final User user) throws UserNotFoundException {
        if (user == null) throw new UserNotFoundException();
        System.out.println("ID: " + user.getId());
        System.out.println("LOGIN: " + user.getLogin());
    }

    @Override
    public String getArgument() {
        return null;
    }
}
