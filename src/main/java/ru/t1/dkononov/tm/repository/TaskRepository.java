package ru.t1.dkononov.tm.repository;

import ru.t1.dkononov.tm.api.repository.ITaskRepository;
import ru.t1.dkononov.tm.model.Task;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public final class TaskRepository implements ITaskRepository {

    private final List<Task> tasks = new ArrayList<>();

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

    @Override
    public Task create(final String name) {
        final Task task = new Task();
        task.setName(name);
        return add(task);
    }

    @Override
    public Task create(final String name, final String description) {
        final Task task = new Task();
        task.setName(name);
        task.setDescription(description);
        return add(task);
    }

    @Override
    public Task findById(final String id) {
        for (final Task task : tasks) {
            if (Objects.equals(id, task.getId())) {
                return task;
            }
        }
        return null;
    }

    @Override
    public Task findByIndex(final Integer index) {
        return tasks.get(index);
    }

    @Override
    public Task remove(final Task task) {
        if (task == null) return null;
        tasks.remove(task);
        return task;
    }

    @Override
    public Task removeById(final String id) {
        final Task task = findById(id);
        if (task == null) return null;
        remove(task);
        return task;
    }

    @Override
    public Task removeByIndex(final Integer index) {
        final Task task = findByIndex(index);
        if (task == null) return null;
        remove(task);
        return task;
    }

}
