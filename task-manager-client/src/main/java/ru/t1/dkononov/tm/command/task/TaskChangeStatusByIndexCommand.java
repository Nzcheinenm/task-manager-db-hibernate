package ru.t1.dkononov.tm.command.task;

import lombok.Getter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.t1.dkononov.tm.dto.request.TaskChangeStatusByIndexRequest;
import ru.t1.dkononov.tm.enumerated.Status;
import ru.t1.dkononov.tm.exception.AbstractException;
import ru.t1.dkononov.tm.util.TerminalUtil;

import java.util.Arrays;

public final class TaskChangeStatusByIndexCommand extends AbstractTaskCommand {

    @Getter
    @NotNull
    public final String NAME = "task-change-status-by-index";

    @Getter
    @NotNull
    public final String DESCRIPTION = "Поменять статус задачи по индексу.";

    @Override
    public void execute() throws Exception {
        System.out.println("[CHANGE PROJECT STATUS BY INDEX]");
        System.out.println("ENTER INDEX:");
        @NotNull final Integer index = TerminalUtil.nextNumber() - 1;
        System.out.println("ENTER STATUS:");
        System.out.println(Arrays.toString(Status.values()));
        @NotNull final String statusValue = TerminalUtil.inLine();
        @Nullable final Status status = Status.toStatus(statusValue);
        @NotNull final TaskChangeStatusByIndexRequest request = new TaskChangeStatusByIndexRequest(getToken());
        request.setIndex(index);
        request.setStatusValue(status.getDisplayName());
        getTaskEndpointClient().changeStatusByIndex(request);
    }

}
