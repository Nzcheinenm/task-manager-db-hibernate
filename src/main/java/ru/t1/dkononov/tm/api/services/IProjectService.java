package ru.t1.dkononov.tm.api.services;

import ru.t1.dkononov.tm.enumerated.Sort;
import ru.t1.dkononov.tm.enumerated.Status;
import ru.t1.dkononov.tm.exception.AbstractException;
import ru.t1.dkononov.tm.exception.field.AbstractFieldException;
import ru.t1.dkononov.tm.model.Project;

import java.util.Comparator;
import java.util.List;

public interface IProjectService {
    List<Project> findAll();

    List<Project> findAll(final Comparator<Project> comparator);

    List<Project> findAll(final Sort sort);

    Project add(final Project project) throws AbstractException;

    void clear();

    Project create(final String name, final String description) throws AbstractFieldException;

    Project create(final String name) throws AbstractFieldException;

    Project findById(final String id) throws AbstractFieldException;

    Project findByIndex(final Integer index) throws AbstractFieldException;

    void remove(final Project project);

    Project removeById(final String id) throws AbstractFieldException;

    Project removeByIndex(final Integer index) throws AbstractFieldException;

    void updateById(final String id, final String name, final String description) throws AbstractException;

    void updateByIndex(final Integer index, final String name, final String description) throws AbstractException;

    void changeProjectStatusById(final String id, final Status status) throws AbstractException;

    void changeProjectStatusByIndex(final Integer index, final Status status) throws AbstractException;

}
