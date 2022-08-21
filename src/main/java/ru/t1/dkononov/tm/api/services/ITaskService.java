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
    List<Task> findAllByProjectId(@Nullable String userId,@Nullable String projectId) throws UserIdEmptyException;

    @NotNull
    Task create(@Nullable String userId,@Nullable String name,@Nullable String description) throws AbstractFieldException;

    @NotNull
    Task create(@Nullable String userId,@Nullable String name) throws AbstractFieldException;

    void updateById(@Nullable String userId,@Nullable String id,@Nullable String name,@Nullable String description) throws AbstractException;

    void updateByIndex(@Nullable String userId,@Nullable Integer index,@Nullable String name,@Nullable String description) throws AbstractException;

    void changeTaskStatusById(@Nullable String userId,@Nullable String id,@Nullable Status status) throws AbstractException;

    void changeTaskStatusByIndex(@Nullable String userId,@Nullable Integer index,@Nullable Status status) throws AbstractException;

}
