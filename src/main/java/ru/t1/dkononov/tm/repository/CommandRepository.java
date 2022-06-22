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
            CommandConst.EXIT, null,
            "Выйти из программы."
    );
    private static final Command PROJECT_ADD = new Command(
            CommandConst.PROJECT_ADD, null,
            "Создать новый проект."
    );
    private static final Command PROJECT_CLEAR = new Command(
            CommandConst.PROJECT_CLEAR, null,
            "Очистить список проектов."
    );
    private static final Command PROJECT_LIST = new Command(
            CommandConst.PROJECT_LIST, null,
            "Вывести список проектов."
    );
    private static final Command TASK_ADD = new Command(
            CommandConst.TASK_ADD, null,
            "Создать новую задачу."
    );
    private static final Command TASK_CLEAR = new Command(
            CommandConst.TASK_CLEAR, null,
            "Очистить список задач."
    );
    private static final Command TASK_LIST = new Command(
            CommandConst.TASK_LIST, null,
            "Вывести список задач."
    );
    private static final Command INFO = new Command(
            CommandConst.INFO, ArgumentConst.INFO,
            "Показать информацию о системе"
    );

    private static final Command[] COMMANDS = new Command[]{
            TASK_ADD,TASK_LIST,TASK_CLEAR,
            PROJECT_ADD,PROJECT_CLEAR,PROJECT_LIST,
            ABOUT, VERSION, HELP, EXIT, INFO
    };

    @Override
    public Command[] getCommands() {
        return COMMANDS;
    }

}
