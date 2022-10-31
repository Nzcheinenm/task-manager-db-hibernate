package ru.t1.dkononov.tm.endpoint;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.t1.dkononov.tm.api.endpoint.ITaskEndpoint;
import ru.t1.dkononov.tm.api.services.IServiceLocator;
import ru.t1.dkononov.tm.api.services.ITaskService;
import ru.t1.dkononov.tm.dto.request.*;
import ru.t1.dkononov.tm.dto.response.*;
import ru.t1.dkononov.tm.enumerated.Sort;
import ru.t1.dkononov.tm.enumerated.Status;
import ru.t1.dkononov.tm.exception.AbstractException;
import ru.t1.dkononov.tm.exception.field.AccessDeniedException;
import ru.t1.dkononov.tm.exception.field.UserIdEmptyException;
import ru.t1.dkononov.tm.model.Session;
import ru.t1.dkononov.tm.model.Task;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import java.util.List;

@WebService(endpointInterface = "ru.t1.dkononov.tm.api.endpoint.ITaskEndpoint")
public final class TaskEndpoint extends AbstractEndpoint implements ITaskEndpoint {

    public TaskEndpoint(@NotNull final IServiceLocator serviceLocator) {
        super(serviceLocator);
    }

    @NotNull
    public ITaskService getTaskService() {
        return getServiceLocator().getTaskService();
    }

    @NotNull
    @Override
    @WebMethod
    public TaskChangeStatusByIdResponse changeStatusById(
            @WebParam(name = REQUEST, partName = REQUEST)
            @NotNull final TaskChangeStatusByIdRequest request
    ) throws Exception {
        @NotNull final Session session = check(request);
        @Nullable final String id = request.getId();
        @Nullable final String userId = session.getUserId();
        @Nullable final Status status = Status.valueOf(request.getStatusValue());
        getTaskService().changeTaskStatusById(userId, id, status);
        return new TaskChangeStatusByIdResponse();
    }

    @NotNull
    @Override
    @WebMethod
    public TaskChangeStatusByIndexResponse changeStatusByIndex(
            @WebParam(name = REQUEST, partName = REQUEST)
            @NotNull final TaskChangeStatusByIndexRequest request
    ) throws Exception {
        @NotNull final Session session = check(request);
        @Nullable final Integer index = request.getIndex();
        @Nullable final String userId = session.getUserId();
        @Nullable final Status status = Status.valueOf(request.getStatusValue());
        getTaskService().changeTaskStatusByIndex(userId, index, status);
        return new TaskChangeStatusByIndexResponse();
    }

    @NotNull
    @Override
    @WebMethod
    public TaskClearResponse clearTask(
            @WebParam(name = REQUEST, partName = REQUEST)
            @NotNull final TaskClearRequest request
    )
            throws UserIdEmptyException, AccessDeniedException {
        @NotNull final Session session = check(request);
        @Nullable final String userId = session.getUserId();
        getTaskService().clear(userId);
        return new TaskClearResponse();
    }

    @NotNull
    @Override
    @WebMethod
    public TaskCreateResponse createTask(
            @WebParam(name = REQUEST, partName = REQUEST)
            @NotNull final TaskCreateRequest request
    ) throws Exception {
        @NotNull final Session session = check(request);
        @Nullable final String userId = session.getUserId();
        @Nullable final String name = request.getName();
        @Nullable final String description = request.getDescription();
        @Nullable final Task task = getTaskService().create(userId, name, description);
        return new TaskCreateResponse(task);
    }

    @NotNull
    @Override
    @WebMethod
    public TaskGetByIdResponse getTaskById(
            @WebParam(name = REQUEST, partName = REQUEST)
            @NotNull final TaskGetByIdRequest request
    ) throws AbstractException {
        @NotNull final Session session = check(request);
        @Nullable final String userId = session.getUserId();
        @Nullable final String id = request.getId();
        @Nullable final Task task = getTaskService().findById(userId, id);
        return new TaskGetByIdResponse(task);
    }

    @NotNull
    @Override
    @WebMethod
    public TaskGetByIndexResponse getTaskByIndex(
            @WebParam(name = REQUEST, partName = REQUEST)
            @NotNull final TaskGetByIndexRequest request
    ) throws AbstractException {
        @NotNull final Session session = check(request);
        @Nullable final Integer index = request.getIndex();
        @Nullable final String userId = session.getUserId();
        @Nullable final Task task = getTaskService().findByIndex(userId, index);
        return new TaskGetByIndexResponse(task);
    }

    @NotNull
    @Override
    @WebMethod
    public TaskListResponse listTask(
            @WebParam(name = REQUEST, partName = REQUEST)
            @NotNull final TaskListRequest request
    )
            throws AccessDeniedException, UserIdEmptyException {
        @NotNull final Session session = check(request);
        @Nullable final String userId = session.getUserId();
        @Nullable final Sort sort = request.getSort();
        @Nullable final List<Task> tasks = getTaskService().findAll(userId, sort);
        return new TaskListResponse(tasks);
    }

    @NotNull
    @WebMethod
    public TaskRemoveByIdResponse removeTaskById(
            @WebParam(name = REQUEST, partName = REQUEST)
            @NotNull final TaskRemoveByIdRequest request
    ) throws Exception {
        @NotNull final Session session = check(request);
        @Nullable final String userId = session.getUserId();
        @Nullable final String id = request.getId();
        getTaskService().removeById(userId, id);
        return new TaskRemoveByIdResponse();
    }

    @NotNull
    @Override
    @WebMethod
    public TaskRemoveByIndexResponse removeTaskByIndex(
            @WebParam(name = REQUEST, partName = REQUEST)
            @NotNull final TaskRemoveByIndexRequest request
    ) throws Exception {
        @NotNull final Session session = check(request);
        @Nullable final String userId = session.getUserId();
        @Nullable final Integer index = request.getIndex();
        @Nullable final Task Task = getTaskService().removeById(userId, String.valueOf(index));
        return new TaskRemoveByIndexResponse(Task);
    }

    @NotNull
    @Override
    @WebMethod
    public TaskStartByIdResponse startTaskById(
            @WebParam(name = REQUEST, partName = REQUEST)
            @NotNull final TaskStartByIdRequest request
    ) throws Exception {
        @NotNull final Session session = check(request);
        @Nullable final String userId = session.getUserId();
        @Nullable final String id = request.getId();
        @Nullable final Task Task = getTaskService().changeTaskStatusById(userId, id, Status.IN_PROGRESS);
        return new TaskStartByIdResponse(Task);
    }

    @NotNull
    @Override
    @WebMethod
    public TaskStartByIndexResponse startTaskByIndex(
            @WebParam(name = REQUEST, partName = REQUEST)
            @NotNull final TaskStartByIndexRequest request
    ) throws Exception {
        @NotNull final Session session = check(request);
        @Nullable final String userId = session.getUserId();
        @Nullable final Integer index = request.getIndex();
        @Nullable final Task Task = getTaskService().changeTaskStatusByIndex(userId, index, Status.IN_PROGRESS);
        return new TaskStartByIndexResponse(Task);
    }

    @NotNull
    @Override
    @WebMethod
    public TaskCompleteByIdResponse completeTaskById(
            @WebParam(name = REQUEST, partName = REQUEST)
            @NotNull final TaskCompleteByIdRequest request
    ) throws Exception {
        @NotNull final Session session = check(request);
        @Nullable final String userId = session.getUserId();
        @Nullable final String id = request.getId();
        @Nullable final Task task = getTaskService().changeTaskStatusById(userId, id, Status.COMPLETED);
        return new TaskCompleteByIdResponse(task);
    }

    @NotNull
    @Override
    @WebMethod
    public TaskCompleteByIndexResponse completeTaskByIndex(
            @WebParam(name = REQUEST, partName = REQUEST)
            @NotNull final TaskCompleteByIndexRequest request
    ) throws Exception {
        @NotNull final Session session = check(request);
        @Nullable final String userId = session.getUserId();
        @Nullable final Integer index = request.getIndex();
        @Nullable final Task task = getTaskService().changeTaskStatusByIndex(userId, index, Status.COMPLETED);
        return new TaskCompleteByIndexResponse(task);
    }

    @NotNull
    @Override
    @WebMethod
    public TaskBindToProjectResponse bindTaskToProject(
            @WebParam(name = REQUEST, partName = REQUEST)
            @NotNull final TaskBindToProjectRequest request
    ) throws Exception {
        @NotNull final Session session = check(request);
        @Nullable final String userId = session.getUserId();
        @Nullable final String projectId = request.getProjectId();
        @Nullable final String taskId = request.getTaskId();
        getServiceLocator().getProjectTaskService().bindTaskToProject(userId, projectId, taskId);
        return new TaskBindToProjectResponse();
    }

    @NotNull
    @Override
    @WebMethod
    public TaskUnbindFromProjectResponse unbindTaskToProject(
            @WebParam(name = REQUEST, partName = REQUEST)
            @NotNull final TaskUnbindFromProjectRequest request
    ) throws Exception {
        @NotNull final Session session = check(request);
        @Nullable final String userId = session.getUserId();
        @Nullable final String projectId = request.getProjectId();
        @Nullable final String taskId = request.getTaskId();
        getServiceLocator().getProjectTaskService().unbindTaskFromProject(userId, projectId, taskId);
        return new TaskUnbindFromProjectResponse();
    }

    @NotNull
    @Override
    @WebMethod
    public TaskUpdateByIdResponse updateTaskById(
            @WebParam(name = REQUEST, partName = REQUEST)
            @NotNull final TaskUpdateByIdRequest request
    ) throws Exception {
        @NotNull final Session session = check(request);
        @Nullable final String userId = session.getUserId();
        @Nullable final String id = request.getId();
        @Nullable final String name = request.getName();
        @Nullable final String description = request.getDescription();
        @Nullable final Task task = getTaskService().updateById(userId, id, name, description);
        return new TaskUpdateByIdResponse(task);
    }

    @NotNull
    @Override
    @WebMethod
    public TaskUpdateByIndexResponse updateTaskByIndex(
            @WebParam(name = REQUEST, partName = REQUEST)
            @NotNull final TaskUpdateByIndexRequest request
    ) throws Exception {
        @NotNull final Session session = check(request);
        @Nullable final String userId = session.getUserId();
        @Nullable final Integer index = request.getIndex();
        @Nullable final String name = request.getName();
        @Nullable final String description = request.getDescription();
        getTaskService().updateByIndex(userId, index, name, description);
        return new TaskUpdateByIndexResponse();
    }

    @NotNull
    @Override
    @WebMethod
    public TaskListByProjectIdResponse listTasksToProjectId(
            @WebParam(name = REQUEST, partName = REQUEST)
            @NotNull final TaskListByProjectIdRequest request
    ) throws Exception {
        @NotNull final Session session = check(request);
        @Nullable final String userId = session.getUserId();
        @Nullable final String projectId = request.getProjectId();
        @Nullable final List<Task> tasks = getTaskService().findAllByProjectId(userId, projectId);
        return new TaskListByProjectIdResponse(tasks);
    }

}
