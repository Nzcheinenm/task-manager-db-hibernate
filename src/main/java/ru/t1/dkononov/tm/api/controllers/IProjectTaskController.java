package ru.t1.dkononov.tm.api.controllers;

import ru.t1.dkononov.tm.exception.AbstractException;

public interface IProjectTaskController {
    void bindTaskToProject() throws AbstractException;

    void unbindTaskFromProject() throws AbstractException;
}
