package ru.t1.dkononov.tm.client;

import lombok.NoArgsConstructor;
import lombok.SneakyThrows;
import org.jetbrains.annotations.NotNull;
import ru.t1.dkononov.tm.api.client.IProjectEndpointClient;
import ru.t1.dkononov.tm.dto.request.*;
import ru.t1.dkononov.tm.dto.response.*;
import ru.t1.dkononov.tm.exception.AbstractException;
import ru.t1.dkononov.tm.exception.field.AbstractFieldException;
import ru.t1.dkononov.tm.exception.field.AccessDeniedException;
import ru.t1.dkononov.tm.exception.field.UserIdEmptyException;


@NoArgsConstructor
public final class ProjectEndpointClient extends AbstractEndpointClient implements IProjectEndpointClient {


    public ProjectEndpointClient(@NotNull AbstractEndpointClient client) {
        super(client);
    }

    @Override
    @NotNull
    @SneakyThrows
    public ProjectChangeStatusByIdResponse changeStatusById(
            @NotNull final ProjectChangeStatusByIdRequest request
    ) throws AbstractException {
        return call(request, ProjectChangeStatusByIdResponse.class);
    }

    @Override
    @NotNull
    @SneakyThrows
    public ProjectChangeStatusByIndexResponse changeStatusByIndex(
            @NotNull final ProjectChangeStatusByIndexRequest request
    ) throws AbstractException {
        return call(request, ProjectChangeStatusByIndexResponse.class);
    }

    @Override
    @NotNull
    @SneakyThrows
    public ProjectClearResponse clearProject(@NotNull final ProjectClearRequest request)
            throws UserIdEmptyException, AccessDeniedException {
        return call(request, ProjectClearResponse.class);
    }

    @Override
    @NotNull
    @SneakyThrows
    public ProjectCreateResponse createProject(@NotNull final ProjectCreateRequest request) throws AbstractFieldException {
        return call(request, ProjectCreateResponse.class);
    }

    @Override
    @NotNull
    @SneakyThrows
    public ProjectGetByIdResponse getProjectById(@NotNull ProjectGetByIdRequest request) throws AbstractException {
        return call(request, ProjectGetByIdResponse.class);
    }

    @Override
    @NotNull
    @SneakyThrows
    public ProjectGetByIndexResponse getProjectByIndex(@NotNull final ProjectGetByIndexRequest request) throws AbstractException {
        return call(request, ProjectGetByIndexResponse.class);
    }

    @Override
    @NotNull
    @SneakyThrows
    public ProjectListResponse listProject(@NotNull ProjectListRequest request) throws AccessDeniedException, UserIdEmptyException {
        return call(request, ProjectListResponse.class);
    }

    @Override
    @NotNull
    public ProjectRemoveByIdResponse removeProjectById(
            @NotNull final ProjectRemoveByIdRequest request
    ) throws Exception {
        return call(request, ProjectRemoveByIdResponse.class);
    }

    @Override
    @NotNull
    public ProjectRemoveByIndexResponse removeProjectById(
            @NotNull final ProjectRemoveByIndexRequest request
    ) throws Exception {
        return call(request, ProjectRemoveByIndexResponse.class);
    }

    @Override
    @NotNull
    public ProjectStartByIdResponse startProjectById(
            @NotNull final ProjectStartByIdRequest request
    ) throws Exception {
        return call(request, ProjectStartByIdResponse.class);
    }

    @Override
    @NotNull
    public ProjectStartByIndexResponse startProjectByIndex(
            @NotNull final ProjectStartByIndexRequest request
    ) throws Exception {
        return call(request, ProjectStartByIndexResponse.class);
    }

    @Override
    @NotNull
    public ProjectCompleteByIdResponse completeProjectById(
            @NotNull final ProjectCompleteByIdRequest request
    ) throws Exception {
        return call(request, ProjectCompleteByIdResponse.class);
    }

    @Override
    @NotNull
    public ProjectCompleteByIndexResponse completeProjectByIndex(
            @NotNull final ProjectCompleteByIndexRequest request
    ) throws Exception {
        return call(request, ProjectCompleteByIndexResponse.class);
    }

    @Override
    @NotNull
    public ProjectUpdateByIdResponse updateProjectById(@NotNull final ProjectUpdateByIdRequest request) throws Exception {
        return call(request, ProjectUpdateByIdResponse.class);
    }

    @Override
    @NotNull
    public ProjectUpdateByIndexResponse updateProjectByIndex(@NotNull final ProjectUpdateByIndexRequest request) throws Exception {
        return call(request, ProjectUpdateByIndexResponse.class);
    }

    public static void main(String[] args)
            throws Exception {
        @NotNull final ProjectEndpointClient client = new ProjectEndpointClient();
        client.connect();

        @NotNull final ProjectListResponse response = client.listProject(new ProjectListRequest());
        System.out.println(response.getProjects());

        client.disconnect();
    }

}
