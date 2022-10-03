package ru.t1.dkononov.tm.endpoint;

import org.jetbrains.annotations.NotNull;
import ru.t1.dkononov.tm.api.endpoint.ISystemEndpoint;
import ru.t1.dkononov.tm.api.services.IPropertyService;
import ru.t1.dkononov.tm.api.services.IServiceLocator;
import ru.t1.dkononov.tm.dto.request.ApplicationAboutRequest;
import ru.t1.dkononov.tm.dto.request.ApplicationVersionRequest;
import ru.t1.dkononov.tm.dto.response.ApplicationAboutResponse;
import ru.t1.dkononov.tm.dto.response.ApplicationVersionResponse;

public final class SystemEndpoint implements ISystemEndpoint {

    @NotNull
    private final IServiceLocator serviceLocator;

    public SystemEndpoint(@NotNull final IServiceLocator serviceLocator) {
        this.serviceLocator = serviceLocator;
    }

    @Override
    @NotNull
    public ApplicationAboutResponse getAbout(@NotNull final ApplicationAboutRequest request) {
        @NotNull final IPropertyService propertyService = serviceLocator.getPropertyService();
        @NotNull final ApplicationAboutResponse response = new ApplicationAboutResponse();
        response.setEmail(propertyService.getAuthorEmail());
        response.setName(propertyService.getAuthorName());
        return response;
    }

    @Override
    @NotNull
    public ApplicationVersionResponse getVersion(@NotNull final ApplicationVersionRequest request) {
        @NotNull final IPropertyService propertyService = serviceLocator.getPropertyService();
        @NotNull final ApplicationVersionResponse response = new ApplicationVersionResponse();
        response.setVersion(propertyService.getApplicationVersion());
        return response;
    }

}
