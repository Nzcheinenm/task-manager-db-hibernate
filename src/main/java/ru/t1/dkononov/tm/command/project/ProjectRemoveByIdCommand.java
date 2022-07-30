package ru.t1.dkononov.tm.command.project;

import ru.t1.dkononov.tm.exception.AbstractException;
import ru.t1.dkononov.tm.model.Project;
import ru.t1.dkononov.tm.util.TerminalUtil;

public final class ProjectRemoveByIdCommand extends AbstractProjectCommand {

    public static final String NAME = "project-remove-by-id";

    public static final String DESCRIPTION = "Удалить проект по Id.";

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
        System.out.println("[ENTER ID]");
        final String value = TerminalUtil.inLine();
        final Project project = getProjectService().removeById(value);
        getProjectTaskService().removeProjectById(project.getId());
    }

}
