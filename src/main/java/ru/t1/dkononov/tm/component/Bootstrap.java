package ru.t1.dkononov.tm.component;

import ru.t1.dkononov.tm.api.*;
import ru.t1.dkononov.tm.constant.ArgumentConst;
import ru.t1.dkononov.tm.constant.CommandConst;
import ru.t1.dkononov.tm.controller.CommandController;
import ru.t1.dkononov.tm.api.ITaskController;
import ru.t1.dkononov.tm.controller.ProjectController;
import ru.t1.dkononov.tm.controller.TaskController;
import ru.t1.dkononov.tm.repository.CommandRepository;
import ru.t1.dkononov.tm.repository.ProjectRepository;
import ru.t1.dkononov.tm.repository.TaskRepository;
import ru.t1.dkononov.tm.service.CommandService;
import ru.t1.dkononov.tm.service.ProjectService;
import ru.t1.dkononov.tm.service.TaskService;
import ru.t1.dkononov.tm.util.TerminalUtil;

public class Bootstrap {

    private final ICommandRepository commandRepository = new CommandRepository();

    private final ICommandService commandService = new CommandService(commandRepository);

    private final ICommandController commandController = new CommandController(commandService);

    private final IProjectRepository projectRepository = new ProjectRepository();

    private final IProjectService projectService = new ProjectService(projectRepository);

    private final IProjectController projectController = new ProjectController(projectService);

    private final ITaskRepository taskRepository = new TaskRepository();

    private final ITaskService taskService = new TaskService(taskRepository);

    private final ITaskController taskController = new TaskController(taskService);

    public void run(final String[] args) {
        processArguments(args);
        processCommands();
    }

    private void processArguments(final String[] args) {
        if (args == null || args.length == 0) return;
        final String argument = args[0];
        processArgument(argument);
    }

    private void processCommands() {
        System.out.println("** WELCOME TO TASK MANAGER **");
        while (!Thread.currentThread().isInterrupted()) {
            System.out.println("ENTER COMMAND: ");
            final String command = TerminalUtil.inLine();
            processCommand(command);
        }
    }

    private void processArgument(final String argument) {
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
                commandController.showErrorArgument();
                break;
        }
        System.exit(0);
    }

    private void processCommand(final String argument) {
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
                commandController.showExit();
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
            default:
                commandController.showErrorCommand();
                break;
        }
    }

}
