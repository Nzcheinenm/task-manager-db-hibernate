package ru.t1.dkononov.tm.api.model;

import ru.t1.dkononov.tm.api.services.IServiceLocator;
import ru.t1.dkononov.tm.exception.AbstractException;
import ru.t1.dkononov.tm.exception.field.AbstractFieldException;

public interface ICommand {

    String getArgument();

    String getDescription();

    String getName();

    void execute() throws AbstractException;

}
