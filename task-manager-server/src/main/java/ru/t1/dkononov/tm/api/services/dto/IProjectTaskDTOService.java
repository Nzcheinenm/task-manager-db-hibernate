package ru.t1.dkononov.tm.api.services.dto;

import org.jetbrains.annotations.Nullable;

public interface IProjectTaskDTOService {
    void removeProjectById(
            @Nullable String userId,
            @Nullable String projectId
    ) throws Exception;

    void unbindTaskFromProject(
            @Nullable String userId,
            @Nullable String projectId,
            @Nullable String taskId
    ) throws Exception;

    void bindTaskToProject(
            @Nullable String userId,
            @Nullable String projectId,
            @Nullable String taskId
    ) throws Exception;
}
