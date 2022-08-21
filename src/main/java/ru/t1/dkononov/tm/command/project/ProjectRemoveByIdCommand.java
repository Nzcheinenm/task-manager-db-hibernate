package ru.t1.dkononov.tm.command.project;

import lombok.Getter;
import org.jetbrains.annotations.NotNull;
import ru.t1.dkononov.tm.exception.AbstractException;
import ru.t1.dkononov.tm.model.Project;
import ru.t1.dkononov.tm.util.TerminalUtil;

public final class ProjectRemoveByIdCommand extends AbstractProjectCommand {

    @Getter
    @NotNull
    public final String NAME = "project-remove-by-id";

    @Getter
    @NotNull
    public final String DESCRIPTION = "Удалить проект по Id.";


    @Override
    public void execute() throws AbstractException {
        final String userId = getUserId();
        System.out.println("[ENTER ID]");
        final String value = TerminalUtil.inLine();
        final Project project = getProjectService().removeById(userId, value);
        getProjectTaskService().removeProjectById(userId, project.getId());
    }

}
