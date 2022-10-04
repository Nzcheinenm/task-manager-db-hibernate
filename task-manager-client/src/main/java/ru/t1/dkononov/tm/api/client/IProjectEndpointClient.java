package ru.t1.dkononov.tm.api.client;

import lombok.SneakyThrows;
import org.jetbrains.annotations.NotNull;
import ru.t1.dkononov.tm.dto.request.*;
import ru.t1.dkononov.tm.dto.response.*;
import ru.t1.dkononov.tm.exception.AbstractException;
import ru.t1.dkononov.tm.exception.field.AbstractFieldException;
import ru.t1.dkononov.tm.exception.field.AccessDeniedException;
import ru.t1.dkononov.tm.exception.field.UserIdEmptyException;

public interface IProjectEndpointClient {
    @NotNull
    @SneakyThrows
    ProjectChangeStatusByIdResponse changeStatusById(
            @NotNull ProjectChangeStatusByIdRequest request
    ) throws AbstractException;

    @NotNull
    @SneakyThrows
    ProjectChangeStatusByIndexResponse changeStatusByIndex(
            @NotNull ProjectChangeStatusByIndexRequest request
    ) throws AbstractException;

    @NotNull
    @SneakyThrows
    ProjectClearResponse clearProject(@NotNull ProjectClearRequest request)
            throws UserIdEmptyException, AccessDeniedException;

    @NotNull
    @SneakyThrows
    ProjectCreateResponse createProject(@NotNull ProjectCreateRequest request) throws AbstractFieldException;

    @NotNull
    @SneakyThrows
    ProjectGetByIdResponse getProjectById(@NotNull ProjectGetByIdRequest request) throws AbstractException;

    @NotNull
    @SneakyThrows
    ProjectGetByIndexResponse getProjectByIndex(@NotNull ProjectGetByIndexRequest request) throws AbstractException;

    @NotNull
    @SneakyThrows
    ProjectListResponse listProject(@NotNull ProjectListRequest request) throws AccessDeniedException, UserIdEmptyException;

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

    @NotNull ProjectUpdateByIdResponse updateProjectById(
            @NotNull ProjectUpdateByIdRequest request
    ) throws Exception;

    @NotNull ProjectUpdateByIndexResponse updateProjectByIndex(
            @NotNull ProjectUpdateByIndexRequest request
    ) throws Exception;

}
