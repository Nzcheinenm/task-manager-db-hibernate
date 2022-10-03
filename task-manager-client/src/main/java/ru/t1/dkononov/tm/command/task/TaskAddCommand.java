package ru.t1.dkononov.tm.command.task;

import lombok.Getter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.t1.dkononov.tm.exception.field.AbstractFieldException;
import ru.t1.dkononov.tm.util.TerminalUtil;

public final class TaskAddCommand extends AbstractTaskCommand {

    @Getter
    @NotNull
    public final String NAME = "task-add";

    @Getter
    @NotNull
    public final String DESCRIPTION = "Создать новую задачу.";

    @Override
    public void execute() throws AbstractFieldException {
        @Nullable final String userId = getUserId();
        System.out.println("[CREATE NEW TASK]");
        System.out.println("ENTER NAME:");
        @NotNull final String name = TerminalUtil.inLine();
        System.out.println("ENTER DESCRIPTION:");
        @NotNull final String description = TerminalUtil.inLine();
        getTaskService().create(userId, name, description);
    }

}
