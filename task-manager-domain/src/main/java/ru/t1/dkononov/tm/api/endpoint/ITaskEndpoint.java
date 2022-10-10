package ru.t1.dkononov.tm.api.endpoint;

import org.jetbrains.annotations.NotNull;
import ru.t1.dkononov.tm.dto.request.*;
import ru.t1.dkononov.tm.dto.response.*;
import ru.t1.dkononov.tm.exception.AbstractException;
import ru.t1.dkononov.tm.exception.field.AbstractFieldException;
import ru.t1.dkononov.tm.exception.field.AccessDeniedException;
import ru.t1.dkononov.tm.exception.field.UserIdEmptyException;

import javax.jws.WebMethod;
import javax.jws.WebService;

@WebService
public interface ITaskEndpoint {

    @NotNull
    @WebMethod
    TaskChangeStatusByIdResponse changeStatusById(
            @NotNull TaskChangeStatusByIdRequest request
    ) throws AbstractException;

    @NotNull
    @WebMethod
    TaskChangeStatusByIndexResponse changeStatusByIndex(
            @NotNull TaskChangeStatusByIndexRequest request
    ) throws AbstractException;

    @NotNull
    @WebMethod
    TaskClearResponse clearTask(@NotNull TaskClearRequest request)
            throws UserIdEmptyException, AccessDeniedException;

    @NotNull
    @WebMethod
    TaskCreateResponse createTask(@NotNull TaskCreateRequest request) throws AbstractFieldException;

    @NotNull
    @WebMethod
    TaskGetByIdResponse getTaskById(@NotNull TaskGetByIdRequest request) throws AbstractException;

    @NotNull
    @WebMethod
    TaskGetByIndexResponse getTaskByIndex(@NotNull TaskGetByIndexRequest request) throws AbstractException;

    @NotNull
    @WebMethod
    TaskListResponse listTask(@NotNull TaskListRequest request) throws AccessDeniedException, UserIdEmptyException;

    @NotNull
    @WebMethod
    TaskRemoveByIdResponse removeTaskById(
            @NotNull TaskRemoveByIdRequest request
    ) throws Exception;

    @NotNull
    @WebMethod
    TaskRemoveByIndexResponse removeTaskByIndex(
            @NotNull TaskRemoveByIndexRequest request
    ) throws Exception;

    @NotNull
    @WebMethod
    TaskStartByIdResponse startTaskById(
            @NotNull TaskStartByIdRequest request
    ) throws Exception;

    @NotNull
    @WebMethod
    TaskStartByIndexResponse startTaskByIndex(
            @NotNull TaskStartByIndexRequest request
    ) throws Exception;

    @NotNull
    @WebMethod
    TaskCompleteByIdResponse completeTaskById(
            @NotNull TaskCompleteByIdRequest request
    ) throws Exception;

    @NotNull
    @WebMethod
    TaskCompleteByIndexResponse completeTaskByIndex(
            @NotNull TaskCompleteByIndexRequest request
    ) throws Exception;

    @NotNull
    @WebMethod
    TaskBindToProjectResponse bindTaskToProject(
            @NotNull TaskBindToProjectRequest request
    ) throws Exception;

    @NotNull
    @WebMethod
    TaskUnbindFromProjectResponse unbindTaskToProject(
            @NotNull TaskUnbindFromProjectRequest request
    ) throws Exception;

    @NotNull
    @WebMethod
    TaskUpdateByIdResponse updateTaskById(
            @NotNull TaskUpdateByIdRequest request
    ) throws Exception;

    @NotNull
    @WebMethod
    TaskUpdateByIndexResponse updateTaskByIndex(
            @NotNull TaskUpdateByIndexRequest request
    ) throws Exception;

    @NotNull
    @WebMethod
    TaskListByProjectIdResponse listTasksToProjectId(
            @NotNull TaskListByProjectIdRequest request
    ) throws Exception;

}
