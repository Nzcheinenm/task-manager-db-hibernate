package ru.t1.dkononov.tm.command.project;

import lombok.Getter;
import org.jetbrains.annotations.NotNull;
import ru.t1.dkononov.tm.dto.request.ProjectClearRequest;
import ru.t1.dkononov.tm.exception.AbstractException;

public final class ProjectClearCommand extends AbstractProjectCommand {

    @Getter
    @NotNull
    public final String NAME = "project-clear";

    @Getter
    @NotNull
    public final String DESCRIPTION = "Очистить список проектов.";

    @Override
    public void execute() throws AbstractException {
        System.out.println("[PROJECT_CLEAR]");
        @NotNull final ProjectClearRequest request = new ProjectClearRequest(getToken());
        getProjectEndpoint().clearProject(request);
    }

}
