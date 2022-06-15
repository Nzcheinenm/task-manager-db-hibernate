package ru.t1.dkononov.tm.api;

import ru.t1.dkononov.tm.model.Command;

public interface ICommandRepository {
    Command[] getCommands();
}
