package ru.t1.dkononov.tm.command.project;

import ru.t1.dkononov.tm.exception.field.AbstractFieldException;
import ru.t1.dkononov.tm.model.Project;
import ru.t1.dkononov.tm.util.TerminalUtil;

public final class ProjectShowByIdCommand extends AbstractProjectCommand {

    public static final String NAME = "project-show-by-id";

    public static final String DESCRIPTION = "Показать проект по Id.";

    @Override
    public String getDescription() {
        return DESCRIPTION;
    }

    @Override
    public String getName() {
        return NAME;
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
