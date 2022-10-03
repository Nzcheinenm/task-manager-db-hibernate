package ru.t1.dkononov.tm.command.project;

import lombok.Getter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.t1.dkononov.tm.exception.AbstractException;
import ru.t1.dkononov.tm.util.TerminalUtil;

public final class ProjectUpdateByIdCommand extends AbstractProjectCommand {

    @Getter
    @NotNull
    public final String NAME = "project-update-by-id";

    @Getter
    @NotNull
    public final String DESCRIPTION = "Обновить проект по Id.";

    @Override
    public void execute() throws AbstractException {
        @Nullable final String userId = getUserId();
        System.out.println("[ENTER ID]");
        @NotNull final String id = TerminalUtil.inLine();
        System.out.println("[ENTER NAME]");
        @NotNull final String name = TerminalUtil.inLine();
        System.out.println("[ENTER DESCRIPTION]");
        @NotNull final String description = TerminalUtil.inLine();
        getProjectService().updateById(userId, id, name, description);
    }

}
