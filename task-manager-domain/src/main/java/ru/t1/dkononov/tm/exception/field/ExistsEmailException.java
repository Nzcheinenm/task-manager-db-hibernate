package ru.t1.dkononov.tm.exception.field;

public final class ExistsEmailException extends AbstractFieldException {

    public ExistsEmailException() {
        super("Error! This Email in exist...");
    }

}
