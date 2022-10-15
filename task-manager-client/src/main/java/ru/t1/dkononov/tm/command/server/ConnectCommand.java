package ru.t1.dkononov.tm.command.server;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.t1.dkononov.tm.api.client.IEndpointClient;
import ru.t1.dkononov.tm.api.services.IServiceLocator;
import ru.t1.dkononov.tm.command.AbstractCommand;
import ru.t1.dkononov.tm.enumerated.Role;

import java.net.Socket;

public final class ConnectCommand extends AbstractCommand {

    @NotNull
    public static final String NAME = "connect";

    @NotNull
    public static final String DESCRIPTION = "connect";

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
//            @NotNull final IEndpointClient endpointClient = serviceLocator.getConnectionEndpointClient();
//            @Nullable final Socket socket = endpointClient.connect();

//            serviceLocator.getAuthEndpointClient().setSocket(socket);
//            serviceLocator.getSystemEndpointClient().setSocket(socket);
//            serviceLocator.getDomainEndpointClient().setSocket(socket);
//            serviceLocator.getProjectEndpointClient().setSocket(socket);
//            serviceLocator.getTaskEndpointClient().setSocket(socket);
//            serviceLocator.getUserEndpointClient().setSocket(socket);
        } catch (@NotNull final Exception e) {
            getServiceLocator().getLoggerService().error(e);
        }
    }

}
