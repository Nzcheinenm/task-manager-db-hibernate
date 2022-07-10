package ru.t1.dkononov.tm.exception.field;

public final class DescriptionEmptyException extends AbstractFieldException{

    public DescriptionEmptyException() {
        super("Error! Description is empty...");
    }

}
