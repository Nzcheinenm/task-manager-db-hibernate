package ru.t1.dkononov.tm.api;

import ru.t1.dkononov.tm.model.Project;

import java.util.List;

public interface IProjectService {
    List<Project> findAll();

    Project create(final String name, final String description);

    Project add(final Project project);

    void clear();

}
