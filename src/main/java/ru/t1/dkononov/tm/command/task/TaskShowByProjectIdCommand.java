package ru.t1.dkononov.tm.command.task;

import lombok.Getter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.t1.dkononov.tm.exception.AbstractException;
import ru.t1.dkononov.tm.model.Task;
import ru.t1.dkononov.tm.util.TerminalUtil;

import java.util.List;

public final class TaskShowByProjectIdCommand extends AbstractTaskCommand {

    @Getter
    @NotNull
    public final String NAME = "task-show-by-project-id";

    @Getter
    @NotNull
    public final String DESCRIPTION = "Вывести задачи с нужным Project Id.";

    @Override
    public void execute() throws AbstractException {
        @Nullable final String userId = getUserId();
        System.out.println("[TASK LIST BY PROJECT ID]");
        System.out.println("[ENTER PROJECT ID:]");
        @NotNull final String projectId = TerminalUtil.inLine();
        @NotNull final List<Task> tasks = getTaskService().findAllByProjectId(userId, projectId);
        showTasks(tasks);
    }

    public String show(@NotNull final Task task) {
        return "[ID: " + task.getId() + "]\n" +
                "[NAME: " + task.getName() + "]\n" +
                "[DESC: " + task.getDescription() + "]\n" +
                "[STATUS: " + task.getStatus() + "]";
    }

    public void showTasks(@NotNull final List<Task> tasks) {
        int index = 1;
        for (@Nullable final Task task : tasks) {
            if (task == null) continue;
            System.out.println(index + ". " + show(task));
            index++;
        }
    }

}
