package ru.t1.dkononov.tm.command.task;

import lombok.Getter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.t1.dkononov.tm.dto.request.TaskChangeStatusByIdRequest;
import ru.t1.dkononov.tm.enumerated.Status;
import ru.t1.dkononov.tm.util.TerminalUtil;

import java.util.Arrays;

public final class TaskChangeStatusByIdCommand extends AbstractTaskCommand {

    @Getter
    @NotNull
    public final String NAME = "task-change-status-by-id";

    @Getter
    @NotNull
    public final String DESCRIPTION = "Поменять статус задачи по Id.";

    @Override
    public void execute() throws Exception {
        System.out.println("[CHANGE PROJECT STATUS BY ID]");
        System.out.println("ENTER ID:");
        @NotNull final String id = TerminalUtil.inLine();
        System.out.println("ENTER STATUS:");
        System.out.println(Arrays.toString(Status.values()));
        @NotNull final String statusValue = TerminalUtil.inLine();
        @Nullable final Status status = Status.toStatus(statusValue);
        @NotNull final TaskChangeStatusByIdRequest request = new TaskChangeStatusByIdRequest(getToken());
        request.setId(id);
        request.setStatusValue(status.getDisplayName());
        getTaskEndpointClient().changeStatusById(request);
    }

}
