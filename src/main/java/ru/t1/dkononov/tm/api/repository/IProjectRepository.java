package ru.t1.dkononov.tm.api.repository;

import org.jetbrains.annotations.NotNull;
import ru.t1.dkononov.tm.model.Project;

public interface IProjectRepository extends IUserOwnedRepository<Project> {

    @NotNull
    Project create(@NotNull String userId, @NotNull String name);

    @NotNull
    Project create(@NotNull String userId, @NotNull String name, @NotNull String description);


}
