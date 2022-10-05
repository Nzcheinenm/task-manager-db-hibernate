package ru.t1.dkononov.tm.command.project;

import lombok.Getter;
import org.jetbrains.annotations.NotNull;
import ru.t1.dkononov.tm.dto.request.ProjectRemoveByIdRequest;
import ru.t1.dkononov.tm.util.TerminalUtil;

public final class ProjectRemoveByIdCommand extends AbstractProjectCommand {

    @Getter
    @NotNull
    public final String NAME = "project-remove-by-id";

    @Getter
    @NotNull
    public final String DESCRIPTION = "Удалить проект по Id.";


    @Override
    public void execute() throws Exception {
        System.out.println("[ENTER ID]");
        @NotNull final String value = TerminalUtil.inLine();

        @NotNull final ProjectRemoveByIdRequest request = new ProjectRemoveByIdRequest();
        request.setId(value);
        getProjectEndpoint().removeProjectById(request);

    }

}
