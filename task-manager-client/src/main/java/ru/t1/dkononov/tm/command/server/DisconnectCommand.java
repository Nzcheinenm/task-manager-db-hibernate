package ru.t1.dkononov.tm.command.server;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.t1.dkononov.tm.api.client.IEndpointClient;
import ru.t1.dkononov.tm.api.services.IServiceLocator;
import ru.t1.dkononov.tm.command.AbstractCommand;
import ru.t1.dkononov.tm.enumerated.Role;

public class DisconnectCommand extends AbstractCommand {

    @NotNull
    public static final String NAME = "disconnect";

    @NotNull
    public static final String DESCRIPTION = "disconnect";

    @Override
    public @Nullable Role[] getRoles() {
        return new Role[0];
    }

    @Override
    public @Nullable String getARGUMENT() {
        return null;
    }

    @Override
    public @NotNull String getDESCRIPTION() {
        return DESCRIPTION;
    }

    @Override
    public @NotNull String getNAME() {
        return NAME;
    }

    @Override
    public void execute() throws Exception {
        try {
            @NotNull final IServiceLocator serviceLocator = getServiceLocator();
            @NotNull final IEndpointClient endpointClient = serviceLocator.getConnectionEndpointClient();
            endpointClient.disconnect();
        } catch (@NotNull final Exception e) {
            getServiceLocator().getLoggerService().error(e);
        }
    }
}
