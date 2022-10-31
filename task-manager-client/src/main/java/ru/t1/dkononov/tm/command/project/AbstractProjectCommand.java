package ru.t1.dkononov.tm.command.project;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.t1.dkononov.tm.api.endpoint.IProjectEndpoint;
import ru.t1.dkononov.tm.api.endpoint.ITaskEndpoint;
import ru.t1.dkononov.tm.command.AbstractCommand;
import ru.t1.dkononov.tm.enumerated.Role;
import ru.t1.dkononov.tm.enumerated.Status;
import ru.t1.dkononov.tm.dto.model.ProjectDTO;

public abstract class AbstractProjectCommand extends AbstractCommand {

    @NotNull
    protected IProjectEndpoint getProjectEndpoint() {
        return serviceLocator.getProjectEndpointClient();
    }

    @NotNull
    protected ITaskEndpoint getProjectTaskEndpoint() {
        return getServiceLocator().getTaskEndpointClient();
    }

    @Nullable
    @Override
    public String getARGUMENT() {
        return null;
    }

    protected void showProject(@Nullable final ProjectDTO project) {
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
