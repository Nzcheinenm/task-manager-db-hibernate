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
import ru.t1.dkononov.tm.dto.model.TaskDTO;

import java.util.Collection;
import java.util.Comparator;
import java.util.List;

public interface ITaskService {

    @NotNull
    @SneakyThrows
    List<TaskDTO> findAll(@Nullable String userId) throws UserIdEmptyException;

    @Nullable
    @SneakyThrows
    List<TaskDTO> findAll(
            @Nullable String userId,
            @Nullable Comparator comparator
    ) throws UserIdEmptyException;

    @NotNull
    @SneakyThrows
    List<TaskDTO> findAll(
            @Nullable String userId,
            @Nullable Sort sort
    ) throws UserIdEmptyException;

    @NotNull
    @SneakyThrows
    List<TaskDTO> findAll();

    @Nullable
    @SneakyThrows
    TaskDTO add(@Nullable String userId, @Nullable TaskDTO model)
            throws ProjectNotFoundException, UserIdEmptyException;

    @Nullable
    @SneakyThrows
    Collection<TaskDTO> add(@NotNull Collection<TaskDTO> models);

    @NotNull
    @SneakyThrows
    Collection<TaskDTO> set(@NotNull Collection<TaskDTO> models);

    @SneakyThrows
    void clear(@Nullable String userId) throws UserIdEmptyException;

    @SneakyThrows
    boolean existsById(@Nullable String userId, @Nullable String id) throws UserIdEmptyException;

    @Nullable
    @SneakyThrows
    TaskDTO remove(@NotNull String userId, @Nullable TaskDTO model) throws UserIdEmptyException;

    @Nullable
    @SneakyThrows
    TaskDTO findById(@Nullable String userId, @Nullable String id)
            throws AbstractException;

    @Nullable
    @SneakyThrows
    TaskDTO findByIndex(@Nullable String userId, @Nullable Integer index)
            throws AbstractException;

    @NotNull
    @SneakyThrows
    TaskDTO removeById(@Nullable String userId, @Nullable String id) throws AbstractException;

    @NotNull
    @SneakyThrows
    TaskDTO removeByIndex(@Nullable String userId, @Nullable Integer index) throws AbstractException;

    @SneakyThrows
    void removeAll(@Nullable String userId) throws UserIdEmptyException;

    @NotNull
    List<TaskDTO> findAllByProjectId(@Nullable String userId, @Nullable String projectId) throws UserIdEmptyException, Exception;

    @NotNull
    TaskDTO create(@Nullable String userId, @Nullable String name, @Nullable String description) throws AbstractFieldException, Exception;

    @NotNull
    TaskDTO create(@Nullable String userId, @Nullable String name) throws AbstractFieldException, Exception;

    TaskDTO updateById(@Nullable String userId, @Nullable String id, @Nullable String name, @Nullable String description) throws AbstractException, Exception;

    TaskDTO updateByIndex(@Nullable String userId, @Nullable Integer index, @Nullable String name, @Nullable String description) throws AbstractException, Exception;

    TaskDTO changeTaskStatusById(@Nullable String userId, @Nullable String id, @Nullable Status status) throws AbstractException, Exception;

    TaskDTO changeTaskStatusByIndex(@Nullable String userId, @Nullable Integer index, @Nullable Status status) throws AbstractException, Exception;

    void updateProjectIdById(@NotNull String userId, @Nullable String taskId, @Nullable String projectId) throws Exception;

}
