package ru.t1.dkononov.tm.api.services;

import org.jetbrains.annotations.NotNull;

public interface IServiceLocator {

    @NotNull
    IAuthService getAuthService();

    @NotNull
    IUserService getUserService();

    @NotNull
    IProjectService getProjectService();

    @NotNull
    IProjectTaskService getProjectTaskService();

    @NotNull
    ITaskService getTaskService();

    @NotNull
    IPropertyService getPropertyService();

    @NotNull
    IDomainService getDomainService();

}
