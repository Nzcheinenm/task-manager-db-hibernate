package ru.t1.dkononov.tm.api.controllers;

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

    void changeTaskStatusById();

    void changeTaskStatusByIndex();

    void completeTaskById();

    void completeTaskByIndex();

    void startTaskById();

    void startTaskByIndex();

    String show(final Task task);

}
