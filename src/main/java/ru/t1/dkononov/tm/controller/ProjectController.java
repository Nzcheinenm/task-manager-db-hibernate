package ru.t1.dkononov.tm.controller;

import ru.t1.dkononov.tm.api.controllers.IProjectController;
import ru.t1.dkononov.tm.api.services.IProjectService;
import ru.t1.dkononov.tm.api.services.IProjectTaskService;
import ru.t1.dkononov.tm.enumerated.Sort;
import ru.t1.dkononov.tm.enumerated.Status;
import ru.t1.dkononov.tm.exception.AbstractException;
import ru.t1.dkononov.tm.exception.field.AbstractFieldException;
import ru.t1.dkononov.tm.model.Project;
import ru.t1.dkononov.tm.util.TerminalUtil;

import java.util.Arrays;
import java.util.List;

public final class ProjectController implements IProjectController {

    private final IProjectService projectService;

    private final IProjectTaskService projectTaskService;

    public ProjectController(final IProjectService projectService, final IProjectTaskService projectTaskService) {
        this.projectService = projectService;
        this.projectTaskService = projectTaskService;
    }

    @Override
    public void showProjects() {
        System.out.println("[SHOW PROJECTS]");
        System.out.println("[ENTER SORT: ]");
        System.out.println(Arrays.toString(Sort.values()));
        final String sortType = TerminalUtil.inLine();
        final Sort sort = Sort.toSort(sortType);
        int index = 0;
        final List<Project> projects = projectService.findAll(sort);
        for (final Project project : projects) {
            index++;
            System.out.println(index + ". " + project.getName());
        }
    }

    @Override
    public void addProject() throws AbstractFieldException {
        System.out.println("[CREATE NEW PROJECT]");
        System.out.println("ENTER NAME:");
        final String name = TerminalUtil.inLine();
        System.out.println("ENTER DESCRIPTION:");
        final String description = TerminalUtil.inLine();
        projectService.create(name, description);
    }

    @Override
    public void clearProjects() {
        System.out.println("[CLEAR LIST PROJECT]");
        projectService.clear();
    }

    @Override
    public void showProjectById() throws AbstractFieldException {
        System.out.println("[SHOW PROJECT]");
        System.out.println("[ENTER ID]");
        final String scanner = TerminalUtil.inLine();
        final Project project = projectService.findById(scanner);
        System.out.println(show(project));
    }

    @Override
    public void showProjectByIndex() throws AbstractFieldException {
        System.out.println("[ENTER INDEX]");
        final Integer value = TerminalUtil.nextNumber() - 1;
        final Project project = projectService.findByIndex(value);
        System.out.println(show(project));
    }

    @Override
    public void removeProjectById() throws AbstractException {
        System.out.println("[ENTER ID]");
        final String value = TerminalUtil.inLine();
        final Project project = projectService.removeById(value);
        projectTaskService.removeProjectById(project.getId());
    }

    @Override
    public void removeProjectByIndex() throws AbstractException {
        System.out.println("[ENTER INDEX]");
        final Integer value = TerminalUtil.nextNumber() - 1;
        final Project project = projectService.removeByIndex(value);
        projectTaskService.removeProjectById(project.getId());
    }

    @Override
    public void updateProjectById() throws AbstractException {
        System.out.println("[ENTER ID]");
        final String id = TerminalUtil.inLine();
        System.out.println("[ENTER NAME]");
        final String name = TerminalUtil.inLine();
        System.out.println("[ENTER DESCRIPTION]");
        final String description = TerminalUtil.inLine();
        projectService.updateById(id, name, description);
    }

    @Override
    public void updateProjectByIndex() throws AbstractException {
        System.out.println("[ENTER INDEX]");
        final Integer index = TerminalUtil.nextNumber() - 1;
        System.out.println("[ENTER NAME]");
        final String name = TerminalUtil.inLine();
        System.out.println("[ENTER DESCRIPTION]");
        final String description = TerminalUtil.inLine();
        projectService.updateByIndex(index, name, description);
    }

    @Override
    public void changeProjectStatusById() throws AbstractException {
        System.out.println("[CHANGE PROJECT STATUS BY ID]");
        System.out.println("ENTER ID:");
        final String id = TerminalUtil.inLine();
        System.out.println("ENTER STATUS:");
        System.out.println(Arrays.toString(Status.values()));
        final String statusValue = TerminalUtil.inLine();
        final Status status = Status.toStatus(statusValue);
        projectService.changeProjectStatusById(id, status);
    }

    @Override
    public void changeProjectStatusByIndex() throws AbstractException {
        System.out.println("[CHANGE PROJECT STATUS BY INDEX]");
        System.out.println("ENTER INDEX:");
        final Integer index = TerminalUtil.nextNumber() - 1;
        System.out.println("ENTER STATUS:");
        System.out.println(Arrays.toString(Status.values()));
        final String statusValue = TerminalUtil.inLine();
        final Status status = Status.toStatus(statusValue);
        projectService.changeProjectStatusByIndex(index, status);
    }

    @Override
    public void completeProjectById() throws AbstractException {
        System.out.println("[COMPLETE PROJECT BY ID]");
        System.out.println("ENTER ID:");
        final String id = TerminalUtil.inLine();
        projectService.changeProjectStatusById(id, Status.COMPLETED);
    }

    @Override
    public void completeProjectByIndex() throws AbstractException {
        System.out.println("[COMPLETE PROJECT BY INDEX]");
        System.out.println("ENTER INDEX:");
        final Integer index = TerminalUtil.nextNumber() - 1;
        projectService.changeProjectStatusByIndex(index, Status.COMPLETED);
    }

    @Override
    public void startProjectById() throws AbstractException {
        System.out.println("[IN PROGRESS PROJECT BY ID]");
        System.out.println("ENTER ID:");
        final String id = TerminalUtil.inLine();
        projectService.changeProjectStatusById(id, Status.IN_PROGRESS);
    }

    @Override
    public void startProjectByIndex() throws AbstractException {
        System.out.println("[IN PROGRESS PROJECT BY INDEX]");
        System.out.println("ENTER INDEX:");
        final Integer index = TerminalUtil.nextNumber() - 1;
        projectService.changeProjectStatusByIndex(index, Status.IN_PROGRESS);
    }

    @Override
    public String show(final Project project) {
        return "[ID: " + project.getId() + "]\n" +
                "[NAME: " + project.getName() + "]\n" +
                "[DESC: " + project.getDescription() + "]\n" +
                "[STATUS: " + project.getStatus() + "]";
    }

}
