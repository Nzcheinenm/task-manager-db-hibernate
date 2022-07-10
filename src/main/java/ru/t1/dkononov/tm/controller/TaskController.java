package ru.t1.dkononov.tm.controller;

import ru.t1.dkononov.tm.api.controllers.ITaskController;
import ru.t1.dkononov.tm.api.services.ITaskService;
import ru.t1.dkononov.tm.enumerated.Sort;
import ru.t1.dkononov.tm.enumerated.Status;
import ru.t1.dkononov.tm.exception.AbstractException;
import ru.t1.dkononov.tm.exception.field.AbstractFieldException;
import ru.t1.dkononov.tm.model.Project;
import ru.t1.dkononov.tm.model.Task;
import ru.t1.dkononov.tm.util.TerminalUtil;

import java.util.Arrays;
import java.util.List;

public final class TaskController implements ITaskController {

    private final ITaskService taskService;

    public TaskController(final ITaskService taskService) {
        this.taskService = taskService;
    }

    @Override
    public void showTasks() {
        System.out.println("[SHOW TASKS]");
        System.out.println("[ENTER SORT: ]");
        System.out.println(Arrays.toString(Sort.values()));
        final String sortType = TerminalUtil.inLine();
        final Sort sort = Sort.toSort(sortType);
        final List<Task> tasks = taskService.findAll(sort);
        showTasks(tasks);
    }

    @Override
    public void addTask() throws AbstractFieldException {
        System.out.println("[CREATE NEW TASK]");
        System.out.println("ENTER NAME:");
        final String name = TerminalUtil.inLine();
        System.out.println("ENTER DESCRIPTION:");
        final String description = TerminalUtil.inLine();
        taskService.create(name, description);
    }

    @Override
    public void clearTasks() {
        System.out.println("[CLEAR LIST TASKS]");
        taskService.clear();
        System.out.println("[OK]");
    }

    @Override
    public void showTaskById() throws AbstractFieldException {
        System.out.println("[SHOW TASK]");
        System.out.println("[ENTER ID]");
        final String scanner = TerminalUtil.inLine();
        final Task task = taskService.findById(scanner);
        System.out.println(show(task));
    }

    @Override
    public void showTaskByIndex() throws AbstractFieldException {
        System.out.println("[SHOW TASK]");
        System.out.println("[ENTER INDEX]");
        final Integer value = TerminalUtil.nextNumber() - 1;
        final Task task = taskService.findByIndex(value);
        System.out.println(show(task));
    }

    @Override
    public void showTaskByProjectId() {
        System.out.println("[TASK LIST BY PROJECT ID]");
        System.out.println("[ENTER PROJECT ID:]");
        final String projectId = TerminalUtil.inLine();
        final List<Task> tasks = taskService.findAllByProjectId(projectId);
        showTasks(tasks);
    }

    @Override
    public void removeTaskById() throws AbstractFieldException {
        System.out.println("[ENTER ID]");
        final String value = TerminalUtil.inLine();
        taskService.removeById(value);
    }

    @Override
    public void removeTaskByIndex() throws AbstractFieldException {
        System.out.println("[ENTER INDEX]");
        final Integer value = TerminalUtil.nextNumber() - 1;
        taskService.removeByIndex(value);
    }

    @Override
    public void updateTaskById() throws AbstractException {
        System.out.println("[ENTER ID]");
        final String id = TerminalUtil.inLine();
        System.out.println("[ENTER NAME]");
        final String name = TerminalUtil.inLine();
        System.out.println("[ENTER DESCRIPTION]");
        final String description = TerminalUtil.inLine();
        taskService.updateById(id, name, description);
    }

    @Override
    public void updateTaskByIndex() throws AbstractException {
        System.out.println("[ENTER INDEX]");
        final Integer index = TerminalUtil.nextNumber() - 1;
        System.out.println("[ENTER NAME]");
        final String name = TerminalUtil.inLine();
        System.out.println("[ENTER DESCRIPTION]");
        final String description = TerminalUtil.inLine();
        taskService.updateByIndex(index, name, description);
    }

    @Override
    public void changeTaskStatusById() throws AbstractException {
        System.out.println("[CHANGE PROJECT STATUS BY ID]");
        System.out.println("ENTER ID:");
        final String id = TerminalUtil.inLine();
        System.out.println("ENTER STATUS:");
        System.out.println(Arrays.toString(Status.values()));
        final String statusValue = TerminalUtil.inLine();
        final Status status = Status.toStatus(statusValue);
        taskService.changeTaskStatusById(id, status);
    }

    @Override
    public void changeTaskStatusByIndex() throws AbstractException {
        System.out.println("[CHANGE PROJECT STATUS BY INDEX]");
        System.out.println("ENTER INDEX:");
        final Integer index = TerminalUtil.nextNumber() - 1;
        System.out.println("ENTER STATUS:");
        System.out.println(Arrays.toString(Status.values()));
        final String statusValue = TerminalUtil.inLine();
        final Status status = Status.toStatus(statusValue);
        taskService.changeTaskStatusByIndex(index, status);
    }

    @Override
    public void completeTaskById() throws AbstractException {
        System.out.println("[COMPLETE PROJECT BY ID]");
        System.out.println("ENTER ID:");
        final String id = TerminalUtil.inLine();
        taskService.changeTaskStatusById(id, Status.COMPLETED);
    }

    @Override
    public void completeTaskByIndex() throws AbstractException {
        System.out.println("[COMPLETE PROJECT BY INDEX]");
        System.out.println("ENTER INDEX:");
        final Integer index = TerminalUtil.nextNumber() - 1;
        taskService.changeTaskStatusByIndex(index, Status.COMPLETED);
    }

    @Override
    public void startTaskById() throws AbstractException {
        System.out.println("[IN PROGRESS PROJECT BY ID]");
        System.out.println("ENTER ID:");
        final String id = TerminalUtil.inLine();
        taskService.changeTaskStatusById(id, Status.IN_PROGRESS);
    }

    @Override
    public void startTaskByIndex() throws AbstractException {
        System.out.println("[IN PROGRESS PROJECT BY INDEX]");
        System.out.println("ENTER INDEX:");
        final Integer index = TerminalUtil.nextNumber() - 1;
        taskService.changeTaskStatusByIndex(index, Status.IN_PROGRESS);
    }

    @Override
    public String show(final Task task) {
        return "[ID: " + task.getId() + "]\n" +
                "[NAME: " + task.getName() + "]\n" +
                "[DESC: " + task.getDescription() + "]\n" +
                "[STATUS: " + task.getStatus() + "]";
    }

    @Override
    public void showTasks(final List<Task> tasks) {
        int index = 1;
        for (final Task task : tasks) {
            if (task == null) continue;
            System.out.println(index + ". " + show(task));
            index++;
        }
    }

}
