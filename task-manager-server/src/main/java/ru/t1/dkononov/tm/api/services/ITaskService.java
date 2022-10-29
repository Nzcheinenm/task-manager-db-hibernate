package ru.t1.dkononov.tm.api.services;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.t1.dkononov.tm.enumerated.Status;
import ru.t1.dkononov.tm.exception.AbstractException;
import ru.t1.dkononov.tm.exception.field.AbstractFieldException;
import ru.t1.dkononov.tm.exception.field.UserIdEmptyException;
import ru.t1.dkononov.tm.model.Task;

import java.util.List;

public interface ITaskService extends IUserOwnedService<Task> {

    @NotNull
    List<Task> findAllByProjectId(@Nullable String userId, @Nullable String projectId) throws UserIdEmptyException, Exception;

    @NotNull
    Task create(@Nullable String userId, @Nullable String name, @Nullable String description) throws AbstractFieldException, Exception;

    @NotNull
    Task create(@Nullable String userId, @Nullable String name) throws AbstractFieldException, Exception;

    Task updateById(@Nullable String userId, @Nullable String id, @Nullable String name, @Nullable String description) throws AbstractException, Exception;

    Task updateByIndex(@Nullable String userId, @Nullable Integer index, @Nullable String name, @Nullable String description) throws AbstractException, Exception;

    Task changeTaskStatusById(@Nullable String userId, @Nullable String id, @Nullable Status status) throws AbstractException, Exception;

    Task changeTaskStatusByIndex(@Nullable String userId, @Nullable Integer index, @Nullable Status status) throws AbstractException, Exception;

    void updateProjectIdById(@NotNull String userId,@Nullable String taskId, @Nullable String projectId) throws Exception;

}
