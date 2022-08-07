package ru.t1.dkononov.tm.repository;

import ru.t1.dkononov.tm.api.repository.IProjectRepository;
import ru.t1.dkononov.tm.model.Project;

public final class ProjectRepository extends AbstractRepository<Project> implements IProjectRepository {


    @Override
    public Project create(final String name) {
        final Project project = new Project();
        project.setName(name);
        return add(project);
    }

    @Override
    public Project create(final String name, final String description) {
        final Project project = new Project();
        project.setName(name);
        project.setDescription(description);
        return add(project);
    }

}
