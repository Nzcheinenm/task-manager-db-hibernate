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

    @NotNull
    public static final String FILE_XML = "./data.xml";

    @NotNull
    public static final String FILE_JSON = "./data.json";

    @NotNull
    public static final String FILE_YAML = "./data.yaml";

    @NotNull
    public final String CONTEXT_FACTORY = "javax.xml.bind.context.factory";

    @NotNull
    public final String CONTEXT_FACTORY_JAXB = "org.eclipse.persistence.jaxb.JAXBContextFactory";

    @NotNull
    public final String MEDIA_TYPE = "eclipselink.media-type";

    @NotNull
    public final String APPLICATION_TYPE_JSON = "application/json";

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
