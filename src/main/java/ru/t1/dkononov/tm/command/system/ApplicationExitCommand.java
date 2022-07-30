package ru.t1.dkononov.tm.command.system;

public final class ApplicationExitCommand extends AbstractSystemCommand {

    public static final String DESCRIPTION = "Закрыть приложение";

    public static final String NAME = "exit";

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
        System.exit(0);
    }

}
