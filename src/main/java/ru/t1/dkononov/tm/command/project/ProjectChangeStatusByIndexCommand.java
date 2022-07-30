package ru.t1.dkononov.tm.command.project;

import ru.t1.dkononov.tm.enumerated.Status;
import ru.t1.dkononov.tm.exception.AbstractException;
import ru.t1.dkononov.tm.util.TerminalUtil;

import java.util.Arrays;

public final class ProjectChangeStatusByIndexCommand extends AbstractProjectCommand {

    public static final String PROJECT_CHANGE_STATUS_BY_INDEX = "project-change-status-by-index";

    @Override
    public String getDescription() {
        return "Поменять статус у проекта по индексу.";
    }

    @Override
    public String getName() {
        return PROJECT_CHANGE_STATUS_BY_INDEX;
    }

    @Override
    public void execute() throws AbstractException {
        System.out.println("[CHANGE PROJECT STATUS BY INDEX]");
        System.out.println("ENTER INDEX:");
        final Integer index = TerminalUtil.nextNumber() - 1;
        System.out.println("ENTER STATUS:");
        System.out.println(Arrays.toString(Status.values()));
        final String statusValue = TerminalUtil.inLine();
        final Status status = Status.toStatus(statusValue);
        getProjectService().changeProjectStatusByIndex(index, status);
    }

}
