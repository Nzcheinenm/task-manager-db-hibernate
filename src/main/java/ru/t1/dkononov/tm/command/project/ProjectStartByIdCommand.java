package ru.t1.dkononov.tm.command.project;

import ru.t1.dkononov.tm.enumerated.Status;
import ru.t1.dkononov.tm.exception.AbstractException;
import ru.t1.dkononov.tm.util.TerminalUtil;

public final class ProjectStartByIdCommand extends AbstractProjectCommand {

    public static final String PROJECT_START_BY_ID = "project-start-by-id";

    @Override
    public String getDescription() {
        return "Начать проект по Id.";
    }

    @Override
    public String getName() {
        return PROJECT_START_BY_ID;
    }

    @Override
    public void execute() throws AbstractException {
        System.out.println("[IN PROGRESS PROJECT BY ID]");
        System.out.println("ENTER ID:");
        final String id = TerminalUtil.inLine();
        getProjectService().changeProjectStatusById(id, Status.IN_PROGRESS);
    }

}
