package ru.t1.dkononov.tm.command;

import lombok.Getter;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.t1.dkononov.tm.api.model.ICommand;
import ru.t1.dkononov.tm.api.services.IAuthService;
import ru.t1.dkononov.tm.api.services.IServiceLocator;
import ru.t1.dkononov.tm.enumerated.Role;
import ru.t1.dkononov.tm.exception.field.AccessDeniedException;

@Getter
@Setter
public abstract class AbstractCommand implements ICommand {

    @NotNull
    protected IServiceLocator serviceLocator;

    @Nullable
    public abstract Role[] getRoles();

    @Nullable
    public IAuthService getAuthService() {
        return serviceLocator.getAuthService();
    }

    @Nullable
    public String getUserId() throws AccessDeniedException {
        return getAuthService().getUserId();
    }

    @NotNull
    @Override
    public String toString() {
        @NotNull final String name = getNAME();
        @Nullable final String argument = getARGUMENT();
        @NotNull final String description = getDESCRIPTION();
        @Nullable String result = "";
        if (name != null && !name.isEmpty()) result += name + " : ";
        if (argument != null && !argument.isEmpty()) result += argument + " : ";
        if (description != null && !description.isEmpty()) result += description;
        return result;
    }

}
