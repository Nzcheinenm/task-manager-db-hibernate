package ru.t1.dkononov.tm.api.services;

import org.jetbrains.annotations.Nullable;
import ru.t1.dkononov.tm.exception.AbstractException;

public interface IProjectTaskService {
    void bindTaskToProject(@Nullable String userId, @Nullable String projectId, @Nullable String taskId) throws AbstractException, Exception;

    void removeProjectById(@Nullable String userId, @Nullable String projectId) throws AbstractException, Exception;

    void unbindTaskFromProject(@Nullable String userId, @Nullable String projectId, String taskId) throws AbstractException, Exception;
}
