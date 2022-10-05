package ru.t1.dkononov.tm.command.task;

import lombok.Getter;
import org.jetbrains.annotations.NotNull;
import ru.t1.dkononov.tm.dto.request.TaskGetByIdRequest;
import ru.t1.dkononov.tm.dto.response.TaskGetByIdResponse;
import ru.t1.dkononov.tm.exception.AbstractException;
import ru.t1.dkononov.tm.model.Task;
import ru.t1.dkononov.tm.util.TerminalUtil;

public final class TaskShowByIdCommand extends AbstractTaskCommand {

    @Getter
    @NotNull
    public final String NAME = "task-show-by-id";

    @Getter
    @NotNull
    public final String DESCRIPTION = "Показать задачу по Id.";

    @Override
    public void execute() throws AbstractException {
        System.out.println("[SHOW TASK]");
        System.out.println("[ENTER ID]");
        @NotNull final String scanner = TerminalUtil.inLine();
        @NotNull final TaskGetByIdRequest request = new TaskGetByIdRequest();
        request.setId(scanner);
        @NotNull final TaskGetByIdResponse response = getTaskEndpointClient().getTaskById(request);
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
