package ru.t1.dkononov.tm.api.services;

public interface IServiceLocator {

    ICommandService getCommandService();

    ILoggerService getLoggerService();

    IProjectService getProjectService();

    IProjectTaskService getProjectTaskService();

    ITaskService getTaskService();

}
