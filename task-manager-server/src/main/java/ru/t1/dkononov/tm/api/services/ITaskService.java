package ru.t1.dkononov.tm.api.services;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.t1.dkononov.tm.enumerated.Status;
import ru.t1.dkononov.tm.exception.AbstractException;
import ru.t1.dkononov.tm.exception.field.AbstractFieldException;
import ru.t1.dkononov.tm.exception.field.UserIdEmptyException;
import ru.t1.dkononov.tm.model.Task;

import java.util.List;

public interface ITaskService {

    @NotNull
    List<Task> findAllByProjectId(@Nullable String userId, @Nullable String projectId) throws UserIdEmptyException, Exception;

    @NotNull
    Task create(@Nullable String userId, @Nullable String name, @Nullable String description) throws AbstractFieldException, Exception;

    @NotNull
    Task create(@Nullable String userId, @Nullable String name) throws AbstractFieldException, Exception;

    @Nullable
    Task updateById(@Nullable String userId, @Nullable String id, @Nullable String name, @Nullable String description) throws AbstractException, Exception;

    @Nullable
    Task updateByIndex(@Nullable String userId, @Nullable Integer index, @Nullable String name, @Nullable String description) throws AbstractException, Exception;

    @Nullable
    Task changeTaskStatusById(@Nullable String userId, @Nullable String id, @Nullable Status status) throws AbstractException, Exception;

    @Nullable
    Task changeTaskStatusByIndex(@Nullable String userId, @Nullable Integer index, @Nullable Status status) throws AbstractException, Exception;

    void updateProjectIdById(@NotNull String userId, @Nullable String taskId, @Nullable String projectId) throws Exception;

    @Nullable
    Task removeById(@Nullable final String userId, @Nullable final String id);

    @Nullable
    Task findById(@Nullable final String userId, @Nullable final String id);

}
