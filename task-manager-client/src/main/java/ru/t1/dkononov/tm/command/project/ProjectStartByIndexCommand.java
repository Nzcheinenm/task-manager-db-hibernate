package ru.t1.dkononov.tm.command.project;

import lombok.Getter;
import org.jetbrains.annotations.NotNull;
import ru.t1.dkononov.tm.dto.request.ProjectStartByIndexRequest;
import ru.t1.dkononov.tm.util.TerminalUtil;

public final class ProjectStartByIndexCommand extends AbstractProjectCommand {

    @Getter
    @NotNull
    public final String NAME = "project-start-by-index";

    @Getter
    @NotNull
    public final String DESCRIPTION = "Начать проект по индексу.";

    @Override
    public void execute() throws Exception {
        System.out.println("[IN PROGRESS PROJECT BY INDEX]");
        System.out.println("ENTER INDEX:");
        @NotNull final Integer index = TerminalUtil.nextNumber() - 1;
        @NotNull final ProjectStartByIndexRequest request = new ProjectStartByIndexRequest(getToken());
        request.setIndex(index);
        getProjectEndpoint().startProjectByIndex(request);
    }

}
