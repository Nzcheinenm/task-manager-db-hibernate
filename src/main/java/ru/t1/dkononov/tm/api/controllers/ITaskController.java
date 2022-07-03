package ru.t1.dkononov.tm.api.controllers;

import ru.t1.dkononov.tm.model.Task;

import java.util.List;

public interface ITaskController {
    void showTasks();

    void addTask();

    void clearTasks();

    void showTaskById();

    void showTaskByIndex();

    void showTaskByProjectId();

    void removeTaskById();

    void removeTaskByIndex();

    void updateTaskById();

    void updateTaskByIndex();

    void changeTaskStatusById();

    void changeTaskStatusByIndex();

    void completeTaskById();

    void completeTaskByIndex();

    void startTaskById();

    void startTaskByIndex();

    String show(final Task task);

    void showTasks(final List<Task> task);

}
