package ru.t1.dkononov.tm.command.project;

import lombok.Getter;
import org.jetbrains.annotations.NotNull;
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
    public void execute() throws AbstractException {
        final String userId = getUserId();
        System.out.println("[ENTER INDEX]");
        final Integer value = TerminalUtil.nextNumber() - 1;
        final Project project = getProjectService().removeByIndex(userId, value);
        getProjectTaskService().removeProjectById(userId, project.getId());
    }

}
