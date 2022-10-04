package ru.t1.dkononov.tm.command.project;

import lombok.Getter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.t1.dkononov.tm.dto.request.ProjectRemoveByIdRequest;
import ru.t1.dkononov.tm.dto.request.ProjectRemoveByIndexRequest;
import ru.t1.dkononov.tm.exception.AbstractException;
import ru.t1.dkononov.tm.model.Project;
import ru.t1.dkononov.tm.util.TerminalUtil;

public final class ProjectRemoveByIndexCommand extends AbstractProjectCommand {

    @Getter
    @NotNull
    public final String NAME = "project-remove-by-index";

    @Getter
    @NotNull
    public final String DESCRIPTION = "Удалить проект по индексу.";

    @Override
    public void execute() throws Exception {
        System.out.println("[ENTER INDEX]");
        @NotNull final Integer value = TerminalUtil.nextNumber() - 1;
        @NotNull final ProjectRemoveByIndexRequest request = new ProjectRemoveByIndexRequest();
        request.setIndex(value);
        getProjectEndpoint().removeProjectById(request);
    }

}
