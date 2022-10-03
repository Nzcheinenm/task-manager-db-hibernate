package ru.t1.dkononov.tm.exception.field;

public final class NameEmptyException extends AbstractFieldException {

    public NameEmptyException() {
        super("Error! Name is empty...");
    }

}
