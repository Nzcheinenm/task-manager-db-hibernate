package ru.t1.dkononov.tm.command.project;

import ru.t1.dkononov.tm.exception.field.AbstractFieldException;
import ru.t1.dkononov.tm.model.Project;
import ru.t1.dkononov.tm.util.TerminalUtil;

public final class ProjectShowByIdCommand extends AbstractProjectCommand {

    public static final String PROJECT_SHOW_BY_ID = "project-show-by-id";

    @Override
    public String getDescription() {
        return "Показать проект по Id.";
    }

    @Override
    public String getName() {
        return PROJECT_SHOW_BY_ID;
    }

    @Override
    public void execute() throws AbstractFieldException {
        System.out.println("[SHOW PROJECT]");
        System.out.println("[ENTER ID]");
        final String scanner = TerminalUtil.inLine();
        final Project project = getProjectService().findById(scanner);
        System.out.println(show(project));
    }

    public String show(final Project project) {
        return "[ID: " + project.getId() + "]\n" +
                "[NAME: " + project.getName() + "]\n" +
                "[DESC: " + project.getDescription() + "]\n" +
                "[STATUS: " + project.getStatus() + "]";
    }

}
