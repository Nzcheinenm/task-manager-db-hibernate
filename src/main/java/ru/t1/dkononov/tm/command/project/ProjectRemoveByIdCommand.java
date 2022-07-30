package ru.t1.dkononov.tm.command.project;

import ru.t1.dkononov.tm.exception.AbstractException;
import ru.t1.dkononov.tm.model.Project;
import ru.t1.dkononov.tm.util.TerminalUtil;

public final class ProjectRemoveByIdCommand extends AbstractProjectCommand {

    public static final String PROJECT_REMOVE_BY_ID = "project-remove-by-id";

    @Override
    public String getDescription() {
        return "Удалить проект по Id.";
    }

    @Override
    public String getName() {
        return PROJECT_REMOVE_BY_ID;
    }

    @Override
    public void execute() throws AbstractException {
        System.out.println("[ENTER ID]");
        final String value = TerminalUtil.inLine();
        final Project project = getProjectService().removeById(value);
        getProjectTaskService().removeProjectById(project.getId());
    }

}
