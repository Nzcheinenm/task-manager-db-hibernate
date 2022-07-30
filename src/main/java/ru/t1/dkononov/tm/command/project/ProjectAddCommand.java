package ru.t1.dkononov.tm.command.project;

import ru.t1.dkononov.tm.exception.field.AbstractFieldException;
import ru.t1.dkononov.tm.util.TerminalUtil;

public final class ProjectAddCommand extends AbstractProjectCommand {

    public static final String PROJECT_ADD = "project-add";

    @Override
    public String getDescription() {
        return "Создать новый проект.";
    }

    @Override
    public String getName() {
        return PROJECT_ADD;
    }

    @Override
    public void execute() throws AbstractFieldException {
        System.out.println("[CREATE NEW PROJECT]");
        System.out.println("ENTER NAME:");
        final String name = TerminalUtil.inLine();
        System.out.println("ENTER DESCRIPTION:");
        final String description = TerminalUtil.inLine();
        getProjectService().create(name, description);
    }

}
