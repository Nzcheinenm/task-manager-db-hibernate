package ru.t1.dkononov.tm.api.repository;

import org.jetbrains.annotations.NotNull;
import ru.t1.dkononov.tm.model.Project;

public interface IProjectRepository extends IUserOwnedRepository<Project> {

    @NotNull
    Project create(@NotNull final String userId, @NotNull final String name);

    @NotNull
    Project create(@NotNull final String userId, @NotNull final String name, final String description);


}
