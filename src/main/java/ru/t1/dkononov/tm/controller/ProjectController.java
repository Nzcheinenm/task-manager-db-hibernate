package ru.t1.dkononov.tm.controller;

import ru.t1.dkononov.tm.api.IProjectController;
import ru.t1.dkononov.tm.api.IProjectService;
import ru.t1.dkononov.tm.model.Project;
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

    @Override
    public void showProjectById() {
        System.out.println("[SHOW PROJECT]");
        System.out.println("[ENTER ID]");
        final String scanner = TerminalUtil.inLine();
        final Project project = projectService.findById(scanner);
        if (project == null) System.out.println("[FAIL]");
        else {
            System.out.println(show(project));
            System.out.println("[OK]");
        }
    }

    @Override
    public void showProjectByIndex() {
        System.out.println("[ENTER INDEX]");
        final Integer value = TerminalUtil.nextNumber() - 1;
        final Project project = projectService.findByIndex(value);
        if (project == null) System.out.println("[FAIL]");
        else {
            System.out.println(show(project));
            System.out.println("[OK]");
        }
    }

    @Override
    public void removeProjectById() {
        System.out.println("[ENTER ID]");
        final String value = TerminalUtil.inLine();
        final Project project = projectService.removeById(value);
        if (project == null) System.out.println("[FAIL]");
        else {
            System.out.println("[OK]");
        }
    }

    @Override
    public void removeProjectByIndex() {
        System.out.println("[ENTER INDEX]");
        final Integer value = TerminalUtil.nextNumber() - 1;
        final Project project = projectService.removeByIndex(value);
        if (project == null) System.out.println("[FAIL]");
        else {
            System.out.println("[OK]");
        }
    }

    @Override
    public void updateProjectById() {
        System.out.println("[ENTER ID]");
        final String id = TerminalUtil.inLine();
        System.out.println("[ENTER NAME]");
        final String name = TerminalUtil.inLine();
        System.out.println("[ENTER DESCRIPTION]");
        final String description = TerminalUtil.inLine();
        final Project project = projectService.updateById(id, name, description);
        if (project == null) System.out.println("[FAIL]");
        else {
            System.out.println("[OK]");
        }
    }

    @Override
    public void updateProjectByIndex() {
        System.out.println("[ENTER INDEX]");
        final Integer index = TerminalUtil.nextNumber() - 1;
        System.out.println("[ENTER NAME]");
        final String name = TerminalUtil.inLine();
        System.out.println("[ENTER DESCRIPTION]");
        final String description = TerminalUtil.inLine();
        final Project project = projectService.updateByIndex(index, name, description);
        if (project == null) System.out.println("[FAIL]");
        else {
            System.out.println("[OK]");
        }
    }

    @Override
    public String show(final Project project) {
        return "[ID: " + project.getId() + "]\n" +
                "[NAME: " + project.getName() + "]\n" +
                "[DESC: " + project.getDescription() + "]";
    }

}
