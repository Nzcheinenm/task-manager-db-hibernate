package ru.t1.dkononov.tm.api.endpoint;

import org.jetbrains.annotations.NotNull;
import ru.t1.dkononov.tm.api.services.IProjectService;
import ru.t1.dkononov.tm.dto.request.*;
import ru.t1.dkononov.tm.dto.response.*;
import ru.t1.dkononov.tm.exception.AbstractException;
import ru.t1.dkononov.tm.exception.field.AbstractFieldException;
import ru.t1.dkononov.tm.exception.field.AccessDeniedException;
import ru.t1.dkononov.tm.exception.field.UserIdEmptyException;

public interface IProjectEndpoint {
    @NotNull IProjectService getProjectService();

    @NotNull ProjectChangeStatusByIdResponse changeStatusById(
            @NotNull ProjectChangeStatusByIdRequest request
    ) throws AbstractException;

    @NotNull ProjectChangeStatusByIndexResponse changeStatusByIndex(
            @NotNull ProjectChangeStatusByIndexRequest request
    ) throws AbstractException;

    @NotNull ProjectClearResponse clearProject(@NotNull ProjectClearRequest request)
            throws UserIdEmptyException, AccessDeniedException;

    @NotNull ProjectCreateResponse createProject(@NotNull ProjectCreateRequest request) throws AbstractFieldException;

    @NotNull ProjectGetByIdResponse getProjectById(@NotNull ProjectGetByIdRequest request) throws AbstractException;

    @NotNull ProjectGetByIndexResponse getProjectByIndex(@NotNull ProjectGetByIndexRequest request) throws AbstractException;

    @NotNull ProjectListResponse listProject(@NotNull ProjectListRequest request) throws AccessDeniedException, UserIdEmptyException;

    @NotNull ProjectRemoveByIdResponse removeProjectById(
            @NotNull ProjectRemoveByIdRequest request
    ) throws Exception;

    @NotNull ProjectRemoveByIndexResponse removeProjectById(
            @NotNull ProjectRemoveByIndexRequest request
    ) throws Exception;

    @NotNull ProjectStartByIdResponse startProjectById(
            @NotNull ProjectStartByIdRequest request
    ) throws Exception;

    @NotNull ProjectStartByIndexResponse startProjectByIndex(
            @NotNull ProjectStartByIndexRequest request
    ) throws Exception;

    @NotNull ProjectCompleteByIdResponse completeProjectById(
            @NotNull ProjectCompleteByIdRequest request
    ) throws Exception;

    @NotNull ProjectCompleteByIndexResponse completeProjectByIndex(
            @NotNull ProjectCompleteByIndexRequest request
    ) throws Exception;
}
