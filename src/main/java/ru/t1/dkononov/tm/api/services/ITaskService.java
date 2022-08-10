package ru.t1.dkononov.tm.api.services;

import ru.t1.dkononov.tm.enumerated.Status;
import ru.t1.dkononov.tm.exception.AbstractException;
import ru.t1.dkononov.tm.exception.field.AbstractFieldException;
import ru.t1.dkononov.tm.exception.field.UserIdEmptyException;
import ru.t1.dkononov.tm.model.Task;

import java.util.List;

public interface ITaskService extends IUserOwnedService<Task> {

    List<Task> findAllByProjectId(String userId, String projectId) throws UserIdEmptyException;

    Task create(String userId, String name, String description) throws AbstractFieldException;

    Task create(String userId, String name) throws AbstractFieldException;

    void updateById(String userId, String id, String name, String description) throws AbstractException;

    void updateByIndex(String userId, Integer index, String name, String description) throws AbstractException;

    void changeTaskStatusById(String userId, String id, Status status) throws AbstractException;

    void changeTaskStatusByIndex(String userId, Integer index, Status status) throws AbstractException;

}
