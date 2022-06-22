package ru.t1.dkononov.tm.api;

import ru.t1.dkononov.tm.model.Task;

import java.util.List;

public interface ITaskService {
    List<Task> findAll();

    Task create(String name, String description);

    Task add(Task project);

    void clear();
}
