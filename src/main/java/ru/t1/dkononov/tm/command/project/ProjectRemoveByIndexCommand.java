package ru.t1.dkononov.tm.command.project;

import ru.t1.dkononov.tm.exception.AbstractException;
import ru.t1.dkononov.tm.model.Project;
import ru.t1.dkononov.tm.util.TerminalUtil;

public final class ProjectRemoveByIndexCommand extends AbstractProjectCommand {

    public static final String PROJECT_REMOVE_BY_INDEX = "project-remove-by-index";

    @Override
    public String getDescription() {
        return "Удалить проект по индексу.";
    }

    @Override
    public String getName() {
        return PROJECT_REMOVE_BY_INDEX;
    }

    @Override
    public void execute() throws AbstractException {
        System.out.println("[ENTER INDEX]");
        final Integer value = TerminalUtil.nextNumber() - 1;
        final Project project = getProjectService().removeByIndex(value);
        getProjectTaskService().removeProjectById(project.getId());
    }

}
