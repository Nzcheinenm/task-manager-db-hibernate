package ru.t1.dkononov.tm.api.controllers;

import ru.t1.dkononov.tm.exception.AbstractException;
import ru.t1.dkononov.tm.exception.field.AbstractFieldException;
import ru.t1.dkononov.tm.model.Task;

import java.util.List;

public interface ITaskController {
    void showTasks();

    void addTask() throws AbstractFieldException;

    void clearTasks();

    void showTaskById() throws AbstractFieldException;

    void showTaskByIndex() throws AbstractFieldException;

    void showTaskByProjectId();

    void removeTaskById() throws AbstractFieldException;

    void removeTaskByIndex() throws AbstractFieldException;

    void updateTaskById() throws AbstractException;

    void updateTaskByIndex() throws AbstractException;

    void changeTaskStatusById() throws AbstractException;

    void changeTaskStatusByIndex() throws AbstractException;

    void completeTaskById() throws AbstractException;

    void completeTaskByIndex() throws AbstractException;

    void startTaskById() throws AbstractException;

    void startTaskByIndex() throws AbstractException;

    String show(final Task task);

    void showTasks(final List<Task> task);

}
