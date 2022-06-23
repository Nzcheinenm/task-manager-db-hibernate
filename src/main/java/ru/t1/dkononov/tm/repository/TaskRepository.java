package ru.t1.dkononov.tm.repository;

import ru.t1.dkononov.tm.api.ITaskRepository;
import ru.t1.dkononov.tm.model.Task;

import java.util.ArrayList;
import java.util.List;

public final class TaskRepository implements ITaskRepository {

    private List<Task> tasks = new ArrayList<>();

    @Override
    public List<Task> findAll() {
        return tasks;
    }

    @Override
    public Task add(final Task task) {
        tasks.add(task);
        return task;
    }

    @Override
    public void clear() {
        tasks.clear();
    }

}
