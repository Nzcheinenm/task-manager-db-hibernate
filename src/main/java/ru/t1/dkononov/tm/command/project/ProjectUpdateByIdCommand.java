package ru.t1.dkononov.tm.command.project;

import ru.t1.dkononov.tm.exception.AbstractException;
import ru.t1.dkononov.tm.util.TerminalUtil;

public final class ProjectUpdateByIdCommand extends AbstractProjectCommand {

    public static final String PROJECT_UPDATE_BY_ID = "project-update-by-id";

    @Override
    public String getDescription() {
        return "Обновить проект по Id.";
    }

    @Override
    public String getName() {
        return PROJECT_UPDATE_BY_ID;
    }

    @Override
    public void execute() throws AbstractException {
        System.out.println("[ENTER ID]");
        final String id = TerminalUtil.inLine();
        System.out.println("[ENTER NAME]");
        final String name = TerminalUtil.inLine();
        System.out.println("[ENTER DESCRIPTION]");
        final String description = TerminalUtil.inLine();
        getProjectService().updateById(id, name, description);
    }

}
