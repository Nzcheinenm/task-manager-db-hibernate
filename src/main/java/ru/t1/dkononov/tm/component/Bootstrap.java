package ru.t1.dkononov.tm.component;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.t1.dkononov.tm.api.repository.ICommandRepository;
import ru.t1.dkononov.tm.api.repository.IProjectRepository;
import ru.t1.dkononov.tm.api.repository.ITaskRepository;
import ru.t1.dkononov.tm.api.repository.IUserRepository;
import ru.t1.dkononov.tm.api.services.*;
import ru.t1.dkononov.tm.command.AbstractCommand;
import ru.t1.dkononov.tm.command.project.*;
import ru.t1.dkononov.tm.command.system.*;
import ru.t1.dkononov.tm.command.task.*;
import ru.t1.dkononov.tm.command.user.*;
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
import ru.t1.dkononov.tm.util.TerminalUtil;


@NoArgsConstructor
public final class Bootstrap implements IServiceLocator {

    @NotNull
    private final ICommandRepository commandRepository = new CommandRepository();

    @Getter
    @NotNull
    private final ICommandService commandService = new CommandService(commandRepository);

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

    @NotNull
    private final IUserRepository userRepository = new UserRepository();

    @Getter
    @NotNull
    private final IUserService userService = new UserService(
            userRepository,projectRepository,taskRepository
    );

    @Getter
    @NotNull
    private final IAuthService authService = new AuthService(userService);

    {
        registry(new ApplicationAboutCommand());
        registry(new ApplicationExitCommand());
        registry(new ApplicationHelpCommand());
        registry(new ApplicationVersionCommand());
        registry(new ArgumentListCommand());
        registry(new CommandListCommand());
        registry(new SystemInfoCommand());

        registry(new ProjectAddCommand());
        registry(new ProjectChangeStatusByIdCommand());
        registry(new ProjectChangeStatusByIndexCommand());
        registry(new ProjectClearCommand());
        registry(new ProjectCompleteByIdCommand());
        registry(new ProjectCompleteByIndexCommand());
        registry(new ProjectListCommand());
        registry(new ProjectRemoveByIdCommand());
        registry(new ProjectRemoveByIndexCommand());
        registry(new ProjectShowByIdCommand());
        registry(new ProjectShowByIndexCommand());
        registry(new ProjectStartByIdCommand());
        registry(new ProjectStartByIndexCommand());
        registry(new ProjectUpdateByIdCommand());
        registry(new ProjectUpdateByIndexCommand());

        registry(new TaskAddCommand());
        registry(new TaskChangeStatusByIdCommand());
        registry(new TaskChangeStatusByIndexCommand());
        registry(new TaskClearCommand());
        registry(new TaskCompleteByIdCommand());
        registry(new TaskCompleteByIndexCommand());
        registry(new TaskListCommand());
        registry(new TaskRemoveByIdCommand());
        registry(new TaskRemoveByIndexCommand());
        registry(new TaskShowByIdCommand());
        registry(new TaskShowByIndexCommand());
        registry(new TaskShowByProjectIdCommand());
        registry(new TaskStartByIdCommand());
        registry(new TaskStartByIndexCommand());
        registry(new TaskUnbindFromProjectCommand());
        registry(new TaskBindFromProjectCommand());
        registry(new TaskUpdateByIdCommand());
        registry(new TaskUpdateByIndexCommand());

        registry(new UserViewProfileCommand());
        registry(new UserChangePasswordCommand());
        registry(new UserRegistryCommand());
        registry(new UserUpdateProfileCommand());
        registry(new UserLogoutCommand());
        registry(new UserLoginCommand());
        registry(new UserLockCommand());
        registry(new UserUnlockCommand());
        registry(new UserRemoveCommand());
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
            initData();
            initLogger();
        } catch (@NotNull final AbstractException e) {
            loggerService.error(e);
            System.err.println("[INIT FAIL]");
        }
    }

    private void processArgument(@Nullable final String argument)
            throws AbstractException {
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

    private void initData() throws AbstractException {
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

    private void processCommand(@Nullable final String command) throws AbstractException {
        @Nullable final AbstractCommand abstractCommand = commandService.getCommandByName(command);
        if (abstractCommand == null) throw new CommandNotSupportedException(command);
        authService.checkRoles(abstractCommand.getRoles());
        abstractCommand.execute();
    }

    private void registry(@NotNull final AbstractCommand command) {
        command.setServiceLocator(this);
        commandService.add(command);
    }

}
