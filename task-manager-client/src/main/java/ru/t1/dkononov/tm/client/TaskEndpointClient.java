package ru.t1.dkononov.tm.client;

import lombok.NoArgsConstructor;
import lombok.SneakyThrows;
import org.jetbrains.annotations.NotNull;
import ru.t1.dkononov.tm.api.client.ITaskEndpointClient;
import ru.t1.dkononov.tm.dto.request.*;
import ru.t1.dkononov.tm.dto.response.*;
import ru.t1.dkononov.tm.exception.AbstractException;
import ru.t1.dkononov.tm.exception.field.AbstractFieldException;
import ru.t1.dkononov.tm.exception.field.AccessDeniedException;
import ru.t1.dkononov.tm.exception.field.UserIdEmptyException;

@NoArgsConstructor
public final class TaskEndpointClient extends AbstractEndpointClient implements ITaskEndpointClient {

    public TaskEndpointClient(@NotNull AbstractEndpointClient client) {
        super(client);
    }

    @Override
    @NotNull
    @SneakyThrows
    public TaskChangeStatusByIdResponse changeStatusById(
            @NotNull final TaskChangeStatusByIdRequest request
    ) throws AbstractException {
        return call(request, TaskChangeStatusByIdResponse.class);
    }

    @Override
    @NotNull
    @SneakyThrows
    public TaskChangeStatusByIndexResponse changeStatusByIndex(
            @NotNull final TaskChangeStatusByIndexRequest request
    ) throws AbstractException {
        return call(request, TaskChangeStatusByIndexResponse.class);
    }

    @Override
    @NotNull
    @SneakyThrows
    public TaskClearResponse clearTask(@NotNull final TaskClearRequest request)
            throws UserIdEmptyException, AccessDeniedException {
        return call(request, TaskClearResponse.class);
    }

    @Override
    @NotNull
    @SneakyThrows
    public TaskCreateResponse createTask(@NotNull final TaskCreateRequest request) throws AbstractFieldException {
        return call(request, TaskCreateResponse.class);
    }

    @Override
    @NotNull
    @SneakyThrows
    public TaskGetByIdResponse getTaskById(@NotNull TaskGetByIdRequest request) throws AbstractException {
        return call(request, TaskGetByIdResponse.class);
    }

    @Override
    @NotNull
    @SneakyThrows
    public TaskGetByIndexResponse getTaskByIndex(@NotNull final TaskGetByIndexRequest request) throws AbstractException {
        return call(request, TaskGetByIndexResponse.class);
    }

    @Override
    @NotNull
    @SneakyThrows
    public TaskListResponse listTask(@NotNull TaskListRequest request) throws AccessDeniedException, UserIdEmptyException {
        return call(request, TaskListResponse.class);
    }

    @Override
    @NotNull
    public TaskRemoveByIdResponse removeTaskById(
            @NotNull final TaskRemoveByIdRequest request
    ) throws Exception {
        return call(request, TaskRemoveByIdResponse.class);
    }

    @Override
    @NotNull
    public TaskRemoveByIndexResponse removeTaskByIndex(
            @NotNull final TaskRemoveByIndexRequest request
    ) throws Exception {
        return call(request, TaskRemoveByIndexResponse.class);
    }

    @Override
    @NotNull
    public TaskStartByIdResponse startTaskById(
            @NotNull final TaskStartByIdRequest request
    ) throws Exception {
        return call(request, TaskStartByIdResponse.class);
    }

    @Override
    @NotNull
    public TaskStartByIndexResponse startTaskByIndex(
            @NotNull final TaskStartByIndexRequest request
    ) throws Exception {
        return call(request, TaskStartByIndexResponse.class);
    }

    @Override
    @NotNull
    public TaskCompleteByIdResponse completeTaskById(
            @NotNull final TaskCompleteByIdRequest request
    ) throws Exception {
        return call(request, TaskCompleteByIdResponse.class);
    }

    @Override
    @NotNull
    public TaskCompleteByIndexResponse completeTaskByIndex(
            @NotNull final TaskCompleteByIndexRequest request
    ) throws Exception {
        return call(request, TaskCompleteByIndexResponse.class);
    }

    @Override
    @NotNull
    public TaskBindToProjectResponse bindTaskToProject(
            @NotNull final TaskBindToProjectRequest request
    ) throws Exception {
        return call(request, TaskBindToProjectResponse.class);
    }

    @Override
    public @NotNull TaskUnbindFromProjectResponse unbindTaskToProject(@NotNull TaskUnbindFromProjectRequest request) throws Exception {
        return call(request, TaskUnbindFromProjectResponse.class);
    }

    @Override
    public @NotNull TaskUpdateByIdResponse updateTaskById(@NotNull TaskUpdateByIdRequest request) throws Exception {
        return call(request, TaskUpdateByIdResponse.class);
    }

    @Override
    public @NotNull TaskUpdateByIndexResponse updateTaskByIndex(@NotNull TaskUpdateByIndexRequest request) throws Exception {
        return call(request, TaskUpdateByIndexResponse.class);
    }

    @Override
    public @NotNull TaskListByProjectIdResponse listTasksToProjectId(@NotNull TaskListByProjectIdRequest request) throws Exception {
        return call(request, TaskListByProjectIdResponse.class);
    }

}
