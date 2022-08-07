package ru.t1.dkononov.tm.api.repository;

import ru.t1.dkononov.tm.model.Task;

import java.util.List;

public interface ITaskRepository extends IRepository<Task> {

    Task create(final String name);

    Task create(final String name, final String description);

    List<Task> findAllByProjectId(final String projectId);
}
