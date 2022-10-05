package ru.t1.dkononov.tm.command.project;

import lombok.Getter;
import org.jetbrains.annotations.NotNull;
import ru.t1.dkononov.tm.dto.request.ProjectUpdateByIdRequest;
import ru.t1.dkononov.tm.util.TerminalUtil;

public final class ProjectUpdateByIdCommand extends AbstractProjectCommand {

    @Getter
    @NotNull
    public final String NAME = "project-update-by-id";

    @Getter
    @NotNull
    public final String DESCRIPTION = "Обновить проект по Id.";

    @Override
    public void execute() throws Exception {
        System.out.println("[ENTER ID]");
        @NotNull final String id = TerminalUtil.inLine();
        System.out.println("[ENTER NAME]");
        @NotNull final String name = TerminalUtil.inLine();
        System.out.println("[ENTER DESCRIPTION]");
        @NotNull final String description = TerminalUtil.inLine();
        @NotNull final ProjectUpdateByIdRequest request = new ProjectUpdateByIdRequest();
        request.setId(id);
        request.setDescription(description);
        request.setName(name);
        getProjectEndpoint().updateProjectById(request);
    }

}
