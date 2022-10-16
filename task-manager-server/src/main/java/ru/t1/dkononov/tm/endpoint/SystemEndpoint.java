package ru.t1.dkononov.tm.endpoint;

import org.jetbrains.annotations.NotNull;
import ru.t1.dkononov.tm.api.endpoint.ISystemEndpoint;
import ru.t1.dkononov.tm.api.services.IPropertyService;
import ru.t1.dkononov.tm.api.services.IServiceLocator;
import ru.t1.dkononov.tm.dto.request.ApplicationAboutRequest;
import ru.t1.dkononov.tm.dto.request.ApplicationVersionRequest;
import ru.t1.dkononov.tm.dto.response.ApplicationAboutResponse;
import ru.t1.dkononov.tm.dto.response.ApplicationVersionResponse;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

@WebService(endpointInterface = "ru.t1.dkononov.tm.api.endpoint.ISystemEndpoint")
public final class SystemEndpoint implements ISystemEndpoint {

    @NotNull
    private final IServiceLocator serviceLocator;

    public SystemEndpoint(@NotNull final IServiceLocator serviceLocator) {
        this.serviceLocator = serviceLocator;
    }

    @NotNull
    @Override
    @WebMethod
    public ApplicationAboutResponse getAbout(
            @WebParam(name = REQUEST, partName = REQUEST)
            @NotNull final ApplicationAboutRequest request
    ) {
        @NotNull final IPropertyService propertyService = serviceLocator.getPropertyService();
        @NotNull final ApplicationAboutResponse response = new ApplicationAboutResponse();
        response.setEmail(propertyService.getAuthorEmail());
        response.setName(propertyService.getAuthorName());
        return response;
    }

    @NotNull
    @Override
    @WebMethod
    public ApplicationVersionResponse getVersion(
            @WebParam(name = REQUEST, partName = REQUEST)
            @NotNull final ApplicationVersionRequest request
    ) {
        @NotNull final IPropertyService propertyService = serviceLocator.getPropertyService();
        @NotNull final ApplicationVersionResponse response = new ApplicationVersionResponse();
        response.setVersion(propertyService.getApplicationVersion());
        return response;
    }

}
