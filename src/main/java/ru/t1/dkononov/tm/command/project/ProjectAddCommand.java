package ru.t1.dkononov.tm.command.project;

import ru.t1.dkononov.tm.exception.field.AbstractFieldException;
import ru.t1.dkononov.tm.util.TerminalUtil;

public final class ProjectAddCommand extends AbstractProjectCommand {

    public static final String DESCRIPTION = "Создать новый проект.";
    public static final String NAME = "project-add";

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
        final String userId = getUserId();
        System.out.println("[CREATE NEW PROJECT]");
        System.out.println("ENTER NAME:");
        final String name = TerminalUtil.inLine();
        System.out.println("ENTER DESCRIPTION:");
        final String description = TerminalUtil.inLine();
        getProjectService().create(userId, name, description);
    }

}
