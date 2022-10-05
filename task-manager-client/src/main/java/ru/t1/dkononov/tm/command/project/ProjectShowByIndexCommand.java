package ru.t1.dkononov.tm.command.project;

import lombok.Getter;
import org.jetbrains.annotations.NotNull;
import ru.t1.dkononov.tm.dto.request.ProjectGetByIndexRequest;
import ru.t1.dkononov.tm.dto.response.ProjectGetByIndexResponse;
import ru.t1.dkononov.tm.exception.AbstractException;
import ru.t1.dkononov.tm.exception.entity.ProjectNotFoundException;
import ru.t1.dkononov.tm.model.Project;
import ru.t1.dkononov.tm.util.TerminalUtil;

public final class ProjectShowByIndexCommand extends AbstractProjectCommand {

    @Getter
    @NotNull
    public final String NAME = "project-show-by-index";

    @Getter
    @NotNull
    public final String DESCRIPTION = "Показать проект по индексу.";

    @Override
    public void execute() throws AbstractException {
        System.out.println("[ENTER INDEX]");
        @NotNull final Integer value = TerminalUtil.nextNumber() - 1;

        @NotNull final ProjectGetByIndexRequest request = new ProjectGetByIndexRequest();
        request.setIndex(value);
        @NotNull final ProjectGetByIndexResponse projectResponse = getProjectEndpoint().getProjectByIndex(request);
        if (projectResponse == null) throw new ProjectNotFoundException();
        System.out.println(show(projectResponse.getProject()));
    }

    @NotNull
    public String show(@NotNull final Project project) {
        return "[ID: " + project.getId() + "]\n" +
                "[NAME: " + project.getName() + "]\n" +
                "[DESC: " + project.getDescription() + "]\n" +
                "[STATUS: " + project.getStatus() + "]";
    }

}
