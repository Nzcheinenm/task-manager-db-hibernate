package ru.t1.dkononov.tm.controller;

import ru.t1.dkononov.tm.api.ITaskController;
import ru.t1.dkononov.tm.api.ITaskService;
import ru.t1.dkononov.tm.model.Task;
import ru.t1.dkononov.tm.util.TerminalUtil;

import java.util.List;

public final class TaskController implements ITaskController {

    private final ITaskService taskService;

    public TaskController(final ITaskService taskService) {
        this.taskService = taskService;
    }

    @Override
    public void showTasks() {
        System.out.println("[SHOW TASKS]");
        int index = 0;
        final List<Task> tasks = taskService.findAll();
        for (final Task task : tasks) {
            index++;
            System.out.println(index + ". " + task.getName());
        }
        System.out.println("[OK]");
    }

    @Override
    public void addTask() {
        System.out.println("[CREATE NEW TASK]");
        System.out.println("ENTER NAME:");
        final String name = TerminalUtil.inLine();
        System.out.println("ENTER DESCRIPTION:");
        final String description = TerminalUtil.inLine();
        final Task task = taskService.create(name, description);
        if (task == null) {
            System.out.println("[FAIL]");
            return;
        }
        System.out.println("[OK]");
    }

    @Override
    public void clearTasks() {
        System.out.println("[CLEAR LIST TASKS]");
        taskService.clear();
        System.out.println("[OK]");
    }

    @Override
    public void showTaskById() {
        System.out.println("[SHOW TASK]");
        System.out.println("[ENTER ID]");
        final String scanner = TerminalUtil.inLine();
        final Task task = taskService.findById(scanner);
        if (task == null) System.out.println("[FAIL]");
        else {
            System.out.println(show(task));
            System.out.println("[OK]");
        }
    }

    @Override
    public void showTaskByIndex() {
        System.out.println("[SHOW TASK]");
        System.out.println("[ENTER INDEX]");
        final Integer value = TerminalUtil.nextNumber() - 1;
        final Task task = taskService.findByIndex(value);
        if (task == null) System.out.println("[FAIL]");
        else {
            System.out.println(show(task));
            System.out.println("[OK]");
        }
    }

    @Override
    public void removeTaskById() {
        System.out.println("[ENTER ID]");
        final String value = TerminalUtil.inLine();
        final Task task = taskService.removeById(value);
        if (task == null) System.out.println("[FAIL]");
        else {
            System.out.println("[OK]");
        }
    }

    @Override
    public void removeTaskByIndex() {
        System.out.println("[ENTER INDEX]");
        final Integer value = TerminalUtil.nextNumber() - 1;
        final Task task = taskService.removeByIndex(value);
        if (task == null) System.out.println("[FAIL]");
        else {
            System.out.println("[OK]");
        }
    }

    @Override
    public void updateTaskById() {
        System.out.println("[ENTER ID]");
        final String id = TerminalUtil.inLine();
        System.out.println("[ENTER NAME]");
        final String name = TerminalUtil.inLine();
        System.out.println("[ENTER DESCRIPTION]");
        final String description = TerminalUtil.inLine();
        final Task task = taskService.updateById(id, name, description);
        if (task == null) System.out.println("[FAIL]");
        else {
            System.out.println("[OK]");
        }
    }

    @Override
    public void updateTaskByIndex() {
        System.out.println("[ENTER INDEX]");
        final Integer index = TerminalUtil.nextNumber() - 1;
        System.out.println("[ENTER NAME]");
        final String name = TerminalUtil.inLine();
        System.out.println("[ENTER DESCRIPTION]");
        final String description = TerminalUtil.inLine();
        final Task task = taskService.updateByIndex(index, name, description);
        if (task == null) System.out.println("[FAIL]");
        else {
            System.out.println("[OK]");
        }
    }

    @Override
    public String show(final Task task) {
        return "[ID: " + task.getId() + "]\n" +
                "[NAME: " + task.getName() + "]\n" +
                "[DESC: " + task.getDescription() + "]";
    }

}
