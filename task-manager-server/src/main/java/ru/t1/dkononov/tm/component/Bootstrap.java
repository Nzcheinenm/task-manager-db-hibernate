package ru.t1.dkononov.tm.component;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;
import ru.t1.dkononov.tm.api.endpoint.*;
import ru.t1.dkononov.tm.api.repository.IProjectRepository;
import ru.t1.dkononov.tm.api.repository.ITaskRepository;
import ru.t1.dkononov.tm.api.repository.IUserRepository;
import ru.t1.dkononov.tm.api.services.*;
import ru.t1.dkononov.tm.dto.request.*;
import ru.t1.dkononov.tm.endpoint.*;
import ru.t1.dkononov.tm.enumerated.Role;
import ru.t1.dkononov.tm.enumerated.Status;
import ru.t1.dkononov.tm.exception.AbstractException;
import ru.t1.dkononov.tm.model.Project;
import ru.t1.dkononov.tm.model.Task;
import ru.t1.dkononov.tm.model.User;
import ru.t1.dkononov.tm.repository.ProjectRepository;
import ru.t1.dkononov.tm.repository.TaskRepository;
import ru.t1.dkononov.tm.repository.UserRepository;
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


    @NotNull
    private final IProjectRepository projectRepository = new ProjectRepository();

    @Getter
    @NotNull
    private final IProjectService projectService = new ProjectService(projectRepository);


    @NotNull
    private final ITaskRepository taskRepository = new TaskRepository();

    @Getter
    @NotNull
    private final ITaskService taskService = new TaskService(taskRepository);

    @Getter
    @NotNull
    private final IProjectTaskService projectTaskService = new ProjectTaskService(projectRepository, taskRepository);

    @Getter
    @NotNull
    private final ILoggerService loggerService = new LoggerService();

    @Getter
    @NotNull
    private final IDomainService domainService = new DomainService(this);

    @Getter
    @NotNull
    private final IPropertyService propertyService = new PropertyService();

//    @NotNull
//    private final IAuthEndpoint authEndpoint = new AuthEndpoint(this);

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
    private final Server server = new Server(this);

    @NotNull
    private final Backup backup = new Backup(this);


    @NotNull
    private final IUserRepository userRepository = new UserRepository();

    @Getter
    @NotNull
    private final IUserService userService = new UserService(
            userRepository, projectRepository, taskRepository,
            propertyService);

    @Getter
    @NotNull
    private final IAuthService authService = new AuthService(userService, propertyService);

    {
        registry(domainEndpoint);
        registry(systemEndpoint);
        registry(userEndpoint);
        registry(projectEndpoint);
        registry(taskEndpoint);
//        registry(authEndpoint);
    }

    private void registry(@NotNull final Object endpoint) {
        @NotNull final String host = "0.0.0.0";
        @NotNull final String port = "8080";
        @NotNull final String name = endpoint.getClass().getSimpleName();
        @NotNull final String url = "http://" + host + ":" + port + "/" + name + "?wsdl";
        Endpoint.publish(url, endpoint);
        System.out.println(url);
    }

    public void start() {
        try {
            initDemoData();
            initBackup();
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
        server.start();
    }

    private void stop() {
        try {
            backup.stop();
            server.stop();
        } catch (IOException e) {
            loggerService.error(e);
        }
        loggerService.info("** TASK-MANAGER SERVER IS SHUTTING DOWN **");
    }

    private void initPID() throws IOException {
        @NotNull final String filename = "task-manager.pid";
        @NotNull final String pid = Long.toString(SystemUtil.getPID());
        Files.write(Paths.get(filename), pid.getBytes());
        @NotNull final File file = new File(filename);
        file.deleteOnExit();
    }

    private void initDemoData() throws AbstractException {
        @NotNull final User test = userService.create("test", "test", "test@test.ru");
        @NotNull final User user = userService.create("user", "user", "user@test.ru");
        @NotNull final User admin = userService.create("admin", "admin", Role.ADMIN);

        projectService.add(test.getId(), new Project("Jira", Status.NOT_STARTED));
        projectService.add(test.getId(), new Project("Confluence", Status.IN_PROGRESS));
        projectService.add(admin.getId(), new Project("SoapUI", Status.IN_PROGRESS));
        projectService.add(user.getId(), new Project("Postman", Status.IN_PROGRESS));

        taskService.add(test.getId(), new Task("Work", Status.IN_PROGRESS));
        taskService.add(admin.getId(), new Task("Homework", Status.NOT_STARTED));
    }


    private void initLogger() {
        loggerService.info("** WELCOME TO TASK-MANAGER SERVER **");
    }

}
