package ru.t1.dkononov.tm.command.project;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.t1.dkononov.tm.api.client.IProjectEndpointClient;
import ru.t1.dkononov.tm.api.client.ITaskEndpointClient;
import ru.t1.dkononov.tm.command.AbstractCommand;
import ru.t1.dkononov.tm.enumerated.Role;
import ru.t1.dkononov.tm.enumerated.Status;
import ru.t1.dkononov.tm.exception.field.AccessDeniedException;
import ru.t1.dkononov.tm.model.Project;

public abstract class AbstractProjectCommand extends AbstractCommand {

    @NotNull
    protected IProjectEndpointClient getProjectEndpoint() {
        return serviceLocator.getProjectEndpointClient();
    }

    @NotNull
    protected ITaskEndpointClient getProjectTaskEndpoint() {
        return getServiceLocator().getTaskEndpointClient();
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
    public Role[] getRoles() {
        return Role.values();
    }

}
