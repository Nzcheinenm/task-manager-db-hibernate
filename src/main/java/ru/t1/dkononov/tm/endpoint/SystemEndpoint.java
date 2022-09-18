package ru.t1.dkononov.tm.endpoint;

import org.jetbrains.annotations.NotNull;
import ru.t1.dkononov.tm.api.endpoint.ISystemEndpoint;
import ru.t1.dkononov.tm.api.services.IPropertyService;
import ru.t1.dkononov.tm.api.services.IServiceLocator;
import ru.t1.dkononov.tm.dto.request.ServerAboutRequest;
import ru.t1.dkononov.tm.dto.request.ServerVersionRequest;
import ru.t1.dkononov.tm.dto.response.ServerAboutResponse;
import ru.t1.dkononov.tm.dto.response.ServerVersionResponse;

public final class SystemEndpoint implements ISystemEndpoint {

    @NotNull
    private final IServiceLocator serviceLocator;

    public SystemEndpoint(@NotNull final IServiceLocator serviceLocator) {
        this.serviceLocator = serviceLocator;
    }

    @Override
    @NotNull
    public ServerAboutResponse getAbout(@NotNull final ServerAboutRequest request) {
        @NotNull final IPropertyService propertyService = serviceLocator.getPropertyService();
        @NotNull final ServerAboutResponse response = new ServerAboutResponse();
        response.setEmail(propertyService.getAuthorEmail());
        response.setName(propertyService.getAuthorName());
        return response;
    }

    @Override
    @NotNull
    public ServerVersionResponse getVersion(@NotNull final ServerVersionRequest request) {
        @NotNull final IPropertyService propertyService = serviceLocator.getPropertyService();
        @NotNull final ServerVersionResponse response = new ServerVersionResponse();
        response.setVersion(propertyService.getApplicationVersion());
        return response;
    }

}
