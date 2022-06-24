package ru.t1.dkononov.tm.api;

import ru.t1.dkononov.tm.model.Project;

import java.util.List;

public interface IProjectService {
    List<Project> findAll();

    Project add(final Project project);

    void clear();

    Project create(final String name, final String description);

    Project create(final String name);

    Project findById(final String id);

    Project findByIndex(final Integer index);

    void remove(final Project project);

    Project removeById(final String id);

    Project removeByIndex(final Integer index);

    Project updateById(final String id, final String name, final String description);

    Project updateByIndex(final Integer index, final String name, final String description);

}
