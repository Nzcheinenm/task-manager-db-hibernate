package ru.t1.dkononov.tm.api.services.dto;

import lombok.SneakyThrows;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.t1.dkononov.tm.dto.model.TaskDTO;
import ru.t1.dkononov.tm.enumerated.Sort;
import ru.t1.dkononov.tm.enumerated.Status;

import java.util.List;

public interface ITaskDTOService extends IUserOwnedDTOService<TaskDTO> {
    @NotNull
    @SneakyThrows
    List<TaskDTO> findAll(
            @Nullable Sort sort
    );

    @NotNull List<TaskDTO> findAllByProjectId(@Nullable String userId, @Nullable String projectId)
            throws Exception;

    @NotNull TaskDTO create(@Nullable String userId, @Nullable String name, @Nullable String description)
            throws Exception;

    @NotNull TaskDTO create(@Nullable String userId, @Nullable String name) throws Exception;

    @Nullable TaskDTO updateById(
            @Nullable String userId,
            @Nullable String id,
            @Nullable String name,
            @Nullable String description
    )
            throws Exception;

    @Nullable TaskDTO updateByIndex(
            @Nullable String userId,
            @Nullable Integer index,
            @Nullable String name,
            @Nullable String description
    )
            throws Exception;

    @Nullable TaskDTO changeTaskStatusById(
            @Nullable String userId,
            @Nullable String id,
            @Nullable Status status
    )
            throws Exception;

    @Nullable TaskDTO changeTaskStatusByIndex(
            @Nullable String userId,
            @Nullable Integer index,
            @NotNull Status status
    ) throws Exception;

    void updateProjectIdById(@NotNull String userId, @Nullable String taskId, @Nullable String projectId) throws Exception;

    @NotNull
    TaskDTO removeById(@NotNull String userId, @Nullable String id);

    @NotNull
    TaskDTO findById(@NotNull String userId, @NotNull String taskId);

}
