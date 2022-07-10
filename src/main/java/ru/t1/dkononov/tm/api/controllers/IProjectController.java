package ru.t1.dkononov.tm.api.controllers;

import ru.t1.dkononov.tm.exception.AbstractException;
import ru.t1.dkononov.tm.exception.field.AbstractFieldException;
import ru.t1.dkononov.tm.model.Project;
import ru.t1.dkononov.tm.util.TerminalUtil;

public interface IProjectController {
    void showProjects();

    void addProject() throws AbstractFieldException;

    void clearProjects();

    void showProjectById() throws AbstractFieldException;

    void showProjectByIndex() throws AbstractFieldException;

    void removeProjectById() throws AbstractException;

    void removeProjectByIndex() throws AbstractException;

    void updateProjectById() throws AbstractException;

    void updateProjectByIndex() throws AbstractException;

    void changeProjectStatusById() throws AbstractException;

    void changeProjectStatusByIndex() throws AbstractException;

    void completeProjectById() throws AbstractException;

    void completeProjectByIndex() throws AbstractException;

    void startProjectById() throws AbstractException;

    void startProjectByIndex() throws AbstractException;

    String show(final Project project);

}
