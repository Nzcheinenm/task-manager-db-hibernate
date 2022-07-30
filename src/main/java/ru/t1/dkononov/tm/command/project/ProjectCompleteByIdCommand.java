package ru.t1.dkononov.tm.command.project;

import ru.t1.dkononov.tm.enumerated.Status;
import ru.t1.dkononov.tm.exception.AbstractException;
import ru.t1.dkononov.tm.util.TerminalUtil;

public final class ProjectCompleteByIdCommand extends AbstractProjectCommand {

    public static final String PROJECT_COMPLETE_BY_ID = "project-complete-by-id";

    @Override
    public String getDescription() {
        return "Завершить проект по Id.";
    }

    @Override
    public String getName() {
        return PROJECT_COMPLETE_BY_ID;
    }

    @Override
    public void execute() throws AbstractException {
        System.out.println("[COMPLETE PROJECT BY ID]");
        System.out.println("ENTER ID:");
        final String id = TerminalUtil.inLine();
        getProjectService().changeProjectStatusById(id, Status.COMPLETED);
    }

}
