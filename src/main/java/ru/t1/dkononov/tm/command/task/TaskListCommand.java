package ru.t1.dkononov.tm.command.task;

import ru.t1.dkononov.tm.enumerated.Sort;
import ru.t1.dkononov.tm.exception.AbstractException;
import ru.t1.dkononov.tm.model.Task;
import ru.t1.dkononov.tm.util.TerminalUtil;

import java.util.Arrays;
import java.util.List;

public final class TaskListCommand extends AbstractTaskCommand {

    public static final String NAME = "task-list";

    public static final String DESCRIPTION = "Вывести список задач.";

    @Override
    public String getDescription() {
        return DESCRIPTION;
    }

    @Override
    public String getName() {
        return NAME;
    }

    @Override
    public void execute() throws AbstractException {
        final String userId = getUserId();
        System.out.println("[SHOW TASKS]");
        System.out.println("[ENTER SORT: ]");
        System.out.println(Arrays.toString(Sort.values()));
        final String sortType = TerminalUtil.inLine();
        final Sort sort = Sort.toSort(sortType);
        final List<Task> tasks = getTaskService().findAll(userId, sort);
        showTasks(tasks);
    }

    public void showTasks(final List<Task> tasks) {
        int index = 1;
        for (final Task task : tasks) {
            if (task == null) continue;
            System.out.println(index + ". " + show(task));
            index++;
        }
    }

    public String show(final Task task) {
        return "[ID: " + task.getId() + "]\n" +
                "[NAME: " + task.getName() + "]\n" +
                "[DESC: " + task.getDescription() + "]\n" +
                "[STATUS: " + task.getStatus() + "]";
    }

}
