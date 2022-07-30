package ru.t1.dkononov.tm.command.project;

import ru.t1.dkononov.tm.enumerated.Status;
import ru.t1.dkononov.tm.exception.AbstractException;
import ru.t1.dkononov.tm.util.TerminalUtil;

public final class ProjectCompleteByIndexCommand extends AbstractProjectCommand {

    public static final String PROJECT_COMPLETE_BY_INDEX = "project-complete-by-index";

    @Override
    public String getDescription() {
        return "Завершить проект по индексу.";
    }

    @Override
    public String getName() {
        return PROJECT_COMPLETE_BY_INDEX;
    }

    @Override
    public void execute() throws AbstractException {
        System.out.println("[COMPLETE PROJECT BY INDEX]");
        System.out.println("ENTER INDEX:");
        final Integer index = TerminalUtil.nextNumber() - 1;
        getProjectService().changeProjectStatusByIndex(index, Status.COMPLETED);
    }

}
