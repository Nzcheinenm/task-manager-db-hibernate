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
        for (Task task : tasks) {
            index++;
            System.out.println(index + ". " + task.getName());
        }
        System.out.println("[OK]");
    }

    @Override
    public void addTask() {
        System.out.println("[CREATE NEW TASK]");
        System.out.println("ENTER NAME:");
        String name = TerminalUtil.inLine();
        System.out.println("ENTER DESCRIPTION:");
        String description = TerminalUtil.inLine();
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

}
