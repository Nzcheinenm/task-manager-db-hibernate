package ru.t1.dkononov.tm.command.task;

import lombok.Getter;
import org.jetbrains.annotations.NotNull;
import ru.t1.dkononov.tm.dto.request.TaskGetByIndexRequest;
import ru.t1.dkononov.tm.dto.response.TaskGetByIndexResponse;
import ru.t1.dkononov.tm.exception.AbstractException;
import ru.t1.dkononov.tm.model.Task;
import ru.t1.dkononov.tm.util.TerminalUtil;

public final class TaskShowByIndexCommand extends AbstractTaskCommand {

    @Getter
    @NotNull
    public final String NAME = "task-show-by-index";

    @Getter
    @NotNull
    public final String DESCRIPTION = "Показать задачу по индексу.";

    @Override
    public void execute() throws AbstractException {
        System.out.println("[SHOW TASK]");
        System.out.println("[ENTER INDEX]");
        @NotNull final Integer value = TerminalUtil.nextNumber() - 1;
        @NotNull final TaskGetByIndexRequest request = new TaskGetByIndexRequest(getToken());
        request.setIndex(value);
        @NotNull final TaskGetByIndexResponse response = getTaskEndpointClient().getTaskByIndex(request);
        if (response.getTask() == null) response.setTask(new Task());
        @NotNull final Task task = response.getTask();
        System.out.println(show(task));
    }

    @NotNull
    public String show(@NotNull final Task task) {
        return "[ID: " + task.getId() + "]\n" +
                "[NAME: " + task.getName() + "]\n" +
                "[DESC: " + task.getDescription() + "]\n" +
                "[STATUS: " + task.getStatus() + "]";
    }

}
