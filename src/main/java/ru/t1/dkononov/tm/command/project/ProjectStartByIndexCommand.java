package ru.t1.dkononov.tm.command.project;

import ru.t1.dkononov.tm.enumerated.Status;
import ru.t1.dkononov.tm.exception.AbstractException;
import ru.t1.dkononov.tm.util.TerminalUtil;

public final class ProjectStartByIndexCommand extends AbstractProjectCommand {

    public static final String PROJECT_START_BY_INDEX = "project-start-by-index";

    @Override
    public String getDescription() {
        return "Начать проект по индексу.";
    }

    @Override
    public String getName() {
        return PROJECT_START_BY_INDEX;
    }

    @Override
    public void execute() throws AbstractException {
        System.out.println("[IN PROGRESS PROJECT BY INDEX]");
        System.out.println("ENTER INDEX:");
        final Integer index = TerminalUtil.nextNumber() - 1;
        getProjectService().changeProjectStatusByIndex(index, Status.IN_PROGRESS);
    }

}
