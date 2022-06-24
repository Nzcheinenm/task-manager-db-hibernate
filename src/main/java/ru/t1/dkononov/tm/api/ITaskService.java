package ru.t1.dkononov.tm.api;

import ru.t1.dkononov.tm.model.Task;

import java.util.List;

public interface ITaskService {
    List<Task> findAll();

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

}
