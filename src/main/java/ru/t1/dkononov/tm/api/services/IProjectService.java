package ru.t1.dkononov.tm.api.services;

import ru.t1.dkononov.tm.enumerated.Status;
import ru.t1.dkononov.tm.exception.AbstractException;
import ru.t1.dkononov.tm.exception.field.AbstractFieldException;
import ru.t1.dkononov.tm.model.Project;

public interface IProjectService extends IService<Project> {

    Project create(final String name, final String description) throws AbstractFieldException;

    Project create(final String name) throws AbstractFieldException;

    void updateById(final String id, final String name, final String description) throws AbstractException;

    void updateByIndex(final Integer index, final String name, final String description) throws AbstractException;

    void changeProjectStatusById(final String id, final Status status) throws AbstractException;

    void changeProjectStatusByIndex(final Integer index, final Status status) throws AbstractException;

}
