package ru.t1.dkononov.tm.command.system;

public final class ApplicationVersionCommand extends AbstractSystemCommand {

    public static final String VERSION = "1.17.0";

    @Override
    public String getArgument() {
        return "version";
    }

    @Override
    public String getDescription() {
        return "Версия приложения";
    }

    @Override
    public String getName() {
        return "version";
    }

    @Override
    public void execute() {
        System.out.println(VERSION);
    }

}
