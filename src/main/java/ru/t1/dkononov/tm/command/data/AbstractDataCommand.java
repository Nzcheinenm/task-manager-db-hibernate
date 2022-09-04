package ru.t1.dkononov.tm.command.data;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.t1.dkononov.tm.command.AbstractCommand;
import ru.t1.dkononov.tm.dto.Domain;

public abstract class AbstractDataCommand extends AbstractCommand {

    @NotNull
    public static final String FILE_BASE64 = "./data.base64";

    @NotNull
    public static final String FILE_BINARY = "./data.bin";

    public AbstractDataCommand() {
    }

    @NotNull
    public Domain getDomain() {
        @NotNull final Domain domain = new Domain();
        domain.setProjects(serviceLocator.getProjectService().findAll());
        domain.setTasks(serviceLocator.getTaskService().findAll());
        domain.setUsers(serviceLocator.getUserService().findAll());
        return domain;
    }

    public void setDomain(@Nullable final Domain domain) {
        if (domain == null) return;
        serviceLocator.getProjectService().set(domain.getProjects());
        serviceLocator.getTaskService().set(domain.getTasks());
        serviceLocator.getUserService().set(domain.getUsers());
        serviceLocator.getAuthService().logout();
    }

}
