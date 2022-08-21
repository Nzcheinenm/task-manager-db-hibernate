package ru.t1.dkononov.tm.command.system;

import lombok.Getter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.t1.dkononov.tm.util.FormatUtil;

public final class SystemInfoCommand extends AbstractSystemCommand {

    @Getter
    @NotNull
    public final String DESCRIPTION = "Показать описание системы";

    @Getter
    @NotNull
    public final String NAME = "info";


    @Nullable
    @Override
    public String getARGUMENT() {
        return null;
    }

    @Override
    public void execute() {
        @NotNull final Runtime runtime = Runtime.getRuntime();
        final long availableProcessors = runtime.availableProcessors();
        final long freeMemory = runtime.freeMemory();
        @NotNull final String freeMemoryFormat = FormatUtil.format(freeMemory);
        final long maxMemory = runtime.maxMemory();
        final boolean maxMemoryCheck = maxMemory == Long.MAX_VALUE;
        @NotNull final String maxMemoryFormat = maxMemoryCheck ? "no limit" : FormatUtil.format(maxMemory);
        final long totalMemory = runtime.totalMemory();
        @NotNull final String totalMemoryFormat = FormatUtil.format(totalMemory);

        System.out.println("Available processors (cores): " + availableProcessors);
        System.out.println("Free memory (bytes): " + freeMemoryFormat);
        System.out.println("Maximum memory (bytes): " + maxMemoryFormat);
        System.out.println("Total memory (bytes): " + totalMemoryFormat);
    }

}
