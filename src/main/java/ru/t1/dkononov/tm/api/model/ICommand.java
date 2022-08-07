package ru.t1.dkononov.tm.api.model;

import ru.t1.dkononov.tm.exception.AbstractException;

public interface ICommand {

    String getArgument();

    String getDescription();

    String getName();

    void execute() throws AbstractException;

}
