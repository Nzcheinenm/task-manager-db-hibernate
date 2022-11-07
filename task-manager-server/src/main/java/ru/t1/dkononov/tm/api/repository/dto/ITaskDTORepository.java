package ru.t1.dkononov.tm.api.repository.dto;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.t1.dkononov.tm.dto.model.TaskDTO;
import ru.t1.dkononov.tm.enumerated.Sort;

import java.util.Comparator;
import java.util.List;

public interface ITaskDTORepository {
    void clear();

    void clear(@NotNull String userId);

    @NotNull List<TaskDTO> findAll();

    @NotNull List<TaskDTO> findAll(@NotNull String userId);

    @NotNull List<TaskDTO> findAll(@NotNull String userId, @NotNull Sort sort);

    @NotNull List<TaskDTO> findAll(@NotNull String userId, @NotNull Comparator<TaskDTO> comparator);

    @NotNull TaskDTO findById(@NotNull String id);

    @Nullable TaskDTO findById(@NotNull String userId, @NotNull String id);

    @NotNull TaskDTO findByIndex(@NotNull Integer index);

    void removeById(@NotNull String id);

    void removeById(@NotNull String userId, @NotNull String id);

    @NotNull List<TaskDTO> findAllByProjectId(@NotNull String userId, @NotNull String projectId);

    @Nullable TaskDTO findTaskIdByProjectId(@NotNull String userId, @NotNull String projectId, @NotNull String taskId);
}
