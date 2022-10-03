package ru.t1.dkononov.tm.command.task;

import lombok.Getter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.t1.dkononov.tm.exception.AbstractException;
import ru.t1.dkononov.tm.exception.field.AbstractFieldException;
import ru.t1.dkononov.tm.util.TerminalUtil;

public final class TaskRemoveByIdCommand extends AbstractTaskCommand {

    @Getter
    @NotNull
    public final String NAME = "task-remove-by-id";

    @Getter
    @NotNull
    public final String DESCRIPTION = "Удалить задачу по Id.";

    @Override
    public void execute() throws AbstractException {
        @Nullable final String userId = getUserId();
        System.out.println("[ENTER ID]");
        @NotNull final String value = TerminalUtil.inLine();
        getTaskService().removeById(userId, value);
    }

}
