package ru.t1.dkononov.tm.api;

import ru.t1.dkononov.tm.model.Project;
import ru.t1.dkononov.tm.util.TerminalUtil;

public interface IProjectController {
    void showProjects();

    void addProject();

    void clearProjects();

    void showProjectById();

    void showProjectByIndex();

    void removeProjectById();

    void removeProjectByIndex();

    void updateProjectById();

    void updateProjectByIndex();

    String show(final Project project);

}
