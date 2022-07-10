package ru.t1.dkononov.tm.exception.field;

public final class ProjectIdEmptyException extends AbstractFieldException{

    public ProjectIdEmptyException() {
        super("Error! Project Id is empty...");
    }

}
