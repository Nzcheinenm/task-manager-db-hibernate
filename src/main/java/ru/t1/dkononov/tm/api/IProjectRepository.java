package ru.t1.dkononov.tm.api;

import ru.t1.dkononov.tm.model.Project;

import java.util.List;

public interface IProjectRepository {
    List<Project> findAll();

    Project add(Project project);

    void clear();
}
