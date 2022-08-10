package ru.t1.dkononov.tm.command.project;

import ru.t1.dkononov.tm.exception.AbstractException;

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
    public void execute() throws AbstractException {
        final String userId = getUserId();
        System.out.println("[PROJECT_CLEAR]");
        getProjectService().clear(userId);
    }

}
