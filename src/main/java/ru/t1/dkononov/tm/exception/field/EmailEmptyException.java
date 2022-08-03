package ru.t1.dkononov.tm.exception.field;

public final class EmailEmptyException extends AbstractFieldException {

    public EmailEmptyException() {
        super("Error! Email is null...");
    }

}
