package ru.t1.dkononov.tm.api.services;

public interface IProjectTaskService {
    void bindTaskToProject(String projectId, String taskId);

    void removeProjectById(String projectId);

    void unbindTaskFromProject(String projectId, String taskId);
}
