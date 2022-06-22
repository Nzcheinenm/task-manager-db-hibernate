package ru.t1.dkononov.tm.api;

import ru.t1.dkononov.tm.model.Task;

import java.util.List;

public interface ITaskRepository {
    List<Task> findAll();

    Task add(Task task);

    void clear();
}
