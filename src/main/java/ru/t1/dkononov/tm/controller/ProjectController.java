package ru.t1.dkononov.tm.controller;

import ru.t1.dkononov.tm.api.IProjectController;
import ru.t1.dkononov.tm.api.IProjectService;
import ru.t1.dkononov.tm.model.Project;
import ru.t1.dkononov.tm.service.ProjectService;
import ru.t1.dkononov.tm.util.TerminalUtil;

import java.util.List;

public final class ProjectController implements IProjectController {

    private final IProjectService projectService;

    public ProjectController(final IProjectService projectService) {
        this.projectService = projectService;
    }

    @Override
    public void showProjects() {
        System.out.println("[SHOW PROJECTS]");
        int index = 0;
        final List<Project> projects = projectService.findAll();
        for (final Project project : projects) {
            index++;
            System.out.println(index + ". " + project.getName());
        }
        System.out.println("[OK]");
    }

    @Override
    public void addProject() {
        System.out.println("[CREATE NEW PROJECT]");
        System.out.println("ENTER NAME:");
        final String name = TerminalUtil.inLine();
        System.out.println("ENTER DESCRIPTION:");
        final String description = TerminalUtil.inLine();
        final Project project = projectService.create(name, description);
        if (project == null) {
            System.out.println("[FAIL]");
            return;
        }
        System.out.println("[OK]");
    }

    @Override
    public void clearProjects() {
        System.out.println("[CLEAR LIST PROJECT]");
        projectService.clear();
        System.out.println("[OK]");
    }

}
