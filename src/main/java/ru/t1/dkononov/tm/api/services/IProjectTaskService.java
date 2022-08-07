package ru.t1.dkononov.tm.api.services;

import ru.t1.dkononov.tm.exception.AbstractException;

public interface IProjectTaskService {
    void bindTaskToProject(final String projectId, final String taskId) throws AbstractException;

    void removeProjectById(final String projectId) throws AbstractException;

    void unbindTaskFromProject(final String projectId, final String taskId) throws AbstractException;
}
