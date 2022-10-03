package ru.t1.dkononov.tm.exception.field;

public final class PasswordEmptyException extends AbstractFieldException {

    public PasswordEmptyException() {
        super("Error! Password is null...");
    }

}
