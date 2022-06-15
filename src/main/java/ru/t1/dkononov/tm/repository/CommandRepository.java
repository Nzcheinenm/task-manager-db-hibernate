package ru.t1.dkononov.tm.repository;

import ru.t1.dkononov.tm.api.ICommandRepository;
import ru.t1.dkononov.tm.constant.ArgumentConst;
import ru.t1.dkononov.tm.constant.CommandConst;
import ru.t1.dkononov.tm.model.Command;

public class CommandRepository implements ICommandRepository {

    private static final Command ABOUT = new Command(
            CommandConst.ABOUT, ArgumentConst.ABOUT,
            "Показать информацию о разработчике."
    );
    private static final Command VERSION = new Command(
            CommandConst.VERSION, ArgumentConst.VERSION,
            "Показать версию приложения."
    );
    private static final Command HELP = new Command(
            CommandConst.HELP, ArgumentConst.HELP,
            "Показать справку о командах."
    );
    private static final Command EXIT = new Command(
            CommandConst.EXIT,null,
            "Выйти из программы."
    );
    private static final Command INFO = new Command(
            CommandConst.INFO, ArgumentConst.INFO,
            "Показать информацию о системе"
    );

    private static final Command[] COMMANDS = new Command[] {
        ABOUT,VERSION,HELP,EXIT,INFO
        };

    @Override
    public Command[] getCommands() {
        return COMMANDS;
    }

}
