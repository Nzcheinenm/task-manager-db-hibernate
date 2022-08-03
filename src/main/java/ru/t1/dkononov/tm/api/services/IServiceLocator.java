package ru.t1.dkononov.tm.api.services;

public interface IServiceLocator {

    IAuthService getAuthService();

    IUserService getUserService();

    ICommandService getCommandService();

    ILoggerService getLoggerService();

    IProjectService getProjectService();

    IProjectTaskService getProjectTaskService();

    ITaskService getTaskService();

}
