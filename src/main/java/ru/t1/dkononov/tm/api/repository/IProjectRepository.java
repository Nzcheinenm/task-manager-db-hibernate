package ru.t1.dkononov.tm.api.repository;

import ru.t1.dkononov.tm.model.Project;

import java.util.List;
import java.util.Objects;

public interface IProjectRepository {
    List<Project> findAll();

    Project add(final Project project);

    void clear();

    boolean existsById(String id);

    Project create(final String name);

    Project create(final String name, final String description);

    Project findById(final String id);

    Project findByIndex(final Integer index);

    Project remove(final Project project);

    Project removeById(final String id);

    Project removeByIndex(final Integer index);

}
