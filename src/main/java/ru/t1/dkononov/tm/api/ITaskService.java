package ru.t1.dkononov.tm.api;

import ru.t1.dkononov.tm.model.Task;

import java.util.List;

public interface ITaskService {
    List<Task> findAll();

    Task create(final String name, final String description);

    Task add(final Task project);

    void clear();

}
