package ru.t1.dkononov.tm.exception.field;

public class UserIdEmptyException extends AbstractFieldException {

    public UserIdEmptyException() {
        super("Error! User Id is empty...");
    }

}
