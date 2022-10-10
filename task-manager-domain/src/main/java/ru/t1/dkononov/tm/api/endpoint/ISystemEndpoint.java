package ru.t1.dkononov.tm.api.endpoint;

import org.jetbrains.annotations.NotNull;
import ru.t1.dkononov.tm.dto.request.ApplicationAboutRequest;
import ru.t1.dkononov.tm.dto.request.ApplicationVersionRequest;
import ru.t1.dkononov.tm.dto.response.ApplicationAboutResponse;
import ru.t1.dkononov.tm.dto.response.ApplicationVersionResponse;

import javax.jws.WebMethod;
import javax.jws.WebService;

@WebService
public interface ISystemEndpoint {
    @NotNull
    @WebMethod
    ApplicationAboutResponse getAbout(@NotNull ApplicationAboutRequest request);

    @NotNull
    @WebMethod
    ApplicationVersionResponse getVersion(@NotNull ApplicationVersionRequest request);
}
