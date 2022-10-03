package ru.t1.dkononov.tm.exception.field;

public final class TaskIdEmptyException extends AbstractFieldException {

    public TaskIdEmptyException() {
        super("Error! Task Id is empty...");
    }

}
