package ru.t1.dkononov.tm.exception.field;

public final class RoleEmptyException extends AbstractFieldException {

    public RoleEmptyException() {
        super("Error! Role is empty...");
    }

}
