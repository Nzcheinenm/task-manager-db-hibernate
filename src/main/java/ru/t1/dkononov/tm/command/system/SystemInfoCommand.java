package ru.t1.dkononov.tm.command.system;

import ru.t1.dkononov.tm.util.FormatUtil;

public final class SystemInfoCommand extends AbstractSystemCommand {

    public static final String DESCRIPTION = "Показать описание системы";

    public static final String NAME = "info";


    @Override
    public String getArgument() {
        return null;
    }

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
        final Runtime runtime = Runtime.getRuntime();
        final long availableProcessors = runtime.availableProcessors();
        final long freeMemory = runtime.freeMemory();
        final String freeMemoryFormat = FormatUtil.format(freeMemory);
        final long maxMemory = runtime.maxMemory();
        final boolean maxMemoryCheck = maxMemory == Long.MAX_VALUE;
        final String maxMemoryFormat = maxMemoryCheck ? "no limit" : FormatUtil.format(maxMemory);
        final long totalMemory = runtime.totalMemory();
        final String totalMemoryFormat = FormatUtil.format(totalMemory);

        System.out.println("Available processors (cores): " + availableProcessors);
        System.out.println("Free memory (bytes): " + freeMemoryFormat);
        System.out.println("Maximum memory (bytes): " + maxMemoryFormat);
        System.out.println("Total memory (bytes): " + totalMemoryFormat);
    }

}
