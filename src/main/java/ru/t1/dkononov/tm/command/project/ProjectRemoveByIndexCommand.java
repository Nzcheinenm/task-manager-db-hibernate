package ru.t1.dkononov.tm.command.project;

import ru.t1.dkononov.tm.exception.AbstractException;
import ru.t1.dkononov.tm.model.Project;
import ru.t1.dkononov.tm.util.TerminalUtil;

public final class ProjectRemoveByIndexCommand extends AbstractProjectCommand {

    public static final String NAME = "project-remove-by-index";

    public static final String DESCRIPTION = "Удалить проект по индексу.";

    @Override
    public String getDescription() {
        return DESCRIPTION;
    }

    @Override
    public String getName() {
        return NAME;
    }

    @Override
    public void execute() throws AbstractException {
        final String userId = getUserId();
        System.out.println("[ENTER INDEX]");
        final Integer value = TerminalUtil.nextNumber() - 1;
        final Project project = getProjectService().removeByIndex(userId, value);
        getProjectTaskService().removeProjectById(userId, project.getId());
    }

}
