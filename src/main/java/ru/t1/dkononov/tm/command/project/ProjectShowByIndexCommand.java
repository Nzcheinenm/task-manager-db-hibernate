package ru.t1.dkononov.tm.command.project;

import ru.t1.dkononov.tm.exception.field.AbstractFieldException;
import ru.t1.dkononov.tm.model.Project;
import ru.t1.dkononov.tm.util.TerminalUtil;

public final class ProjectShowByIndexCommand extends AbstractProjectCommand {

    public static final String PROJECT_SHOW_BY_INDEX = "project-show-by-index";

    @Override
    public String getDescription() {
        return "Показать проект по индексу.";
    }

    @Override
    public String getName() {
        return PROJECT_SHOW_BY_INDEX;
    }

    @Override
    public void execute() throws AbstractFieldException {
        System.out.println("[ENTER INDEX]");
        final Integer value = TerminalUtil.nextNumber() - 1;
        final Project project = getProjectService().findByIndex(value);
        System.out.println(show(project));
    }

    public String show(final Project project) {
        return "[ID: " + project.getId() + "]\n" +
                "[NAME: " + project.getName() + "]\n" +
                "[DESC: " + project.getDescription() + "]\n" +
                "[STATUS: " + project.getStatus() + "]";
    }

}
