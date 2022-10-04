package ru.t1.dkononov.tm.command.project;

import lombok.Getter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.t1.dkononov.tm.dto.request.ProjectCompleteByIdRequest;
import ru.t1.dkononov.tm.dto.request.ProjectCompleteByIndexRequest;
import ru.t1.dkononov.tm.enumerated.Status;
import ru.t1.dkononov.tm.exception.AbstractException;
import ru.t1.dkononov.tm.util.TerminalUtil;

public final class ProjectCompleteByIndexCommand extends AbstractProjectCommand {

    @Getter
    @NotNull
    public final String NAME = "project-complete-by-index";

    @Getter
    @NotNull
    public final String DESCRIPTION = "Завершить проект по индексу.";

    @Override
    public void execute() throws Exception {
        System.out.println("[COMPLETE PROJECT BY INDEX]");
        System.out.println("ENTER INDEX:");
        @NotNull final Integer index = TerminalUtil.nextNumber() - 1;
        @NotNull final ProjectCompleteByIndexRequest request = new ProjectCompleteByIndexRequest();
        request.setIndex(index);
        getProjectEndpoint().completeProjectByIndex(request);
    }

}
