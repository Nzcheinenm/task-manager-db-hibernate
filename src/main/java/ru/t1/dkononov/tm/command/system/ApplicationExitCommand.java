package ru.t1.dkononov.tm.command.system;

public final class ApplicationExitCommand extends AbstractSystemCommand {

    @Override
    public String getArgument() {
        return null;
    }

    @Override
    public String getDescription() {
        return "Закрыть приложение";
    }

    @Override
    public String getName() {
        return "exit";
    }

    @Override
    public void execute() {
        System.exit(0);
    }

}
