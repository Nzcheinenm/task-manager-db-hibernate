package ru.t1.dkononov.tm.endpoint;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.t1.dkononov.tm.api.endpoint.IProjectEndpoint;
import ru.t1.dkononov.tm.api.services.IProjectService;
import ru.t1.dkononov.tm.api.services.IServiceLocator;
import ru.t1.dkononov.tm.dto.request.*;
import ru.t1.dkononov.tm.dto.response.*;
import ru.t1.dkononov.tm.enumerated.Sort;
import ru.t1.dkononov.tm.enumerated.Status;
import ru.t1.dkononov.tm.exception.AbstractException;
import ru.t1.dkononov.tm.exception.field.AbstractFieldException;
import ru.t1.dkononov.tm.exception.field.AccessDeniedException;
import ru.t1.dkononov.tm.exception.field.UserIdEmptyException;
import ru.t1.dkononov.tm.model.Project;

import java.util.List;

public final class ProjectEndpoint extends AbstractEndpoint implements IProjectEndpoint {

    public ProjectEndpoint(@NotNull IServiceLocator serviceLocator) {
        super(serviceLocator);
    }

    @Override
    public IProjectService getProjectService() {
        return getServiceLocator().getProjectService();
    }

    @Override
    @NotNull
    public ProjectChangeStatusByIdResponse changeStatusById(
            @NotNull final ProjectChangeStatusByIdRequest request
    ) throws AbstractException {
        check(request);
        @Nullable final String id = request.getId();
        @Nullable final String userId = request.getUserId();
        @Nullable final Status status = Status.valueOf(request.getStatusValue());
        @Nullable final Project project = getProjectService().changeProjectStatusById(userId, id, status);
        return new ProjectChangeStatusByIdResponse(project);
    }

    @Override
    @NotNull
    public ProjectChangeStatusByIndexResponse changeStatusByIndex(
            @NotNull final ProjectChangeStatusByIndexRequest request
    ) throws AbstractException {
        check(request);
        @Nullable final Integer index = request.getIndex();
        @Nullable final String userId = request.getUserId();
        @Nullable final Status status = Status.valueOf(request.getStatusValue());
        @Nullable final Project project = getProjectService().changeProjectStatusByIndex(userId, index, status);
        return new ProjectChangeStatusByIndexResponse(project);
    }

    @Override
    @NotNull
    public ProjectClearResponse clearProject(@NotNull final ProjectClearRequest request)
            throws UserIdEmptyException, AccessDeniedException {
        check(request);
        @Nullable final String userId = request.getUserId();
        getProjectService().clear(userId);
        return new ProjectClearResponse();
    }

    @Override
    @NotNull
    public ProjectCreateResponse createProject(@NotNull final ProjectCreateRequest request) throws AbstractFieldException {
        check(request);
        @Nullable final String userId = request.getUserId();
        @Nullable final String name = request.getName();
        @Nullable final String description = request.getDescription();
        @Nullable final Project project = getProjectService().create(userId, name, description);
        return new ProjectCreateResponse(project);
    }

    @Override
    @NotNull
    public ProjectGetByIdResponse getProjectById(@NotNull ProjectGetByIdRequest request) throws AbstractException {
        check(request);
        @Nullable final String userId = request.getUserId();
        @Nullable final String id = request.getId();
        @Nullable final Project project = getProjectService().findById(userId, id);
        return new ProjectGetByIdResponse(project);
    }

    @Override
    @NotNull
    public ProjectGetByIndexResponse getProjectByIndex(@NotNull final ProjectGetByIndexRequest request) throws AbstractException {
        check(request);
        @Nullable final Integer index = request.getIndex();
        @Nullable final String userId = request.getUserId();
        @Nullable final Project project = getProjectService().findByIndex(userId, index);
        return new ProjectGetByIndexResponse(project);
    }

    @Override
    @NotNull
    public ProjectListResponse listProject(@NotNull final ProjectListRequest request)
            throws AccessDeniedException, UserIdEmptyException {
        check(request);
        @Nullable final String userId = request.getUserId();
        @Nullable final Sort sort = request.getSort();
        @Nullable final List<Project> projects = getProjectService().findAll(userId, sort);
        return new ProjectListResponse(projects);
    }

    @Override
    @NotNull
    public ProjectRemoveByIdResponse removeProjectById(
            @NotNull final ProjectRemoveByIdRequest request
    ) throws Exception {
        check(request);
        @Nullable final String userId = request.getUserId();
        @Nullable final String id = request.getId();
        getProjectService().removeById(userId, id);
        return new ProjectRemoveByIdResponse();
    }

    @Override
    @NotNull
    public ProjectRemoveByIndexResponse removeProjectById(
            @NotNull final ProjectRemoveByIndexRequest request
    ) throws Exception {
        check(request);
        @Nullable final String userId = request.getUserId();
        @Nullable final Integer index = request.getIndex();
        getProjectService().removeById(userId, String.valueOf(index));
        return new ProjectRemoveByIndexResponse();
    }

    @Override
    @NotNull
    public ProjectStartByIdResponse startProjectById(
            @NotNull final ProjectStartByIdRequest request
    ) throws Exception {
        check(request);
        @Nullable final String userId = request.getUserId();
        @Nullable final String id = request.getId();
        @Nullable final Project project = getProjectService().changeProjectStatusById(userId, id, Status.IN_PROGRESS);
        return new ProjectStartByIdResponse(project);
    }

    @Override
    @NotNull
    public ProjectStartByIndexResponse startProjectByIndex(
            @NotNull final ProjectStartByIndexRequest request
    ) throws Exception {
        check(request);
        @Nullable final String userId = request.getUserId();
        @Nullable final Integer index = request.getIndex();
        @Nullable final Project project = getProjectService().changeProjectStatusByIndex(userId, index, Status.IN_PROGRESS);
        return new ProjectStartByIndexResponse(project);
    }

    @Override
    @NotNull
    public ProjectCompleteByIdResponse completeProjectById(
            @NotNull final ProjectCompleteByIdRequest request
    ) throws Exception {
        check(request);
        @Nullable final String userId = request.getUserId();
        @Nullable final String id = request.getId();
        @Nullable final Project project = getProjectService().changeProjectStatusById(userId, id, Status.COMPLETED);
        return new ProjectCompleteByIdResponse(project);
    }

    @Override
    @NotNull
    public ProjectCompleteByIndexResponse completeProjectByIndex(
            @NotNull final ProjectCompleteByIndexRequest request
    ) throws Exception {
        check(request);
        @Nullable final String userId = request.getUserId();
        @Nullable final Integer index = request.getIndex();
        @Nullable final Project project = getProjectService().changeProjectStatusByIndex(userId, index, Status.COMPLETED);
        return new ProjectCompleteByIndexResponse(project);
    }

    @Override
    public @NotNull ProjectUpdateByIdResponse updateProjectById(
            @NotNull final ProjectUpdateByIdRequest request
    ) throws Exception {
        check(request);
        @Nullable final String userId = request.getUserId();
        @Nullable final String id = request.getId();
        @Nullable final String name = request.getName();
        @Nullable final String description = request.getDescription();
        @Nullable final Project project = getProjectService().updateById(userId, id, name, description);
        return new ProjectUpdateByIdResponse(project);
    }

    @Override
    public @NotNull ProjectUpdateByIndexResponse updateProjectByIndex(
            @NotNull final ProjectUpdateByIndexRequest request
    ) throws Exception {
        check(request);
        @Nullable final String userId = request.getUserId();
        @Nullable final Integer index = request.getIndex();
        @Nullable final String name = request.getName();
        @Nullable final String description = request.getDescription();
        @Nullable final Project project = getProjectService().updateByIndex(userId, index, name, description);
        return new ProjectUpdateByIndexResponse(project);
    }

}
