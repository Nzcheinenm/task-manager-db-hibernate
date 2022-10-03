package ru.t1.dkononov.tm.exception.entity;

public final class TaskNotFoundException extends AbstractEntityNotFoundException {

    public TaskNotFoundException() {
        super("Error!Task not found...");
    }

}
