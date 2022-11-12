package ru.t1.dkononov.tm.command.task;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.t1.dkononov.tm.api.endpoint.ITaskEndpoint;
import ru.t1.dkononov.tm.command.AbstractCommand;
import ru.t1.dkononov.tm.dto.model.TaskDTO;
import ru.t1.dkononov.tm.enumerated.Role;
import ru.t1.dkononov.tm.enumerated.Status;

import java.util.List;

public abstract class AbstractTaskCommand extends AbstractCommand {

    @NotNull
    protected ITaskEndpoint getTaskEndpointClient() {
        return serviceLocator.getTaskEndpointClient();
    }

//    @NotNull
//    protected IProjectTaskService getProjectTaskService() {
//        return getServiceLocator().getProjectTaskService();
//    }

    @Nullable
    @Override
    public String getARGUMENT() {
        return null;
    }

    protected void renderTasks(@NotNull final List<TaskDTO> tasks) {
        int index = 1;
        for (@Nullable final TaskDTO task : tasks) {
            if (task == null) continue;
            System.out.println(index + ". " + task);
            index++;
        }
    }

    protected void showTask(@Nullable final TaskDTO task) {
        if (task == null) return;
        System.out.println("ID: " + task.getId());
        System.out.println("NAME: " + task.getName());
        System.out.println("DESCRIPTION: " + task.getDescription());
        System.out.println("STATUS: " + Status.toName(task.getStatus()));
    }

    @Nullable
    public Role[] getRoles() {
        return Role.values();
    }

}
