package ru.t1.dkononov.tm.api.services;

import lombok.SneakyThrows;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.t1.dkononov.tm.enumerated.Sort;
import ru.t1.dkononov.tm.enumerated.Status;
import ru.t1.dkononov.tm.exception.AbstractException;
import ru.t1.dkononov.tm.exception.entity.ProjectNotFoundException;
import ru.t1.dkononov.tm.exception.field.AbstractFieldException;
import ru.t1.dkononov.tm.exception.field.UserIdEmptyException;
import ru.t1.dkononov.tm.model.Task;

import java.util.Collection;
import java.util.Comparator;
import java.util.List;

public interface ITaskService {

    @NotNull
    @SneakyThrows
    List<Task> findAll(@Nullable String userId) throws UserIdEmptyException;

    @Nullable
    @SneakyThrows
    List<Task> findAll(
            @Nullable String userId,
            @Nullable Comparator comparator
    ) throws UserIdEmptyException;

    @NotNull
    @SneakyThrows
    List<Task> findAll(
            @Nullable String userId,
            @Nullable Sort sort
    ) throws UserIdEmptyException;

    @NotNull
    @SneakyThrows
    List<Task> findAll();

    @Nullable
    @SneakyThrows
    Task add(@Nullable String userId, @Nullable Task model)
            throws ProjectNotFoundException, UserIdEmptyException;

    @Nullable
    @SneakyThrows
    Collection<Task> add(@NotNull Collection<Task> models);

    @NotNull
    @SneakyThrows
    Collection<Task> set(@NotNull Collection<Task> models);

    @SneakyThrows
    void clear(@Nullable String userId) throws UserIdEmptyException;

    @SneakyThrows
    boolean existsById(@Nullable String userId, @Nullable String id) throws UserIdEmptyException;

    @Nullable
    @SneakyThrows
    Task remove(@NotNull String userId, @Nullable Task model) throws UserIdEmptyException;

    @Nullable
    @SneakyThrows
    Task findById(@Nullable String userId, @Nullable String id)
            throws AbstractException;

    @Nullable
    @SneakyThrows
    Task findByIndex(@Nullable String userId, @Nullable Integer index)
            throws AbstractException;

    @NotNull
    @SneakyThrows
    Task removeById(@Nullable String userId, @Nullable String id) throws AbstractException;

    @NotNull
    @SneakyThrows
    Task removeByIndex(@Nullable String userId, @Nullable Integer index) throws AbstractException;

    @SneakyThrows
    void removeAll(@Nullable String userId) throws UserIdEmptyException;

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

    void updateProjectIdById(@NotNull String userId, @Nullable String taskId, @Nullable String projectId) throws Exception;

}
