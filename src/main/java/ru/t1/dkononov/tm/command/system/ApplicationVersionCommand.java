package ru.t1.dkononov.tm.command.system;

public final class ApplicationVersionCommand extends AbstractSystemCommand {

    public static final String VERSION = "1.17.0";

    public static final String DESCRIPTION = "Версия приложения";

    public static final String NAME = "version";

    public static final String ARGUMENT = "-version";

    @Override
    public String getArgument() {
        return ARGUMENT;
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
        System.out.println(VERSION);
    }

}
