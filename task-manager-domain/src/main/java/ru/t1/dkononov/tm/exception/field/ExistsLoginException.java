package ru.t1.dkononov.tm.exception.field;

public final class ExistsLoginException extends AbstractFieldException {

    public ExistsLoginException() {
        super("Error! This login in exist...");
    }

}
