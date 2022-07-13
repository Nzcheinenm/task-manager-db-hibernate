package ru.t1.dkononov.tm.exception.entity;

public final class ProjectNotFoundException extends AbstractEntityNotFoundException {

    public ProjectNotFoundException() {
        super("Error!Project not found...");
    }

}
