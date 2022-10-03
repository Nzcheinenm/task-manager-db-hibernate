package ru.t1.dkononov.tm.api.endpoint;

import org.jetbrains.annotations.NotNull;
import ru.t1.dkononov.tm.api.services.ITaskService;
import ru.t1.dkononov.tm.dto.request.*;
import ru.t1.dkononov.tm.dto.response.*;
import ru.t1.dkononov.tm.exception.AbstractException;
import ru.t1.dkononov.tm.exception.field.AbstractFieldException;
import ru.t1.dkononov.tm.exception.field.AccessDeniedException;
import ru.t1.dkononov.tm.exception.field.UserIdEmptyException;

public interface ITaskEndpoint {
    @NotNull ITaskService getTaskService();

    @NotNull TaskChangeStatusByIdResponse changeStatusById(
            @NotNull TaskChangeStatusByIdRequest request
    ) throws AbstractException;

    @NotNull TaskChangeStatusByIndexResponse changeStatusByIndex(
            @NotNull TaskChangeStatusByIndexRequest request
    ) throws AbstractException;

    @NotNull TaskClearResponse clearTask(@NotNull TaskClearRequest request)
            throws UserIdEmptyException, AccessDeniedException;

    @NotNull TaskCreateResponse createTask(@NotNull TaskCreateRequest request) throws AbstractFieldException;

    @NotNull TaskGetByIdResponse getTaskById(@NotNull TaskGetByIdRequest request) throws AbstractException;

    @NotNull TaskGetByIndexResponse getTaskByIndex(@NotNull TaskGetByIndexRequest request) throws AbstractException;

    @NotNull TaskListResponse listTask(@NotNull TaskListRequest request) throws AccessDeniedException, UserIdEmptyException;

    @NotNull TaskRemoveByIdResponse removeTaskById(
            @NotNull TaskRemoveByIdRequest request
    ) throws Exception;

    @NotNull TaskRemoveByIndexResponse removeTaskById(
            @NotNull TaskRemoveByIndexRequest request
    ) throws Exception;

    @NotNull TaskStartByIdResponse startTaskById(
            @NotNull TaskStartByIdRequest request
    ) throws Exception;

    @NotNull TaskStartByIndexResponse startTaskByIndex(
            @NotNull TaskStartByIndexRequest request
    ) throws Exception;

    @NotNull TaskCompleteByIdResponse completeTaskById(
            @NotNull TaskCompleteByIdRequest request
    ) throws Exception;

    @NotNull TaskCompleteByIndexResponse completeTaskByIndex(
            @NotNull TaskCompleteByIndexRequest request
    ) throws Exception;

    @NotNull TaskBindToProjectResponse bindTaskToProject(
            @NotNull TaskBindToProjectRequest request
    ) throws Exception;
}
