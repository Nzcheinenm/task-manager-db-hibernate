package ru.t1.dkononov.tm.api;

import ru.t1.dkononov.tm.model.Task;

public interface ITaskController {
    void showTasks();

    void addTask();

    void clearTasks();

    void showTaskById();

    void showTaskByIndex();

    void removeTaskById();

    void removeTaskByIndex();

    void updateTaskById();

    void updateTaskByIndex();

    String show(final Task task);

}
