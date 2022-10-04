package ru.t1.dkononov.tm.endpoint;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.t1.dkononov.tm.api.endpoint.ITaskEndpoint;
import ru.t1.dkononov.tm.api.services.ITaskService;
import ru.t1.dkononov.tm.api.services.IServiceLocator;
import ru.t1.dkononov.tm.dto.request.*;
import ru.t1.dkononov.tm.dto.response.*;
import ru.t1.dkononov.tm.enumerated.Sort;
import ru.t1.dkononov.tm.enumerated.Status;
import ru.t1.dkononov.tm.exception.AbstractException;
import ru.t1.dkononov.tm.exception.field.AbstractFieldException;
import ru.t1.dkononov.tm.exception.field.AccessDeniedException;
import ru.t1.dkononov.tm.exception.field.UserIdEmptyException;
import ru.t1.dkononov.tm.model.Task;

import java.util.List;

public final class TaskEndpoint extends AbstractEndpoint implements ITaskEndpoint {

    public TaskEndpoint(@NotNull final IServiceLocator serviceLocator) {
        super(serviceLocator);
    }

    @Override
    @NotNull
    public ITaskService getTaskService() {
        return getServiceLocator().getTaskService();
    }

    @Override
    @NotNull
    public TaskChangeStatusByIdResponse changeStatusById(
            @NotNull final TaskChangeStatusByIdRequest request
    ) throws AbstractException {
        check(request);
        @Nullable final String id = request.getId();
        @Nullable final String userId = request.getUserId();
        @Nullable final Status status = Status.valueOf(request.getStatusValue());
        getTaskService().changeTaskStatusById(userId,id,status);
        return new TaskChangeStatusByIdResponse();
    }

    @Override
    @NotNull
    public TaskChangeStatusByIndexResponse changeStatusByIndex(
            @NotNull final TaskChangeStatusByIndexRequest request
    ) throws AbstractException {
        check(request);
        @Nullable final Integer index = request.getIndex();
        @Nullable final String userId = request.getUserId();
        @Nullable final Status status = Status.valueOf(request.getStatusValue());
        getTaskService().changeTaskStatusByIndex(userId,index,status);
        return new TaskChangeStatusByIndexResponse();
    }

    @Override
    @NotNull
    public TaskClearResponse clearTask(@NotNull final TaskClearRequest request)
            throws UserIdEmptyException, AccessDeniedException {
        check(request);
        @Nullable final String userId = request.getUserId();
        getTaskService().clear(userId);
        return new TaskClearResponse();
    }

    @Override
    @NotNull
    public TaskCreateResponse createTask(@NotNull final TaskCreateRequest request) throws AbstractFieldException {
        check(request);
        @Nullable final String userId = request.getUserId();
        @Nullable final String name = request.getName();
        @Nullable final String description = request.getDescription();
        @Nullable final Task task = getTaskService().create(userId,name,description);
        return new TaskCreateResponse(task);
    }

    @Override
    @NotNull
    public TaskGetByIdResponse getTaskById(@NotNull TaskGetByIdRequest request) throws AbstractException {
        check(request);
        @Nullable final String userId = request.getUserId();
        @Nullable final String id = request.getId();
        @Nullable final Task task = getTaskService().findById(userId,id);
        return new TaskGetByIdResponse(task);
    }

    @Override
    @NotNull
    public TaskGetByIndexResponse getTaskByIndex(@NotNull final TaskGetByIndexRequest request) throws AbstractException {
        check(request);
        @Nullable final Integer index = request.getIndex();
        @Nullable final String userId = request.getUserId();
        @Nullable final Task task = getTaskService().findByIndex(userId,index);
        return new TaskGetByIndexResponse(task);
    }

    @Override
    @NotNull
    public TaskListResponse listTask(@NotNull TaskListRequest request) throws AccessDeniedException, UserIdEmptyException {
        check(request);
        @Nullable final String userId = request.getUserId();
        @Nullable final Sort sort = request.getSort();
        @Nullable final List<Task> tasks = getTaskService().findAll(userId,sort);
        return new TaskListResponse(tasks);
    }

    @Override
    @NotNull
    public TaskRemoveByIdResponse removeTaskById(
            @NotNull final TaskRemoveByIdRequest request
    ) throws Exception {
        check(request);
        @Nullable final String userId = request.getUserId();
        @Nullable final String id = request.getId();
        getTaskService().removeById(userId,id);
        return new TaskRemoveByIdResponse();
    }

    @Override
    @NotNull
    public TaskRemoveByIndexResponse removeTaskById(
            @NotNull final TaskRemoveByIndexRequest request
    ) throws Exception {
        check(request);
        @Nullable final String userId = request.getUserId();
        @Nullable final Integer index = request.getIndex();
        @Nullable final Task Task = getTaskService().removeById(userId, String.valueOf(index));
        return new TaskRemoveByIndexResponse(Task);
    }

    @Override
    @NotNull
    public TaskStartByIdResponse startTaskById(
            @NotNull final TaskStartByIdRequest request
    ) throws Exception {
        check(request);
        @Nullable final String userId = request.getUserId();
        @Nullable final String id = request.getId();
        getTaskService().changeTaskStatusById(userId,id,Status.IN_PROGRESS);
        return new TaskStartByIdResponse();
    }

    @Override
    @NotNull
    public TaskStartByIndexResponse startTaskByIndex(
            @NotNull final TaskStartByIndexRequest request
    ) throws Exception {
        check(request);
        @Nullable final String userId = request.getUserId();
        @Nullable final Integer index = request.getIndex();
        getTaskService().changeTaskStatusByIndex(userId, index,Status.IN_PROGRESS);
        return new TaskStartByIndexResponse();
    }

    @Override
    @NotNull
    public TaskCompleteByIdResponse completeTaskById(
            @NotNull final TaskCompleteByIdRequest request
    ) throws Exception {
        check(request);
        @Nullable final String userId = request.getUserId();
        @Nullable final String id = request.getId();
        getTaskService().changeTaskStatusById(userId, id, Status.COMPLETED);
        return new TaskCompleteByIdResponse();
    }

    @Override
    @NotNull
    public TaskCompleteByIndexResponse completeTaskByIndex(
            @NotNull final TaskCompleteByIndexRequest request
    ) throws Exception {
        check(request);
        @Nullable final String userId = request.getUserId();
        @Nullable final Integer index = request.getIndex();
        getTaskService().changeTaskStatusByIndex(userId, index, Status.COMPLETED);
        return new TaskCompleteByIndexResponse();
    }

    @Override
    @NotNull
    public TaskBindToProjectResponse bindTaskToProject(
            @NotNull final TaskBindToProjectRequest request
    ) throws Exception {
        check(request);
        @Nullable final String userId = request.getUserId();
        @Nullable final String projectId = request.getProjectId();
        @Nullable final String taskId = request.getTaskId();
        getServiceLocator().getProjectTaskService().bindTaskToProject(userId,projectId,taskId);
        return new TaskBindToProjectResponse();
    }

    @Override
    public @NotNull TaskUnbindFromProjectResponse unbindTaskToProject(@NotNull TaskUnbindFromProjectRequest request) throws Exception {
        check(request);
        @Nullable final String userId = request.getUserId();
        @Nullable final String projectId = request.getProjectId();
        @Nullable final String taskId = request.getTaskId();
        getServiceLocator().getProjectTaskService().unbindTaskFromProject(userId,projectId,taskId);
        return new TaskUnbindFromProjectResponse();
    }

    @Override
    public @NotNull TaskUpdateByIdResponse updateTaskById(@NotNull TaskUpdateByIdRequest request) throws Exception {
        check(request);
        @Nullable final String userId = request.getUserId();
        @Nullable final String id = request.getId();
        @Nullable final String name = request.getName();
        @Nullable final String description = request.getDescription();
        getTaskService().updateById(userId, id, name, description);
        return new TaskUpdateByIdResponse();
    }

    @Override
    public @NotNull TaskUpdateByIndexResponse updateTaskByIndex(@NotNull TaskUpdateByIndexRequest request) throws Exception {
        check(request);
        @Nullable final String userId = request.getUserId();
        @Nullable final Integer index = request.getIndex();
        @Nullable final String name = request.getName();
        @Nullable final String description = request.getDescription();
        getTaskService().updateByIndex(userId, index, name, description);
        return new TaskUpdateByIndexResponse();
    }

    @Override
    public @NotNull TaskListByProjectIdResponse listTasksToProjectId(@NotNull TaskListByProjectIdRequest request) throws Exception {
        check(request);
        @Nullable final String userId = request.getUserId();
        @Nullable final String projectId = request.getProjectId();
        @Nullable final List<Task> tasks = getTaskService().findAllByProjectId(userId,projectId);
        return new TaskListByProjectIdResponse(tasks);
    }

}
