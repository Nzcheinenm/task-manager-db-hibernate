package ru.t1.dkononov.tm.api.services;

import ru.t1.dkononov.tm.enumerated.Status;
import ru.t1.dkononov.tm.model.Project;
import ru.t1.dkononov.tm.model.Task;

import java.util.List;

public interface ITaskService {
    List<Task> findAll();

    List<Task> findAllByProjectId(String projectId);

    Task add(final Task project);

    void clear();

    Task create(final String name, final String description);

    Task create(final String name);

    Task findById(final String id);

    Task findByIndex(final Integer index);

    void remove(final Task task);

    Task removeById(final String id);

    Task removeByIndex(final Integer index);

    Task updateById(final String id, final String name, final String description);

    Task updateByIndex(final Integer index, final String name, final String description);

    Task changeTaskStatusById(String id, Status status);

    Task changeTaskStatusByIndex(Integer index, Status status);

}
