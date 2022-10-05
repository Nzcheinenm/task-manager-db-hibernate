package ru.t1.dkononov.tm.api.client;

import lombok.SneakyThrows;
import org.jetbrains.annotations.NotNull;
import ru.t1.dkononov.tm.dto.request.*;
import ru.t1.dkononov.tm.dto.response.*;
import ru.t1.dkononov.tm.exception.AbstractException;
import ru.t1.dkononov.tm.exception.field.AbstractFieldException;
import ru.t1.dkononov.tm.exception.field.AccessDeniedException;
import ru.t1.dkononov.tm.exception.field.UserIdEmptyException;

public interface ITaskEndpointClient {
    @NotNull
    @SneakyThrows
    TaskChangeStatusByIdResponse changeStatusById(
            @NotNull TaskChangeStatusByIdRequest request
    ) throws AbstractException;

    @NotNull
    @SneakyThrows
    TaskChangeStatusByIndexResponse changeStatusByIndex(
            @NotNull TaskChangeStatusByIndexRequest request
    ) throws AbstractException;

    @NotNull
    @SneakyThrows
    TaskClearResponse clearTask(@NotNull TaskClearRequest request)
            throws UserIdEmptyException, AccessDeniedException;

    @NotNull
    @SneakyThrows
    TaskCreateResponse createTask(@NotNull TaskCreateRequest request) throws AbstractFieldException;

    @NotNull
    @SneakyThrows
    TaskGetByIdResponse getTaskById(@NotNull TaskGetByIdRequest request) throws AbstractException;

    @NotNull
    @SneakyThrows
    TaskGetByIndexResponse getTaskByIndex(@NotNull TaskGetByIndexRequest request) throws AbstractException;

    @NotNull
    @SneakyThrows
    TaskListResponse listTask(@NotNull TaskListRequest request) throws AccessDeniedException, UserIdEmptyException;

    @NotNull TaskRemoveByIdResponse removeTaskById(
            @NotNull TaskRemoveByIdRequest request
    ) throws Exception;

    @NotNull TaskRemoveByIndexResponse removeTaskByIndex(
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

    @NotNull TaskUnbindFromProjectResponse unbindTaskToProject(
            @NotNull TaskUnbindFromProjectRequest request
    ) throws Exception;

    @NotNull TaskUpdateByIdResponse updateTaskById(
            @NotNull TaskUpdateByIdRequest request
    ) throws Exception;

    @NotNull TaskUpdateByIndexResponse updateTaskByIndex(
            @NotNull TaskUpdateByIndexRequest request
    ) throws Exception;

    @NotNull TaskListByProjectIdResponse listTasksToProjectId(
            @NotNull TaskListByProjectIdRequest request
    ) throws Exception;

}
