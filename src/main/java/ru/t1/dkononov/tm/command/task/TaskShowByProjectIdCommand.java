package ru.t1.dkononov.tm.command.task;

import ru.t1.dkononov.tm.exception.AbstractException;
import ru.t1.dkononov.tm.model.Task;
import ru.t1.dkononov.tm.util.TerminalUtil;

import java.util.List;

public final class TaskShowByProjectIdCommand extends AbstractTaskCommand {

    public static final String TASK_SHOW_BY_PROJECT_ID = "task-show-by-project-id";

    @Override
    public String getDescription() {
        return "Вывести задачи с нужным Project Id.";
    }

    @Override
    public String getName() {
        return TASK_SHOW_BY_PROJECT_ID;
    }

    @Override
    public void execute() throws AbstractException {
        System.out.println("[TASK LIST BY PROJECT ID]");
        System.out.println("[ENTER PROJECT ID:]");
        final String projectId = TerminalUtil.inLine();
        final List<Task> tasks = getTaskService().findAllByProjectId(projectId);
        showTasks(tasks);
    }

    public String show(final Task task) {
        return "[ID: " + task.getId() + "]\n" +
                "[NAME: " + task.getName() + "]\n" +
                "[DESC: " + task.getDescription() + "]\n" +
                "[STATUS: " + task.getStatus() + "]";
    }

    public void showTasks(final List<Task> tasks) {
        int index = 1;
        for (final Task task : tasks) {
            if (task == null) continue;
            System.out.println(index + ". " + show(task));
            index++;
        }
    }

}
