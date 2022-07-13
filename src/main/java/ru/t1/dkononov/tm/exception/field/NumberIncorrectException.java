package ru.t1.dkononov.tm.exception.field;

public final class NumberIncorrectException extends AbstractFieldException {

    public NumberIncorrectException() {
        super("Error! Number is incorrect...");
    }

    public NumberIncorrectException(final String value,final Throwable e) {
        super("Error! Is value\"" + value + "\" is incorrect...");
    }

}
