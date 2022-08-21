package ru.t1.dkononov.tm.repository;

import org.jetbrains.annotations.NotNull;
import ru.t1.dkononov.tm.api.repository.IProjectRepository;
import ru.t1.dkononov.tm.model.Project;

public final class ProjectRepository extends AbstractUserOwnedRepository<Project>
        implements IProjectRepository {


    @NotNull
    @Override
    public Project create(@NotNull final String userId,@NotNull final String name) {
        @NotNull final Project project = new Project();
        project.setName(name);
        project.setUserId(userId);
        return add(project);
    }

    @NotNull
    @Override
    public Project create(
            @NotNull final String userId,
            @NotNull final String name,
            @NotNull final String description
    ) {
        @NotNull final Project project = new Project();
        project.setName(name);
        project.setDescription(description);
        project.setUserId(userId);
        return add(project);
    }

}
