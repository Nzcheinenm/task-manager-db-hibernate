package ru.t1.dkononov.tm.command.project;

import lombok.Getter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.t1.dkononov.tm.exception.AbstractException;
import ru.t1.dkononov.tm.exception.field.AbstractFieldException;
import ru.t1.dkononov.tm.model.Project;
import ru.t1.dkononov.tm.util.TerminalUtil;

public final class ProjectShowByIndexCommand extends AbstractProjectCommand {

    @Getter
    @NotNull
    public final String NAME = "project-show-by-index";

    @Getter
    @NotNull
    public final String DESCRIPTION = "Показать проект по индексу.";

    @Override
    public void execute() throws AbstractException {
        @Nullable final String userId = getUserId();
        System.out.println("[ENTER INDEX]");
        @NotNull final Integer value = TerminalUtil.nextNumber() - 1;
        @Nullable final Project project = getProjectService().findByIndex(userId, value);
        System.out.println(show(project));
    }

    @NotNull
    public String show(@NotNull final Project project) {
        return "[ID: " + project.getId() + "]\n" +
                "[NAME: " + project.getName() + "]\n" +
                "[DESC: " + project.getDescription() + "]\n" +
                "[STATUS: " + project.getStatus() + "]";
    }

}
