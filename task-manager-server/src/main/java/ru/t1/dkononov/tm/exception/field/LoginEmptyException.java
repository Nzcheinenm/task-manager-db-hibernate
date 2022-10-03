package ru.t1.dkononov.tm.exception.field;

public final class LoginEmptyException extends AbstractFieldException {

    public LoginEmptyException() {
        super("Error! Login is null...");
    }

}
