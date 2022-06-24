package ru.t1.dkononov.tm.service;

import ru.t1.dkononov.tm.api.IProjectRepository;
import ru.t1.dkononov.tm.api.IProjectService;
import ru.t1.dkononov.tm.model.Project;

import java.util.List;
import java.util.Objects;

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
    public Project add(final Project project) {
        if (project == null) return null;
        return projectRepository.add(project);
    }

    @Override
    public void clear() {
        projectRepository.clear();
    }

    @Override
    public Project create(final String name, final String description) {
        if (name == null || name.isEmpty()) return null;
        if (description == null && description.isEmpty()) return null;
        return projectRepository.create(name, description);
    }

    @Override
    public Project create(final String name) {
        if (name == null || name.isEmpty()) return null;
        return projectRepository.create(name);
    }

    @Override
    public Project findById(final String id) {
        if (id == null || id.isEmpty()) return null;
        return projectRepository.findById(id);
    }

    @Override
    public Project findByIndex(final Integer index) {
        if (index == null || index < 0) return null;
        return projectRepository.findByIndex(index);
    }

    @Override
    public void remove(final Project project) {
        projectRepository.remove(project);
    }

    @Override
    public Project removeById(final String id) {
        if (id == null || id.isEmpty()) return null;
        final Project project = projectRepository.findById(id);
        remove(project);
        return project;
    }

    @Override
    public Project removeByIndex(final Integer index) {
        if (index == null || index < 0) return null;
        final Project project = projectRepository.findByIndex(index);
        projectRepository.remove(project);
        return project;
    }

    @Override
    public Project updateById(final String id, final String name, final String description) {
        if (id == null || id.isEmpty()) return null;
        if (name == null || name.isEmpty()) return null;
        if (description == null || description.isEmpty()) return null;
        final Project project = projectRepository.findById(id);
        if (project == null) return null;
        project.setName(name);
        project.setDescription(description);
        return project;
    }

    @Override
    public Project updateByIndex(final Integer index, final String name, final String description) {
        if (index == null || index < 0) return null;
        if (name == null || name.isEmpty()) return null;
        if (description == null || description.isEmpty()) return null;
        final Project project = projectRepository.findByIndex(index);
        if (project == null) return null;
        project.setName(name);
        project.setDescription(description);
        return project;
    }

}
