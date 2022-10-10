package ru.t1.dkononov.tm.endpoint;

import lombok.NoArgsConstructor;
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

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import java.util.List;

import static ru.t1.dkononov.tm.api.endpoint.IEndpoint.REQUEST;

@WebService(endpointInterface = "ru.t1.dkononov.tm.api.endpoint.IProjectEndpoint")
public final class ProjectEndpoint extends AbstractEndpoint implements IProjectEndpoint {

    @NotNull
    private IServiceLocator serviceLocator;

    public ProjectEndpoint(@NotNull IServiceLocator serviceLocator) {
        super(serviceLocator);
    }

    public IProjectService getProjectService() {
        return getServiceLocator().getProjectService();
    }

    @NotNull
    @Override
    @WebMethod
    public ProjectChangeStatusByIdResponse changeStatusById (
            @WebParam(name = REQUEST, partName = REQUEST)
            @NotNull final ProjectChangeStatusByIdRequest request
    ) throws AbstractException {
        check(request);
        @Nullable final String id = request.getId();
        @Nullable final String userId = request.getUserId();
        @Nullable final Status status = Status.valueOf(request.getStatusValue());
        @Nullable final Project project = getProjectService().changeProjectStatusById(userId, id, status);
        return new ProjectChangeStatusByIdResponse(project);
    }

    @NotNull
    @Override
    @WebMethod
    public ProjectChangeStatusByIndexResponse changeStatusByIndex(
            @WebParam(name = REQUEST, partName = REQUEST)
            @NotNull final ProjectChangeStatusByIndexRequest request
    ) throws AbstractException {
        check(request);
        @Nullable final Integer index = request.getIndex();
        @Nullable final String userId = request.getUserId();
        @Nullable final Status status = Status.valueOf(request.getStatusValue());
        @Nullable final Project project = getProjectService().changeProjectStatusByIndex(userId, index, status);
        return new ProjectChangeStatusByIndexResponse(project);
    }

    @NotNull
    @Override
    @WebMethod
    public ProjectClearResponse clearProject(
            @WebParam(name = REQUEST, partName = REQUEST)
            @NotNull final ProjectClearRequest request
    ) throws UserIdEmptyException, AccessDeniedException {
        check(request);
        @Nullable final String userId = request.getUserId();
        getProjectService().clear(userId);
        return new ProjectClearResponse();
    }

    @NotNull
    @Override
    @WebMethod
    public ProjectCreateResponse createProject(
            @WebParam(name = REQUEST, partName = REQUEST)
            @NotNull final ProjectCreateRequest request
    ) throws AbstractFieldException {
        check(request);
        @Nullable final String userId = request.getUserId();
        @Nullable final String name = request.getName();
        @Nullable final String description = request.getDescription();
        @Nullable final Project project = getProjectService().create(userId, name, description);
        return new ProjectCreateResponse(project);
    }

    @NotNull
    @Override
    @WebMethod
    public ProjectGetByIdResponse getProjectById(
            @WebParam(name = REQUEST, partName = REQUEST)
            @NotNull ProjectGetByIdRequest request
    ) throws AbstractException {
        check(request);
        @Nullable final String userId = request.getUserId();
        @Nullable final String id = request.getId();
        @Nullable final Project project = getProjectService().findById(userId, id);
        return new ProjectGetByIdResponse(project);
    }

    @NotNull
    @Override
    @WebMethod
    public ProjectGetByIndexResponse getProjectByIndex(
            @WebParam(name = REQUEST, partName = REQUEST)
            @NotNull final ProjectGetByIndexRequest request
    ) throws AbstractException {
        check(request);
        @Nullable final Integer index = request.getIndex();
        @Nullable final String userId = request.getUserId();
        @Nullable final Project project = getProjectService().findByIndex(userId, index);
        return new ProjectGetByIndexResponse(project);
    }

    @NotNull
    @Override
    @WebMethod
    public ProjectListResponse listProject(
            @WebParam(name = REQUEST, partName = REQUEST)
            @NotNull final ProjectListRequest request
    ) throws AccessDeniedException, UserIdEmptyException {
        check(request);
        @Nullable final String userId = request.getUserId();
        @Nullable final Sort sort = request.getSort();
        @Nullable final List<Project> projects = getProjectService().findAll(userId, sort);
        return new ProjectListResponse(projects);
    }

    @NotNull
    @Override
    @WebMethod
    public ProjectRemoveByIdResponse removeProjectById(
            @WebParam(name = REQUEST, partName = REQUEST)
            @NotNull final ProjectRemoveByIdRequest request
    ) throws Exception {
        check(request);
        @Nullable final String userId = request.getUserId();
        @Nullable final String id = request.getId();
        getProjectService().removeById(userId, id);
        return new ProjectRemoveByIdResponse();
    }

    @NotNull
    @Override
    @WebMethod
    public ProjectRemoveByIndexResponse removeProjectByIndex(
            @WebParam(name = REQUEST, partName = REQUEST)
            @NotNull final ProjectRemoveByIndexRequest request
    ) throws Exception {
        check(request);
        @Nullable final String userId = request.getUserId();
        @Nullable final Integer index = request.getIndex();
        getProjectService().removeById(userId, String.valueOf(index));
        return new ProjectRemoveByIndexResponse();
    }

    @NotNull
    @Override
    @WebMethod
    public ProjectStartByIdResponse startProjectById(
            @WebParam(name = REQUEST, partName = REQUEST)
            @NotNull final ProjectStartByIdRequest request
    ) throws Exception {
        check(request);
        @Nullable final String userId = request.getUserId();
        @Nullable final String id = request.getId();
        @Nullable final Project project = getProjectService().changeProjectStatusById(userId, id, Status.IN_PROGRESS);
        return new ProjectStartByIdResponse(project);
    }

    @NotNull
    @Override
    @WebMethod
    public ProjectStartByIndexResponse startProjectByIndex(
            @WebParam(name = REQUEST, partName = REQUEST)
            @NotNull final ProjectStartByIndexRequest request
    ) throws Exception {
        check(request);
        @Nullable final String userId = request.getUserId();
        @Nullable final Integer index = request.getIndex();
        @Nullable final Project project = getProjectService().changeProjectStatusByIndex(userId, index, Status.IN_PROGRESS);
        return new ProjectStartByIndexResponse(project);
    }

    @NotNull
    @Override
    @WebMethod
    public ProjectCompleteByIdResponse completeProjectById(
            @WebParam(name = REQUEST, partName = REQUEST)
            @NotNull final ProjectCompleteByIdRequest request
    ) throws Exception {
        check(request);
        @Nullable final String userId = request.getUserId();
        @Nullable final String id = request.getId();
        @Nullable final Project project = getProjectService().changeProjectStatusById(userId, id, Status.COMPLETED);
        return new ProjectCompleteByIdResponse(project);
    }

    @NotNull
    @Override
    @WebMethod
    public ProjectCompleteByIndexResponse completeProjectByIndex(
            @WebParam(name = REQUEST, partName = REQUEST)
            @NotNull final ProjectCompleteByIndexRequest request
    ) throws Exception {
        check(request);
        @Nullable final String userId = request.getUserId();
        @Nullable final Integer index = request.getIndex();
        @Nullable final Project project = getProjectService().changeProjectStatusByIndex(userId, index, Status.COMPLETED);
        return new ProjectCompleteByIndexResponse(project);
    }

    @Override
    @NotNull
    @WebMethod
    public ProjectUpdateByIdResponse updateProjectById(
            @WebParam(name = REQUEST, partName = REQUEST)
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
    @NotNull
    @WebMethod
    public ProjectUpdateByIndexResponse updateProjectByIndex(
            @WebParam(name = REQUEST, partName = REQUEST)
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
