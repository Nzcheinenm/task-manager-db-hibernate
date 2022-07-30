package ru.t1.dkononov.tm.command.project;

public final class ProjectClearCommand extends AbstractProjectCommand {

    public static final String NAME = "project-clear";

    public static final String DESCRIPTION = "Очистить список проектов.";

    @Override
    public String getDescription() {
        return DESCRIPTION;
    }

    @Override
    public String getName() {
        return NAME;
    }

    @Override
    public void execute() {
        System.out.println("[PROJECT_CLEAR]");
        getProjectService().clear();
    }

}
