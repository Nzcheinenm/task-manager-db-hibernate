package ru.t1.dkononov.tm.command.system;

import lombok.Getter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public final class ApplicationExitCommand extends AbstractSystemCommand {

    @Getter
    @NotNull
    public final String DESCRIPTION = "Закрыть приложение";

    @Getter
    @NotNull
    public final String NAME = "exit";

    @Nullable
    @Override
    public String getARGUMENT() {
        return null;
    }


    @Override
    public void execute() {
        System.exit(0);
    }

}
