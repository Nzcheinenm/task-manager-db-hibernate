package ru.t1.dkononov.tm.command.project;

import lombok.Getter;
import org.jetbrains.annotations.NotNull;
import ru.t1.dkononov.tm.dto.request.ProjectStartByIdRequest;
import ru.t1.dkononov.tm.util.TerminalUtil;

public final class ProjectStartByIdCommand extends AbstractProjectCommand {

    @Getter
    @NotNull
    public final String NAME = "project-start-by-id";

    @Getter
    @NotNull
    public final String DESCRIPTION = "Начать проект по Id.";

    @Override
    public void execute() throws Exception {
        System.out.println("[IN PROGRESS PROJECT BY ID]");
        System.out.println("ENTER ID:");
        @NotNull final String id = TerminalUtil.inLine();
        @NotNull final ProjectStartByIdRequest request = new ProjectStartByIdRequest(getToken());
        request.setId(id);
        getProjectEndpoint().startProjectById(request);
    }

}
