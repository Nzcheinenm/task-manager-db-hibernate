package ru.t1.dkononov.tm.repository;

import ru.t1.dkononov.tm.api.repository.ICommandRepository;
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
    private static final Command PROJECT_REMOVE_BY_ID = new Command(
            CommandConst.PROJECT_REMOVE_BY_ID, null,
            "Удалить проект по Id."
    );
    private static final Command PROJECT_REMOVE_BY_INDEX = new Command(
            CommandConst.PROJECT_REMOVE_BY_INDEX, null,
            "Удалить проект по индексу."
    );
    private static final Command PROJECT_SHOW_BY_ID = new Command(
            CommandConst.PROJECT_SHOW_BY_ID, null,
            "Показать проект по Id."
    );
    private static final Command PROJECT_SHOW_BY_INDEX = new Command(
            CommandConst.PROJECT_SHOW_BY_INDEX, null,
            "Показать проект по индексу."
    );
    private static final Command PROJECT_UPDATE_BY_ID = new Command(
            CommandConst.PROJECT_UPDATE_BY_ID, null,
            "Обновить проект по Id."
    );
    private static final Command PROJECT_UPDATE_BY_INDEX = new Command(
            CommandConst.PROJECT_UPDATE_BY_INDEX, null,
            "Обновить проект по индексу."
    );

    private static final Command PROJECT_CHANGE_STATUS_BY_ID = new Command(
            CommandConst.PROJECT_CHANGE_STATUS_BY_ID, null,
            "Поменять статус у проекта по Id."
    );
    private static final Command PROJECT_CHANGE_STATUS_BY_INDEX = new Command(
            CommandConst.PROJECT_CHANGE_STATUS_BY_INDEX, null,
            "Поменять статус у проекта по индексу."
    );
    private static final Command PROJECT_COMPLETE_BY_ID = new Command(
            CommandConst.PROJECT_COMPLETE_BY_ID, null,
            "Завершить проект по Id."
    );
    private static final Command PROJECT_COMPLETE_BY_INDEX = new Command(
            CommandConst.PROJECT_COMPLETE_BY_INDEX, null,
            "Завершить проект по индексу."
    );
    private static final Command PROJECT_START_BY_ID = new Command(
            CommandConst.PROJECT_START_BY_ID, null,
            "Начать проект по Id."
    );
    private static final Command PROJECT_START_BY_INDEX = new Command(
            CommandConst.PROJECT_START_BY_INDEX, null,
            "Начать проект по индексу."
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
    private static final Command TASK_REMOVE_BY_ID = new Command(
            CommandConst.TASK_REMOVE_BY_ID, null,
            "Удалить задачу по Id."
    );
    private static final Command TASK_REMOVE_BY_INDEX = new Command(
            CommandConst.TASK_REMOVE_BY_INDEX, null,
            "Удалить задачу по индексу."
    );
    private static final Command TASK_SHOW_BY_ID = new Command(
            CommandConst.TASK_SHOW_BY_ID, null,
            "Показать задачу по Id."
    );
    private static final Command TASK_SHOW_BY_INDEX = new Command(
            CommandConst.TASK_SHOW_BY_INDEX, null,
            "Показать задачу по индексу."
    );
    private static final Command TASK_UPDATE_BY_ID = new Command(
            CommandConst.TASK_UPDATE_BY_ID, null,
            "Обновить задачу по Id."
    );
    private static final Command TASK_UPDATE_BY_INDEX = new Command(
            CommandConst.TASK_UPDATE_BY_INDEX, null,
            "Обновить задачу по индексу."
    );

    private static final Command TASK_CHANGE_STATUS_BY_ID = new Command(
            CommandConst.TASK_CHANGE_STATUS_BY_ID, null,
            "Поменять статус задачи по Id."
    );
    private static final Command TASK_CHANGE_STATUS_BY_INDEX = new Command(
            CommandConst.TASK_CHANGE_STATUS_BY_INDEX, null,
            "Поменять статус задачи по индексу."
    );
    private static final Command TASK_COMPLETE_BY_ID = new Command(
            CommandConst.TASK_COMPLETE_BY_ID, null,
            "Завершить задачу по Id."
    );
    private static final Command TASK_COMPLETE_BY_INDEX = new Command(
            CommandConst.TASK_COMPLETE_BY_INDEX, null,
            "Завершить задачу по индексу."
    );
    private static final Command TASK_START_BY_ID = new Command(
            CommandConst.TASK_START_BY_ID, null,
            "Начать задачу по Id."
    );
    private static final Command TASK_START_BY_INDEX = new Command(
            CommandConst.TASK_START_BY_INDEX, null,
            "Начать задачу по индексу."
    );
    private static final Command INFO = new Command(
            CommandConst.INFO, ArgumentConst.INFO,
            "Показать информацию о системе"
    );

    private static final Command TASK_BIND_TO_PROJECT = new Command(
            CommandConst.TASK_BIND_TO_PROJECT, null,
            "Привязать задачу к проекту."
    );
    private static final Command TASK_SHOW_BY_PROJECT_ID = new Command(
            CommandConst.TASK_SHOW_BY_PROJECT_ID, null,
            "Вывести задачи с нужным Project Id."
    );
    private static final Command TASK_UNBIND_FROM_PROJECT = new Command(
            CommandConst.TASK_UNBIND_FROM_PROJECT, null,
            "Отвязать задачу от Проекта"
    );

    private static final Command[] COMMANDS = new Command[]{
            TASK_ADD, TASK_LIST, TASK_CLEAR,
            TASK_REMOVE_BY_ID, TASK_REMOVE_BY_INDEX,
            TASK_SHOW_BY_ID, TASK_SHOW_BY_INDEX,
            TASK_UPDATE_BY_ID, TASK_UPDATE_BY_INDEX,
            TASK_CHANGE_STATUS_BY_ID, TASK_CHANGE_STATUS_BY_INDEX,
            TASK_COMPLETE_BY_ID, TASK_COMPLETE_BY_INDEX,
            TASK_START_BY_ID, TASK_START_BY_INDEX,

            TASK_BIND_TO_PROJECT, TASK_SHOW_BY_PROJECT_ID,
            TASK_UNBIND_FROM_PROJECT,

            PROJECT_ADD, PROJECT_CLEAR, PROJECT_LIST,
            PROJECT_REMOVE_BY_ID, PROJECT_REMOVE_BY_INDEX,
            PROJECT_SHOW_BY_ID, PROJECT_SHOW_BY_INDEX,
            PROJECT_UPDATE_BY_ID, PROJECT_UPDATE_BY_INDEX,
            PROJECT_CHANGE_STATUS_BY_ID, PROJECT_CHANGE_STATUS_BY_INDEX,
            PROJECT_COMPLETE_BY_ID, PROJECT_COMPLETE_BY_INDEX,
            PROJECT_START_BY_ID, PROJECT_START_BY_INDEX,

            ABOUT, VERSION, HELP, EXIT, INFO
    };

    @Override
    public Command[] getCommands() {
        return COMMANDS;
    }

}
