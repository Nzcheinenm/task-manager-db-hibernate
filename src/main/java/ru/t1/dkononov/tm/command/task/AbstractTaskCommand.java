package ru.t1.dkononov.tm.command.task;

import ru.t1.dkononov.tm.api.services.ICommandService;
import ru.t1.dkononov.tm.api.services.IProjectTaskService;
import ru.t1.dkononov.tm.api.services.ITaskService;
import ru.t1.dkononov.tm.command.AbstractCommand;
import ru.t1.dkononov.tm.enumerated.Status;
import ru.t1.dkononov.tm.model.Task;

import java.util.List;

public abstract class AbstractTaskCommand extends AbstractCommand {

    protected ITaskService getTaskService() {
        return serviceLocator.getTaskService();
    }

    protected IProjectTaskService getProjectTaskService() {
        return getServiceLocator().getProjectTaskService();
    }

    @Override
    public String getArgument() {
        return null;
    }

    protected void renderTasks(final List<Task> tasks) {
        int index = 1;
        for (final Task task : tasks) {
            if (task == null) continue;
            System.out.println(index + ". " + task);
            index++;
        }
    }

    protected void showTask(final Task task) {
        if (task == null) return;
        System.out.println("ID: " + task.getId());
        System.out.println("NAME: " + task.getName());
        System.out.println("DESCRIPTION: " + task.getDescription());
        System.out.println("STATUS: " + Status.toName(task.getStatus()));
    }

}
