package ru.t1.dkononov.tm.api.services;

public interface IProjectTaskService {
    void bindTaskToProject(final String projectId,final String taskId);

    void removeProjectById(final String projectId);

    void unbindTaskFromProject(final String projectId,final String taskId);
}
