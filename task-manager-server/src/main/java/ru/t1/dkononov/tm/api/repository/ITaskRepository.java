package ru.t1.dkononov.tm.api.repository;

import org.jetbrains.annotations.NotNull;
import ru.t1.dkononov.tm.model.Task;

import java.util.List;

public interface ITaskRepository extends IUserOwnedRepository<Task> {

    @NotNull
    Task create(@NotNull String userId, @NotNull String name);

    @NotNull
    Task create(@NotNull String userId, @NotNull String name, String description);

    @NotNull
    List<Task> findAllByProjectId(@NotNull String userId, @NotNull String projectId);
}
