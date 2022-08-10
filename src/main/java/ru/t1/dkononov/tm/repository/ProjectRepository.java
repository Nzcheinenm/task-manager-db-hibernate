package ru.t1.dkononov.tm.repository;

import ru.t1.dkononov.tm.api.repository.IProjectRepository;
import ru.t1.dkononov.tm.model.Project;

public final class ProjectRepository extends AbstractUserOwnedRepository<Project> implements IProjectRepository {


    @Override
    public Project create(final String userId, final String name) {
        final Project project = new Project();
        project.setName(name);
        project.setUserId(userId);
        return add(project);
    }

    @Override
    public Project create(final String userId, final String name, final String description) {
        final Project project = new Project();
        project.setName(name);
        project.setDescription(description);
        project.setUserId(userId);
        return add(project);
    }

}
