package ru.t1.dkononov.tm.api.services;

import org.jetbrains.annotations.NotNull;

public interface IServiceLocator {

    @NotNull
    IAuthService getAuthService();

    @NotNull
    IUserService getUserService();

    @NotNull
    ICommandService getCommandService();

    @NotNull
    ILoggerService getLoggerService();

    @NotNull
    IProjectService getProjectService();

    @NotNull
    IProjectTaskService getProjectTaskService();

    @NotNull
    ITaskService getTaskService();

    @NotNull
    IPropertyService getPropertyService();

}
