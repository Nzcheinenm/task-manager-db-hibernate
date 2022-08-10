package ru.t1.dkononov.tm.api.services;

import ru.t1.dkononov.tm.exception.AbstractException;

public interface IProjectTaskService {
    void bindTaskToProject(String userId, String projectId, String taskId) throws AbstractException;

    void removeProjectById(String userId, String projectId) throws AbstractException;

    void unbindTaskFromProject(String userId, String projectId, String taskId) throws AbstractException;
}
