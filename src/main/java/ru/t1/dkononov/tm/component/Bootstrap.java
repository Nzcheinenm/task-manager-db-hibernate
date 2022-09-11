package ru.t1.dkononov.tm.component;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.reflections.Reflections;
import ru.t1.dkononov.tm.api.repository.ICommandRepository;
import ru.t1.dkononov.tm.api.repository.IProjectRepository;
import ru.t1.dkononov.tm.api.repository.ITaskRepository;
import ru.t1.dkononov.tm.api.repository.IUserRepository;
import ru.t1.dkononov.tm.api.services.*;
import ru.t1.dkononov.tm.command.AbstractCommand;
import ru.t1.dkononov.tm.command.data.AbstractDataCommand;
import ru.t1.dkononov.tm.command.data.DataBase64LoadCommand;
import ru.t1.dkononov.tm.command.data.DataBinaryLoadCommand;
import ru.t1.dkononov.tm.enumerated.Role;
import ru.t1.dkononov.tm.enumerated.Status;
import ru.t1.dkononov.tm.exception.AbstractException;
import ru.t1.dkononov.tm.exception.system.ArgumentNotSupportedException;
import ru.t1.dkononov.tm.exception.system.CommandNotSupportedException;
import ru.t1.dkononov.tm.model.Project;
import ru.t1.dkononov.tm.model.Task;
import ru.t1.dkononov.tm.model.User;
import ru.t1.dkononov.tm.repository.CommandRepository;
import ru.t1.dkononov.tm.repository.ProjectRepository;
import ru.t1.dkononov.tm.repository.TaskRepository;
import ru.t1.dkononov.tm.repository.UserRepository;
import ru.t1.dkononov.tm.service.*;
import ru.t1.dkononov.tm.util.SystemUtil;
import ru.t1.dkononov.tm.util.TerminalUtil;

import javax.xml.bind.JAXBException;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Modifier;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Set;


@NoArgsConstructor
public final class Bootstrap implements IServiceLocator {

    @NotNull
    private static final String PACKAGE_COMMAND = "ru.t1.dkononov.tm.command";

    @NotNull
    private final ICommandRepository commandRepository = new CommandRepository();

    @Getter
    @NotNull
    private final ICommandService commandService = new CommandService(commandRepository);

    @NotNull
    private final IProjectRepository projectRepository = new ProjectRepository();

    @NotNull
    private final Backup backup = new Backup(this);

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
    private final IPropertyService propertyService = new PropertyService();

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
        @NotNull final Reflections reflections = new Reflections(PACKAGE_COMMAND);
        @NotNull final Set<Class<? extends AbstractCommand>> classes =
                reflections.getSubTypesOf(AbstractCommand.class);
        for (@NotNull final Class<? extends AbstractCommand> clazz : classes) {
            try {
                registry(clazz);
            } catch (@NotNull final InstantiationException | @NotNull IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void run(@NotNull final String[] args) {
        if (processArgument(args)) System.exit(0);
        init();
        while (!Thread.currentThread().isInterrupted()) {
            try {
                System.out.println("ENTER COMMAND:");
                @NotNull final String command = TerminalUtil.inLine();
                processCommand(command);
                System.out.println("[OK]");
                loggerService.command(command);
            } catch (@NotNull final Exception e) {
                loggerService.error(e);
                System.err.println("[FAIL]");
            }
        }
    }

    private void init() {
        try {
            initDemoData();
            initBackup();
            initLogger();
            initPID();
        } catch (final AbstractException | IOException | JAXBException | ClassNotFoundException e) {
            loggerService.error(e);
            System.err.println("[INIT FAIL]");
        }
    }

    private void initBackup() throws AbstractException, JAXBException, IOException, ClassNotFoundException {
        backup.init();
    }

    private void processArgument(@Nullable final String argument)
            throws AbstractException, IOException, ClassNotFoundException, JAXBException {
        @Nullable final AbstractCommand abstractCommand = commandService.getCommandByArgument(argument);
        if (abstractCommand == null) throw new ArgumentNotSupportedException(argument);
        abstractCommand.execute();
    }

    private boolean processArgument(@Nullable final String[] args) {
        if (args == null || args.length == 0) return false;
        @Nullable final String argument = args[0];
        try {
            processArgument(argument);
            return true;
        } catch (@NotNull final Exception e) {
            loggerService.error(e);
            return false;
        }
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
        loggerService.info("** WELCOME TO TASK-MANAGER **");
        Runtime.getRuntime().addShutdownHook(new Thread(() ->
                loggerService.info("** TASK-MANAGER IS SHUTTING DOWN **")));
    }

    public void processCommand(@Nullable final String command, final boolean checkRoles)
            throws AbstractException, IOException, ClassNotFoundException, JAXBException {
        @Nullable final AbstractCommand abstractCommand = commandService.getCommandByName(command);
        if (abstractCommand == null) throw new CommandNotSupportedException(command);
        if (checkRoles) authService.checkRoles(abstractCommand.getRoles());
        abstractCommand.execute();
    }

    private void processCommand(@Nullable final String command)
            throws AbstractException, IOException, ClassNotFoundException, JAXBException {
        processCommand(command, true);
    }

    private void registry(@NotNull final Class<? extends AbstractCommand> clazz) throws InstantiationException, IllegalAccessException {
        if (Modifier.isAbstract(clazz.getModifiers())) return;
        if (!AbstractCommand.class.isAssignableFrom(clazz)) return;
        @NotNull final AbstractCommand command = clazz.newInstance();
        registry(command);
    }

    private void registry(@NotNull final AbstractCommand command) {
        command.setServiceLocator(this);
        commandService.add(command);
    }

}
