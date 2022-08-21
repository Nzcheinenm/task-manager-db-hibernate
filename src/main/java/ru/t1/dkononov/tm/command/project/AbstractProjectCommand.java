package ru.t1.dkononov.tm.command.project;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.t1.dkononov.tm.api.services.IProjectService;
import ru.t1.dkononov.tm.api.services.IProjectTaskService;
import ru.t1.dkononov.tm.command.AbstractCommand;
import ru.t1.dkononov.tm.enumerated.Role;
import ru.t1.dkononov.tm.enumerated.Status;
import ru.t1.dkononov.tm.exception.field.AccessDeniedException;
import ru.t1.dkononov.tm.model.Project;

public abstract class AbstractProjectCommand extends AbstractCommand {

    @NotNull
    protected IProjectService getProjectService() {
        return serviceLocator.getProjectService();
    }

    @NotNull
    protected IProjectTaskService getProjectTaskService() {
        return getServiceLocator().getProjectTaskService();
    }

    @Nullable
    @Override
    public String getARGUMENT() {
        return null;
    }

    protected void showProject(@Nullable final Project project) {
        if (project == null) return;
        System.out.println("ID: " + project.getId());
        System.out.println("NAME: " + project.getName());
        System.out.println("DESCRIPTION: " + project.getDescription());
        System.out.println("STATUS: " + Status.toName(project.getStatus()));
    }

    @Nullable
    @Override
    public String getUserId() throws AccessDeniedException {
        return getAuthService().getUserId();
    }

    @Nullable
    public Role[] getRoles() {
        return Role.values();
    }

}
