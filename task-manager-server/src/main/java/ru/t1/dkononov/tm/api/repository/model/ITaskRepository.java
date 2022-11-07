package ru.t1.dkononov.tm.api.repository.model;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.t1.dkononov.tm.enumerated.Sort;
import ru.t1.dkononov.tm.model.Task;

import java.util.Comparator;
import java.util.List;

public interface ITaskRepository {
    void clear();

    void clear(@NotNull String userId);

    @NotNull List<Task> findAll();

    @NotNull List<Task> findAll(@NotNull String userId);

    @NotNull List<Task> findAll(@NotNull String userId, @NotNull Sort sort);

    @NotNull List<Task> findAll(@NotNull String userId, @NotNull Comparator<Task> comparator);

    @NotNull Task findById(@NotNull String id);

    @Nullable Task findById(@NotNull String userId, @NotNull String id);

    @NotNull Task findByIndex(@NotNull Integer index);

    void removeById(@NotNull String id);

    void removeById(@NotNull String userId, @NotNull String id);

    @NotNull List<Task> findAllByProjectId(@NotNull String userId, @NotNull String projectId);

    @Nullable Task findTaskIdByProjectId(@NotNull String userId, @NotNull String projectId, @NotNull String taskId);
}
