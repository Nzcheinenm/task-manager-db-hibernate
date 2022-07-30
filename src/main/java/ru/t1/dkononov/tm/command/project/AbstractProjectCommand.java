package ru.t1.dkononov.tm.command.project;

import ru.t1.dkononov.tm.api.services.IProjectService;
import ru.t1.dkononov.tm.api.services.IProjectTaskService;
import ru.t1.dkononov.tm.command.AbstractCommand;
import ru.t1.dkononov.tm.enumerated.Status;
import ru.t1.dkononov.tm.model.Project;

public abstract class AbstractProjectCommand extends AbstractCommand {

    protected IProjectService getProjectService() {
        return serviceLocator.getProjectService();
    }

    protected IProjectTaskService getProjectTaskService() {
        return getServiceLocator().getProjectTaskService();
    }

    @Override
    public String getArgument() {
        return null;
    }

    protected void showProject(final Project project) {
        if (project == null) return;
        System.out.println("ID: " + project.getId());
        System.out.println("NAME: " + project.getName());
        System.out.println("DESCRIPTION: " + project.getDescription());
        System.out.println("STATUS: " + Status.toName(project.getStatus()));
    }

}
