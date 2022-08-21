package ru.t1.dkononov.tm.api.repository;

import org.jetbrains.annotations.NotNull;
import ru.t1.dkononov.tm.model.Task;

import java.util.List;

public interface ITaskRepository extends IUserOwnedRepository<Task> {

    @NotNull
    Task create(@NotNull final String userId,@NotNull final String name);

    @NotNull
    Task create(@NotNull final String userId,@NotNull final String name, final String description);

    @NotNull
    List<Task> findAllByProjectId(@NotNull final String userId,@NotNull final String projectId);
}
