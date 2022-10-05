package ru.t1.dkononov.tm.command.project;

import lombok.Getter;
import org.jetbrains.annotations.NotNull;
import ru.t1.dkononov.tm.dto.request.ProjectCompleteByIdRequest;
import ru.t1.dkononov.tm.util.TerminalUtil;

public final class ProjectCompleteByIdCommand extends AbstractProjectCommand {

    @Getter
    @NotNull
    public final String NAME = "project-complete-by-id";

    @Getter
    @NotNull
    public final String DESCRIPTION = "Завершить проект по Id.";


    @Override
    public void execute() throws Exception {
        System.out.println("[COMPLETE PROJECT BY ID]");
        System.out.println("ENTER ID:");
        @NotNull final String id = TerminalUtil.inLine();
        @NotNull final ProjectCompleteByIdRequest request = new ProjectCompleteByIdRequest();
        request.setId(id);
        getProjectEndpoint().completeProjectById(request);
    }

}
