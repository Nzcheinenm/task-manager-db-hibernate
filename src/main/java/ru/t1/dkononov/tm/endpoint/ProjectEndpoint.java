package ru.t1.dkononov.tm.endpoint;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.t1.dkononov.tm.api.services.IProjectService;
import ru.t1.dkononov.tm.api.services.IServiceLocator;
import ru.t1.dkononov.tm.dto.request.ProjectChangeStatusByIdRequest;
import ru.t1.dkononov.tm.dto.response.ProjectChangeStatusByIdResponse;
import ru.t1.dkononov.tm.exception.field.AccessDeniedException;

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
    ) throws AccessDeniedException {
        check(request);
        @Nullable final String id = request.getId();
        return null;
    }

}
