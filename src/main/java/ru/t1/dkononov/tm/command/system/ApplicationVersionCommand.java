package ru.t1.dkononov.tm.command.system;

import lombok.Getter;
import org.jetbrains.annotations.NotNull;

public final class ApplicationVersionCommand extends AbstractSystemCommand {

    @NotNull
    public static final String VERSION = "1.17.0";

    @Getter
    @NotNull
    public final String DESCRIPTION = "Версия приложения";

    @Getter
    @NotNull
    public final String NAME = "version";

    @Getter
    @NotNull
    public final String ARGUMENT = "-version";

    @Override
    public void execute() {
        System.out.println(VERSION);
    }

}
