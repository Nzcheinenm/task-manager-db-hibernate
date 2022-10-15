package ru.t1.dkononov.tm.command.project;

import lombok.Getter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.t1.dkononov.tm.dto.request.ProjectChangeStatusByIndexRequest;
import ru.t1.dkononov.tm.enumerated.Status;
import ru.t1.dkononov.tm.exception.AbstractException;
import ru.t1.dkononov.tm.util.TerminalUtil;

import java.util.Arrays;


public final class ProjectChangeStatusByIndexCommand extends AbstractProjectCommand {

    @Getter
    @NotNull
    public final String NAME = "project-change-status-by-index";

    @Getter
    @NotNull
    public final String DESCRIPTION = "Поменять статус у проекта по индексу.";

    @Override
    public void execute() throws AbstractException {
        System.out.println("[CHANGE PROJECT STATUS BY INDEX]");
        System.out.println("ENTER INDEX:");
        @NotNull final Integer index = TerminalUtil.nextNumber() - 1;
        System.out.println("ENTER STATUS:");
        System.out.println(Arrays.toString(Status.values()));
        @NotNull final String statusValue = TerminalUtil.inLine();
        @Nullable final Status status = Status.toStatus(statusValue);
        @NotNull final ProjectChangeStatusByIndexRequest request = new ProjectChangeStatusByIndexRequest(getToken());
        request.setIndex(index);
        request.setStatusValue(status.getDisplayName());
        getProjectEndpoint().changeStatusByIndex(request);
    }

}
