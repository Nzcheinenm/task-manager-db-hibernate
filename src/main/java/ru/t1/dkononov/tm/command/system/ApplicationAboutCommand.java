package ru.t1.dkononov.tm.command.system;

public final class ApplicationAboutCommand extends AbstractSystemCommand {

    public static final String DESCRIPTION = "Информация о разработчике";

    public static final String NAME = "about";

    public static final String ARGUMENT = "-a";

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
        System.out.println("[ABOUT]");
        System.out.println("Имя: Дмитрий Кононов");
        System.out.println("E-mail: dkononov@t1-consulting.ru");
    }

}
