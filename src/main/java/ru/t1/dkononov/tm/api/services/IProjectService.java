package ru.t1.dkononov.tm.api.services;

import ru.t1.dkononov.tm.enumerated.Sort;
import ru.t1.dkononov.tm.enumerated.Status;
import ru.t1.dkononov.tm.model.Project;
import ru.t1.dkononov.tm.model.Task;

import java.util.Comparator;
import java.util.List;

public interface IProjectService {
    List<Project> findAll();

    List<Project> findAll(Comparator<Project> comparator);

    List<Project> findAll(Sort sort);

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

    Project changeProjectStatusById(String id, Status status);

    Project changeProjectStatusByIndex(Integer index, Status status);

}
