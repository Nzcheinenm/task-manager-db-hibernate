package ru.t1.dkononov.tm.service;

import ru.t1.dkononov.tm.api.IProjectRepository;
import ru.t1.dkononov.tm.api.IProjectService;
import ru.t1.dkononov.tm.model.Project;

import java.util.List;

public final class ProjectService implements IProjectService {

    private final IProjectRepository projectRepository;

    public ProjectService(final IProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    @Override
    public List<Project> findAll() {
        return projectRepository.findAll();
    }

    @Override
    public Project create(final String name, final String description) {
        if (name == null) return null;
        if (description == null) return null;
        final Project project = new Project();
        project.setName(name);
        project.setDescription(description);
        add(project);
        return project;
    }

    @Override
    public Project add(final Project project) {
        if (project == null) return null;
        return projectRepository.add(project);
    }

    @Override
    public void clear() {
        projectRepository.clear();
    }

}
