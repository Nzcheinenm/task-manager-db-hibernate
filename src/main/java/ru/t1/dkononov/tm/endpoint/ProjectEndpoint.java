package ru.t1.dkononov.tm.endpoint;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
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

public final class ProjectEndpoint extends AbstractEndpoint {

    public ProjectEndpoint(@NotNull IServiceLocator serviceLocator) {
        super(serviceLocator);
    }

    @NotNull IProjectService getProjectService() {
        return getServiceLocator().getProjectService();
    }

    @NotNull
    public ProjectChangeStatusByIdResponse changeStatusById(
            @NotNull final ProjectChangeStatusByIdRequest request
    ) throws AbstractException {
        check(request);
        @Nullable final String id = request.getId();
        @Nullable final String userId = request.getUserId();
        @Nullable final Status status = Status.valueOf(request.getStatusValue());
        @Nullable final Project project = getProjectService().changeProjectStatusById(userId,id,status);
        return new ProjectChangeStatusByIdResponse(project);
    }

    @NotNull
    public ProjectChangeStatusByIndexResponse changeStatusByIndex(
            @NotNull final ProjectChangeStatusByIndexRequest request
    ) throws AbstractException {
        check(request);
        @Nullable final Integer index = request.getIndex();
        @Nullable final String userId = request.getUserId();
        @Nullable final Status status = Status.valueOf(request.getStatusValue());
        @Nullable final Project project = getProjectService().changeProjectStatusByIndex(userId,index,status);
        return new ProjectChangeStatusByIndexResponse(project);
    }

    @NotNull
    public ProjectClearResponse clearProject(@NotNull final ProjectClearRequest request)
            throws UserIdEmptyException, AccessDeniedException {
        check(request);
        @Nullable final String userId = request.getUserId();
        getProjectService().clear(userId);
        return new ProjectClearResponse();
    }

    @NotNull
    public ProjectCreateResponse createProject(@NotNull final ProjectCreateRequest request) throws AbstractFieldException {
        check(request);
        @Nullable final String userId = request.getUserId();
        @Nullable final String name = request.getName();
        @Nullable final String description = request.getDescription();
        @Nullable final Project project = getProjectService().create(userId,name,description);
        return new ProjectCreateResponse(project);
    }

    @NotNull
    public ProjectGetByIdResponse getProjectById(@NotNull ProjectGetByIdRequest request) throws AbstractException {
        check(request);
        @Nullable final String userId = request.getUserId();
        @Nullable final String id = request.getId();
        @Nullable final Project project = getProjectService().findById(userId,id);
        return new ProjectGetByIdResponse(project);
    }

    @NotNull
    public ProjectGetByIndexResponse getProjectByIndex(@NotNull final ProjectGetByIndexRequest request) throws AbstractException {
        check(request);
        @Nullable final Integer index = request.getIndex();
        @Nullable final String userId = request.getUserId();
        @Nullable final Project project = getProjectService().findByIndex(userId,index);
        return new ProjectGetByIndexResponse(project);
    }

    @NotNull
    public ProjectListResponse listProject(@NotNull ProjectListRequest request) throws AccessDeniedException, UserIdEmptyException {
        check(request);
        @Nullable final String userId = request.getUserId();
        @Nullable final Sort sort = request.getSort();
        @Nullable final List<Project> projects = getProjectService().findAll(userId,sort);
        return new ProjectListResponse(projects);
    }

}
