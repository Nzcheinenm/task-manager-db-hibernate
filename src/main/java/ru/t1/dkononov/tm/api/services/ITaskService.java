package ru.t1.dkononov.tm.api.services;

import ru.t1.dkononov.tm.enumerated.Status;
import ru.t1.dkononov.tm.exception.AbstractException;
import ru.t1.dkononov.tm.exception.field.AbstractFieldException;
import ru.t1.dkononov.tm.model.Task;

import java.util.List;

public interface ITaskService extends IService<Task> {

    List<Task> findAllByProjectId(final String projectId);

    Task create(final String name, final String description) throws AbstractFieldException;

    Task create(final String name) throws AbstractFieldException;

    void updateById(final String id, final String name, final String description) throws AbstractException;

    void updateByIndex(final Integer index, final String name, final String description) throws AbstractException;

    void changeTaskStatusById(final String id, final Status status) throws AbstractException;

    void changeTaskStatusByIndex(final Integer index, final Status status) throws AbstractException;

}
