package ru.t1.dkononov.tm.api.services;

import ru.t1.dkononov.tm.enumerated.Sort;
import ru.t1.dkononov.tm.enumerated.Status;
import ru.t1.dkononov.tm.exception.AbstractException;
import ru.t1.dkononov.tm.exception.entity.AbstractEntityNotFoundException;
import ru.t1.dkononov.tm.exception.field.AbstractFieldException;
import ru.t1.dkononov.tm.model.Task;

import java.util.Comparator;
import java.util.List;

public interface ITaskService {
    List<Task> findAll();

    List<Task> findAll(final Comparator<Task> comparator);

    List<Task> findAll(final Sort sort);

    List<Task> findAllByProjectId(final String projectId);

    Task add(final Task project) throws AbstractEntityNotFoundException;

    void clear();

    Task create(final String name, final String description) throws AbstractFieldException;

    Task create(final String name) throws AbstractFieldException;

    Task findById(final String id) throws AbstractFieldException;

    Task findByIndex(final Integer index) throws AbstractFieldException;

    void remove(final Task task);

    Task removeById(final String id) throws AbstractFieldException;

    Task removeByIndex(final Integer index) throws AbstractFieldException;

    void updateById(final String id, final String name, final String description) throws AbstractException;

    void updateByIndex(final Integer index, final String name, final String description) throws AbstractException;

    void changeTaskStatusById(final String id, final Status status) throws AbstractException;

    void changeTaskStatusByIndex(final Integer index, final Status status) throws AbstractException;

}
