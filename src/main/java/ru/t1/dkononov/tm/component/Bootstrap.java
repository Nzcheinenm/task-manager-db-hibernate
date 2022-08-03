package ru.t1.dkononov.tm.component;

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
import ru.t1.dkononov.tm.repository.CommandRepository;
import ru.t1.dkononov.tm.repository.ProjectRepository;
import ru.t1.dkononov.tm.repository.TaskRepository;
import ru.t1.dkononov.tm.repository.UserRepository;
import ru.t1.dkononov.tm.service.*;
import ru.t1.dkononov.tm.util.TerminalUtil;

public class Bootstrap implements IServiceLocator {

    private final ICommandRepository commandRepository = new CommandRepository();

    private final ICommandService commandService = new CommandService(commandRepository);

    private final IProjectRepository projectRepository = new ProjectRepository();

    private final IProjectService projectService = new ProjectService(projectRepository);

    private final ITaskRepository taskRepository = new TaskRepository();

    private final ITaskService taskService = new TaskService(taskRepository);

    private final IProjectTaskService projectTaskService = new ProjectTaskService(projectRepository, taskRepository);

    private final ILoggerService loggerService = new LoggerService();

    private final IUserRepository userRepository = new UserRepository();

    private final IUserService userService = new UserService(userRepository);

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
    }

    public void run(final String[] args) {
        if (processArgument(args)) System.exit(0);
        init();
        while (!Thread.currentThread().isInterrupted()) {
            try {
                System.out.println("ENTER COMMAND:");
                final String command = TerminalUtil.inLine();
                processCommand(command);
                System.out.println("[OK]");
                loggerService.command(command);
            } catch (final Exception e) {
                loggerService.error(e);
                System.err.println("[FAIL]");
            }
        }

    }

    private void init() {
        try {
            initData();
            initLogger();
        } catch (final AbstractException e) {
            loggerService.error(e);
            System.err.println("[INIT FAIL]");
        }
    }

    private void processArgument(final String argument) throws AbstractException {
        final AbstractCommand abstractCommand = commandService.getCommandByArgument(argument);
        if (abstractCommand == null) throw new ArgumentNotSupportedException(argument);
        abstractCommand.execute();
    }

    private boolean processArgument(final String[] args) {
        if (args == null || args.length == 0) return false;
        final String argument = args[0];
        try {
            processArgument(argument);
            return true;
        } catch (final Exception e) {
            loggerService.error(e);
            return false;
        }
    }

    private void initData() throws AbstractException {
        userService.create("test", "test", "test@test.ru");
        userService.create("admin", "admin", Role.ADMIN);

        projectService.add(new Project("Jira", Status.NOT_STARTED));
        projectService.add(new Project("Confluence", Status.IN_PROGRESS));
        projectService.add(new Project("SoapUI", Status.IN_PROGRESS));

        taskService.add(new Task("Work", Status.IN_PROGRESS));
        taskService.add(new Task("Homework", Status.NOT_STARTED));
    }

    private void initLogger() {
        loggerService.info("** WELCOME TO TASK-MANAGER **");
        Runtime.getRuntime().addShutdownHook(new Thread(() ->
                loggerService.info("** TASK-MANAGER IS SHUTTING DOWN **")));
    }

    private void processCommand(final String command) throws AbstractException {
        final AbstractCommand abstractCommand = commandService.getCommandByName(command);
        if (abstractCommand == null) throw new CommandNotSupportedException(command);
        abstractCommand.execute();
    }

    private void registry(final AbstractCommand command) {
        command.setServiceLocator(this);
        commandService.add(command);
    }

    @Override
    public IAuthService getAuthService() {
        return authService;
    }

    @Override
    public IUserService getUserService() {
        return userService;
    }

    @Override
    public ICommandService getCommandService() {
        return commandService;
    }

    @Override
    public ILoggerService getLoggerService() {
        return loggerService;
    }

    @Override
    public IProjectService getProjectService() {
        return projectService;
    }

    @Override
    public IProjectTaskService getProjectTaskService() {
        return projectTaskService;
    }

    @Override
    public ITaskService getTaskService() {
        return taskService;
    }

}
