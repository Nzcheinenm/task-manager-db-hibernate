package ru.t1.dkononov.tm.controller;

import ru.t1.dkononov.tm.api.controllers.IProjectTaskController;
import ru.t1.dkononov.tm.api.services.IProjectTaskService;
import ru.t1.dkononov.tm.exception.AbstractException;
import ru.t1.dkononov.tm.util.TerminalUtil;

public class ProjectTaskController implements IProjectTaskController {

    private final IProjectTaskService projectTaskService;

    public ProjectTaskController(final IProjectTaskService projectTaskService) {
        this.projectTaskService = projectTaskService;
    }

    @Override
    public void bindTaskToProject() throws AbstractException {
        System.out.println("[BIND TASK TO PROJECT]");
        System.out.println("[ENTER PROJECT ID:]");
        final String projectId = TerminalUtil.inLine();
        System.out.println("[ENTER TASK ID:]");
        final String taskId = TerminalUtil.inLine();
        projectTaskService.bindTaskToProject(projectId, taskId);
    }

    @Override
    public void unbindTaskFromProject() throws AbstractException {
        System.out.println("[UNBIND TASK TO PROJECT]");
        System.out.println("[ENTER PROJECT ID:]");
        final String projectId = TerminalUtil.inLine();
        System.out.println("[ENTER TASK ID:]");
        final String taskId = TerminalUtil.inLine();
        projectTaskService.unbindTaskFromProject(projectId, taskId);
    }

}
