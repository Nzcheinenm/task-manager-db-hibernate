package ru.t1.dkononov.tm.component;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;
import ru.t1.dkononov.tm.api.endpoint.*;
import ru.t1.dkononov.tm.api.repository.IProjectRepository;
import ru.t1.dkononov.tm.api.repository.ISessionRepository;
import ru.t1.dkononov.tm.api.repository.ITaskRepository;
import ru.t1.dkononov.tm.api.repository.IUserRepository;
import ru.t1.dkononov.tm.api.services.*;
import ru.t1.dkononov.tm.endpoint.*;
import ru.t1.dkononov.tm.enumerated.Role;
import ru.t1.dkononov.tm.model.User;
import ru.t1.dkononov.tm.service.*;
import ru.t1.dkononov.tm.util.SystemUtil;

import javax.xml.ws.Endpoint;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;


@NoArgsConstructor
public final class Bootstrap implements IServiceLocator {

    @NotNull
    private static final String PACKAGE_COMMAND = "ru.t1.dkononov.tm.command";

    @Getter
    @NotNull
    private final IPropertyService propertyService = new PropertyService();

    @NotNull
    private final IConnectionService connectionService = new ConnectionService(propertyService);

    @Getter
    @NotNull
    private final IProjectService projectService = new ProjectService(connectionService);

    @Getter
    @NotNull
    private final ITaskService taskService = new TaskService(connectionService);

    @Getter
    @NotNull
    private final IProjectTaskService projectTaskService = new ProjectTaskService(projectService,taskService);

    @Getter
    @NotNull
    private final ILoggerService loggerService = new LoggerService();

    @Getter
    @NotNull
    private final IDomainService domainService = new DomainService(this);

    @Getter
    @NotNull
    private final ISessionService sessionService = new SessionService(connectionService);

    @NotNull
    private final IAuthEndpoint authEndpoint = new AuthEndpoint(this);

    @NotNull
    private final ISystemEndpoint systemEndpoint = new SystemEndpoint(this);

    @NotNull
    private final IDomainEndpoint domainEndpoint = new DomainEndpoint(this);

    @NotNull
    private final IProjectEndpoint projectEndpoint = new ProjectEndpoint(this);

    @NotNull
    private final ITaskEndpoint taskEndpoint = new TaskEndpoint(this);

    @NotNull
    private final IUserEndpoint userEndpoint = new UserEndpoint(this);

    @NotNull
    private final Backup backup = new Backup(this);

    @Getter
    @NotNull
    private final IUserService userService = new UserService(propertyService, connectionService);

    @Getter
    @NotNull
    private final IAuthService authService = new AuthService(userService, propertyService, sessionService);

    {
        registry(domainEndpoint);
        registry(systemEndpoint);
        registry(userEndpoint);
        registry(projectEndpoint);
        registry(taskEndpoint);
        registry(authEndpoint);
    }

    private void registry(@NotNull final Object endpoint) {
        @NotNull final String host = propertyService.getServerHost();
        @NotNull final String port = String.valueOf(propertyService.getServerPort());
        @NotNull final String name = endpoint.getClass().getSimpleName();
        @NotNull final String url = "http://" + host + ":" + port + "/" + name + "?wsdl";
        Endpoint.publish(url, endpoint);
        System.out.println(url);
    }

    public void start() {
        try {
            initDemoData();
            //initBackup();
            initLogger();
            initPID();
        } catch (final Exception e) {
            loggerService.error(e);
            System.err.println("[INIT FAIL]");
        }
    }

    private void initBackup() throws Exception {
        Runtime.getRuntime().addShutdownHook(new Thread(this::stop));
        backup.init();
    }

    private void stop() {
        backup.stop();
        loggerService.info("** TASK-MANAGER SERVER IS SHUTTING DOWN **");
    }

    private void initPID() throws IOException {
        @NotNull final String filename = "task-manager.pid";
        @NotNull final String pid = Long.toString(SystemUtil.getPID());
        Files.write(Paths.get(filename), pid.getBytes());
        @NotNull final File file = new File(filename);
        file.deleteOnExit();
    }

    private void initDemoData() throws Exception {
        @NotNull final User test = userService.create("test", "test", "test@test.ru");
        @NotNull final User user = userService.create("user", "user", "user@test.ru");
        @NotNull final User admin = userService.create("admin", "admin", Role.ADMIN);

        projectService.create(test.getId(),"Jira", "Desc");
        projectService.create(test.getId(), "Confluence","Conf");
        projectService.create(admin.getId(), "SoapUI", "Pelp");
        projectService.create(user.getId(), "Postman", "Ferst");

        taskService.create(test.getId(),"Work", "Working");
        taskService.create(admin.getId(), "Homework", "Speed");
    }


    private void initLogger() {
        loggerService.info("** WELCOME TO TASK-MANAGER SERVER **");
    }

}
