package ru.t1.dkononov.tm.api.services;

import ru.t1.dkononov.tm.enumerated.Status;
import ru.t1.dkononov.tm.exception.AbstractException;
import ru.t1.dkononov.tm.exception.field.AbstractFieldException;
import ru.t1.dkononov.tm.model.Project;

public interface IProjectService extends IUserOwnedService<Project> {

    Project create(String userId, String name, String description) throws AbstractFieldException;

    Project create(String userId, String name) throws AbstractFieldException;

    void updateById(String userId, String id, String name, String description) throws AbstractException;

    void updateByIndex(String userId, Integer index, String name, String description) throws AbstractException;

    void changeProjectStatusById(String userId, String id, Status status) throws AbstractException;

    void changeProjectStatusByIndex(String userId, Integer index, Status status) throws AbstractException;

}
