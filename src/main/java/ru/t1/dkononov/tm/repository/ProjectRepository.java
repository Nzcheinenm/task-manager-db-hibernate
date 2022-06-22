package ru.t1.dkononov.tm.repository;

import ru.t1.dkononov.tm.api.IProjectRepository;
import ru.t1.dkononov.tm.model.Project;

import java.util.ArrayList;
import java.util.List;

public class ProjectRepository implements IProjectRepository {

    private List<Project> projects = new ArrayList<>();

    @Override
    public List<Project> findAll() {
        return projects;
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

}
