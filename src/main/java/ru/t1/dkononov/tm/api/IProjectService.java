package ru.t1.dkononov.tm.api;

import ru.t1.dkononov.tm.model.Project;

import java.util.List;

public interface IProjectService {
    List<Project> findAll();

    Project create(String name, String description);

    Project add(Project project);

    void clear();

}
