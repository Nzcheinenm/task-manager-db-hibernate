package ru.t1.dkononov.tm.api.endpoint;

import lombok.SneakyThrows;
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
public interface IProjectEndpoint extends IEndpoint {

    @NotNull
    String NAME = "ProjectEndpoint";

    @NotNull
    String PART = NAME + "Service";

    @SneakyThrows
    @WebMethod(exclude = true)
    static IProjectEndpoint newInstance() {
        return newInstance(HOST, PORT);
    }

    @SneakyThrows
    @WebMethod(exclude = true)
    static IProjectEndpoint newInstance(@NotNull final IConnectionProvider connectionProvider) {
        return IEndpoint.newInstance(connectionProvider, NAME, SPACE, PART, IProjectEndpoint.class);
    }

    @SneakyThrows
    @WebMethod(exclude = true)
    static IProjectEndpoint newInstance(@NotNull final String host, @NotNull final String port) {
        return IEndpoint.newInstance(host, port, NAME, SPACE, PART, IProjectEndpoint.class);
    }

    @NotNull
    @WebMethod
    ProjectChangeStatusByIdResponse changeStatusById(
            @NotNull ProjectChangeStatusByIdRequest request
    ) throws AbstractException;

    @NotNull
    @WebMethod
    ProjectChangeStatusByIndexResponse changeStatusByIndex(
            @NotNull ProjectChangeStatusByIndexRequest request
    ) throws AbstractException;

    @NotNull
    @WebMethod
    ProjectClearResponse clearProject(@NotNull ProjectClearRequest request)
            throws UserIdEmptyException, AccessDeniedException;

    @NotNull
    @WebMethod
    ProjectCreateResponse createProject(@NotNull ProjectCreateRequest request) throws AbstractFieldException;

    @NotNull
    @WebMethod
    ProjectGetByIdResponse getProjectById(@NotNull ProjectGetByIdRequest request) throws AbstractException;

    @NotNull
    @WebMethod
    ProjectGetByIndexResponse getProjectByIndex(@NotNull ProjectGetByIndexRequest request) throws AbstractException;

    @NotNull
    @WebMethod
    ProjectListResponse listProject(@NotNull ProjectListRequest request) throws AccessDeniedException, UserIdEmptyException;

    @NotNull
    @WebMethod
    ProjectRemoveByIdResponse removeProjectById(
            @NotNull ProjectRemoveByIdRequest request
    ) throws Exception;

    @NotNull
    @WebMethod
    ProjectRemoveByIndexResponse removeProjectByIndex(
            @NotNull ProjectRemoveByIndexRequest request
    ) throws Exception;

    @NotNull
    @WebMethod
    ProjectStartByIdResponse startProjectById(
            @NotNull ProjectStartByIdRequest request
    ) throws Exception;

    @NotNull
    @WebMethod
    ProjectStartByIndexResponse startProjectByIndex(
            @NotNull ProjectStartByIndexRequest request
    ) throws Exception;

    @NotNull
    @WebMethod
    ProjectCompleteByIdResponse completeProjectById(
            @NotNull ProjectCompleteByIdRequest request
    ) throws Exception;

    @NotNull
    @WebMethod
    ProjectCompleteByIndexResponse completeProjectByIndex(
            @NotNull ProjectCompleteByIndexRequest request
    ) throws Exception;

    @NotNull
    @WebMethod
    ProjectUpdateByIdResponse updateProjectById(
            @NotNull ProjectUpdateByIdRequest request
    ) throws Exception;

    @NotNull
    @WebMethod
    ProjectUpdateByIndexResponse updateProjectByIndex(
            @NotNull ProjectUpdateByIndexRequest request
    ) throws Exception;

}
