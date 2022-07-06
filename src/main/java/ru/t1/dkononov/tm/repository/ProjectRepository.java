package ru.t1.dkononov.tm.repository;

import ru.t1.dkononov.tm.api.repository.IProjectRepository;
import ru.t1.dkononov.tm.model.Project;
import ru.t1.dkononov.tm.model.Task;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

public final class ProjectRepository implements IProjectRepository {

    private final List<Project> projects = new ArrayList<>();

    @Override
    public List<Project> findAll() {
        return projects;
    }

    @Override
    public List<Project> findAll(final Comparator<Project> comparator) {
        final List<Project> result = new ArrayList<>(projects);
        result.sort(comparator);
        return result;
    }

    @Override
    public Project add(final Project project) {
        projects.add(project);
        return project;
    }

    @Override
    public void clear() {
        projects.clear();
    }

    @Override
    public boolean existsById(final String id) {
        return findById(id) != null;
    }

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

    @Override
    public Project findById(final String id) {
        for (final Project project : projects) {
            if (Objects.equals(id, project.getId())) {
                return project;
            }
        }
        return null;
    }

    @Override
    public Project findByIndex(final Integer index) {
        return projects.get(index);
    }

    @Override
    public Project remove(final Project project) {
        if (project == null) return null;
        projects.remove(project);
        return project;
    }

    @Override
    public Project removeById(final String id) {
        final Project project = findById(id);
        if (project == null) return null;
        remove(project);
        return project;
    }

    @Override
    public Project removeByIndex(final Integer index) {
        final Project project = findByIndex(index);
        if (project == null) return null;
        remove(project);
        return project;
    }

}
