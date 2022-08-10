package ru.t1.dkononov.tm.command.project;

import ru.t1.dkononov.tm.enumerated.Status;
import ru.t1.dkononov.tm.exception.AbstractException;
import ru.t1.dkononov.tm.util.TerminalUtil;

public final class ProjectStartByIdCommand extends AbstractProjectCommand {

    public static final String NAME = "project-start-by-id";

    public static final String DESCRIPTION = "Начать проект по Id.";

    @Override
    public String getDescription() {
        return DESCRIPTION;
    }

    @Override
    public String getName() {
        return NAME;
    }

    @Override
    public void execute() throws AbstractException {
        final String userId = getUserId();
        System.out.println("[IN PROGRESS PROJECT BY ID]");
        System.out.println("ENTER ID:");
        final String id = TerminalUtil.inLine();
        getProjectService().changeProjectStatusById(userId, id, Status.IN_PROGRESS);
    }

}
