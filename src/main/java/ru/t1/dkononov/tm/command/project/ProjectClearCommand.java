package ru.t1.dkononov.tm.command.project;

public final class ProjectClearCommand extends AbstractProjectCommand {

    public static final String PROJECT_CLEAR = "project-clear";

    @Override
    public String getDescription() {
        return "Очистить список проектов.";
    }

    @Override
    public String getName() {
        return PROJECT_CLEAR;
    }

    @Override
    public void execute() {
        System.out.println("[PROJECT_CLEAR]");
        getProjectService().clear();
    }

}
