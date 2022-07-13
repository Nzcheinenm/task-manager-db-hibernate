package ru.t1.dkononov.tm.component;

import ru.t1.dkononov.tm.api.controllers.ICommandController;
import ru.t1.dkononov.tm.api.controllers.IProjectController;
import ru.t1.dkononov.tm.api.controllers.IProjectTaskController;
import ru.t1.dkononov.tm.api.repository.ICommandRepository;
import ru.t1.dkononov.tm.api.repository.IProjectRepository;
import ru.t1.dkononov.tm.api.repository.ITaskRepository;
import ru.t1.dkononov.tm.api.services.*;
import ru.t1.dkononov.tm.constant.ArgumentConst;
import ru.t1.dkononov.tm.constant.CommandConst;
import ru.t1.dkononov.tm.controller.CommandController;
import ru.t1.dkononov.tm.api.controllers.ITaskController;
import ru.t1.dkononov.tm.controller.ProjectController;
import ru.t1.dkononov.tm.controller.ProjectTaskController;
import ru.t1.dkononov.tm.controller.TaskController;
import ru.t1.dkononov.tm.enumerated.Status;
import ru.t1.dkononov.tm.exception.AbstractException;
import ru.t1.dkononov.tm.exception.system.ArgumentNotSupportedException;
import ru.t1.dkononov.tm.exception.system.CommandNotSupportedException;
import ru.t1.dkononov.tm.model.Project;
import ru.t1.dkononov.tm.model.Task;
import ru.t1.dkononov.tm.repository.CommandRepository;
import ru.t1.dkononov.tm.repository.ProjectRepository;
import ru.t1.dkononov.tm.repository.TaskRepository;
import ru.t1.dkononov.tm.service.*;
import ru.t1.dkononov.tm.util.TerminalUtil;
import sun.rmi.runtime.Log;

public class Bootstrap {

    private final ICommandRepository commandRepository = new CommandRepository();

    private final ICommandService commandService = new CommandService(commandRepository);

    private final ICommandController commandController = new CommandController(commandService);

    private final IProjectRepository projectRepository = new ProjectRepository();

    private final IProjectService projectService = new ProjectService(projectRepository);

    private final ITaskRepository taskRepository = new TaskRepository();

    private final ITaskService taskService = new TaskService(taskRepository);

    private final IProjectTaskService projectTaskService = new ProjectTaskService(projectRepository, taskRepository);

    private final IProjectController projectController = new ProjectController(projectService, projectTaskService);

    private final IProjectTaskController projectTaskController = new ProjectTaskController(projectTaskService);

    private final ITaskController taskController = new TaskController(taskService);

    private final ILoggerService loggerService = new LoggerService();

    public void run(final String[] args) {
        processArguments(args);
        processCommands();
    }

    public void close() {
        System.exit(0);
    }

    private void processArguments(final String[] args) {
        if (args == null || args.length == 0) return;
        final String argument = args[0];
        try {
            processArgument(argument);
        } catch (final Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void initData() throws AbstractException {
        projectService.add(new Project("Jira", Status.NOT_STARTED));
        projectService.add(new Project("Confluence", Status.IN_PROGRESS));
        projectService.add(new Project("SoapUI", Status.IN_PROGRESS));

        taskService.add(new Task("Work", Status.IN_PROGRESS));
        taskService.add(new Task("Homework", Status.NOT_STARTED));
    }

    private void processCommands() {
        try {
            initData();
        } catch (final AbstractException e) {
            loggerService.error(e);
            System.out.println("[FAIL TO INIT TEST DATA]");
        }

        initLogger();

        while (!Thread.currentThread().isInterrupted()) {
            try {
                System.out.println("ENTER COMMAND: ");
                final String command = TerminalUtil.inLine();
                processCommand(command);
                System.out.println("[OK]");
                loggerService.command(command);
            } catch (final Exception e) {
                loggerService.error(e);
                System.out.println("[FAIL]");
            }
        }
    }

    private void initLogger() {
        loggerService.info("** WELCOME TO TASK-MANAGER **");
        Runtime.getRuntime().addShutdownHook(new Thread(() ->
                loggerService.info("** TASK-MANAGER IS SHUTTING DOWN **")));
    }

    private void processArgument(final String argument) throws CommandNotSupportedException {
        if (argument == null || argument.isEmpty()) return;
        switch (argument) {
            case ArgumentConst.HELP:
                commandController.showHelp();
                break;
            case ArgumentConst.ABOUT:
                commandController.showAbout();
                break;
            case ArgumentConst.VERSION:
                commandController.showVersion();
                break;
            case ArgumentConst.INFO:
                commandController.showSystemInfo();
                break;
            default:
                throw new CommandNotSupportedException(argument);
        }
        System.exit(0);
    }

    private void processCommand(final String argument) throws AbstractException {
        if (argument == null || argument.isEmpty()) return;
        switch (argument) {
            case CommandConst.HELP:
                commandController.showHelp();
                break;
            case CommandConst.ABOUT:
                commandController.showAbout();
                break;
            case CommandConst.VERSION:
                commandController.showVersion();
                break;
            case CommandConst.INFO:
                commandController.showSystemInfo();
                break;
            case CommandConst.EXIT:
                close();
                break;
            case CommandConst.PROJECT_ADD:
                projectController.addProject();
                break;
            case CommandConst.PROJECT_CLEAR:
                projectController.clearProjects();
                break;
            case CommandConst.PROJECT_LIST:
                projectController.showProjects();
                break;
            case CommandConst.PROJECT_SHOW_BY_ID:
                projectController.showProjectById();
                break;
            case CommandConst.PROJECT_SHOW_BY_INDEX:
                projectController.showProjectByIndex();
                break;
            case CommandConst.PROJECT_REMOVE_BY_ID:
                projectController.removeProjectById();
                break;
            case CommandConst.PROJECT_REMOVE_BY_INDEX:
                projectController.removeProjectByIndex();
                break;
            case CommandConst.PROJECT_UPDATE_BY_ID:
                projectController.updateProjectById();
                break;
            case CommandConst.PROJECT_UPDATE_BY_INDEX:
                projectController.updateProjectByIndex();
                break;
            case CommandConst.PROJECT_CHANGE_STATUS_BY_ID:
                projectController.changeProjectStatusById();
                break;
            case CommandConst.PROJECT_CHANGE_STATUS_BY_INDEX:
                projectController.changeProjectStatusByIndex();
                break;
            case CommandConst.PROJECT_COMPLETE_BY_ID:
                projectController.completeProjectById();
                break;
            case CommandConst.PROJECT_COMPLETE_BY_INDEX:
                projectController.completeProjectByIndex();
                break;
            case CommandConst.PROJECT_START_BY_ID:
                projectController.startProjectById();
                break;
            case CommandConst.PROJECT_START_BY_INDEX:
                projectController.startProjectByIndex();
                break;

            case CommandConst.TASK_ADD:
                taskController.addTask();
                break;
            case CommandConst.TASK_CLEAR:
                taskController.clearTasks();
                break;
            case CommandConst.TASK_LIST:
                taskController.showTasks();
                break;
            case CommandConst.TASK_SHOW_BY_ID:
                taskController.showTaskById();
                break;
            case CommandConst.TASK_SHOW_BY_INDEX:
                taskController.showTaskByIndex();
                break;
            case CommandConst.TASK_REMOVE_BY_ID:
                taskController.removeTaskById();
                break;
            case CommandConst.TASK_REMOVE_BY_INDEX:
                taskController.removeTaskByIndex();
                break;
            case CommandConst.TASK_UPDATE_BY_ID:
                taskController.updateTaskById();
                break;
            case CommandConst.TASK_UPDATE_BY_INDEX:
                taskController.updateTaskByIndex();
                break;
            case CommandConst.TASK_CHANGE_STATUS_BY_ID:
                taskController.changeTaskStatusById();
                break;
            case CommandConst.TASK_CHANGE_STATUS_BY_INDEX:
                taskController.changeTaskStatusByIndex();
                break;
            case CommandConst.TASK_COMPLETE_BY_ID:
                taskController.completeTaskById();
                break;
            case CommandConst.TASK_COMPLETE_BY_INDEX:
                taskController.completeTaskByIndex();
                break;
            case CommandConst.TASK_START_BY_ID:
                taskController.startTaskById();
                break;
            case CommandConst.TASK_START_BY_INDEX:
                taskController.startTaskByIndex();
                break;
            case CommandConst.TASK_UNBIND_FROM_PROJECT:
                projectTaskController.unbindTaskFromProject();
                break;
            case CommandConst.TASK_SHOW_BY_PROJECT_ID:
                taskController.showTaskByProjectId();
                break;
            case CommandConst.TASK_BIND_TO_PROJECT:
                projectTaskController.bindTaskToProject();
                break;

            default:
                throw new ArgumentNotSupportedException(argument);
        }
    }

}
