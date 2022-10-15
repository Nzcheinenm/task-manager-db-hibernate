package ru.t1.dkononov.tm.command.project;

import lombok.Getter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.t1.dkononov.tm.dto.request.ProjectChangeStatusByIdRequest;
import ru.t1.dkononov.tm.enumerated.Status;
import ru.t1.dkononov.tm.exception.AbstractException;
import ru.t1.dkononov.tm.util.TerminalUtil;

import java.util.Arrays;

public final class ProjectChangeStatusByIdCommand extends AbstractProjectCommand {

    @Getter
    @Nullable
    public final String DESCRIPTION = "Поменять статус у проекта по Id.";

    @Getter
    @Nullable
    public final String NAME = "project-change-status-by-id";

    @Override
    public void execute() throws AbstractException {
        System.out.println("[CHANGE PROJECT STATUS BY ID]");
        System.out.println("ENTER ID:");
        @NotNull final String id = TerminalUtil.inLine();
        System.out.println("ENTER STATUS:");
        System.out.println(Arrays.toString(Status.values()));
        @NotNull final String statusValue = TerminalUtil.inLine();
        @Nullable final Status status = Status.toStatus(statusValue);
        @NotNull final ProjectChangeStatusByIdRequest request = new ProjectChangeStatusByIdRequest(getToken());
        request.setId(id);
        request.setStatusValue(status.getDisplayName());
        getProjectEndpoint().changeStatusById(request);
    }

}
