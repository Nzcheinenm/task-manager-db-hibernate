package ru.t1.dkononov.tm.api.services;

import org.jetbrains.annotations.NotNull;
import ru.t1.dkononov.tm.api.client.IEndpointClient;
import ru.t1.dkononov.tm.client.*;

public interface IServiceLocator {

    @NotNull
    ICommandService getCommandService();

    @NotNull
    ILoggerService getLoggerService();

    @NotNull
    IPropertyService getPropertyService();

    @NotNull
    IEndpointClient getConnectionEndpointClient();

    @NotNull
    AuthEndpointClient getAuthEndpointClient();

    @NotNull
    SystemEndpointClient getSystemEndpointClient();

    @NotNull
    DomainEndpointClient getDomainEndpointClient();

    @NotNull
    ProjectEndpointClient getProjectEndpointClient();

    @NotNull
    TaskEndpointClient getTaskEndpointClient();

    @NotNull
    UserEndpointClient getUserEndpointClient();

}
