package ru.t1.dkononov.tm.command.system;

public final class ApplicationAboutCommand extends AbstractSystemCommand {

    @Override
    public String getArgument() {
        return "-a";
    }

    @Override
    public String getDescription() {
        return "Информация о разработчике";
    }

    @Override
    public String getName() {
        return "about";
    }

    @Override
    public void execute() {
        System.out.println("[ABOUT]");
        System.out.println("Имя: Дмитрий Кононов");
        System.out.println("E-mail: dkononov@t1-consulting.ru");
    }

}
