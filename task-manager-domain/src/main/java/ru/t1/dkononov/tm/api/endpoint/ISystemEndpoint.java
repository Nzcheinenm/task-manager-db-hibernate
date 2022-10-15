package ru.t1.dkononov.tm.api.endpoint;

import lombok.SneakyThrows;
import org.jetbrains.annotations.NotNull;
import ru.t1.dkononov.tm.dto.request.ApplicationAboutRequest;
import ru.t1.dkononov.tm.dto.request.ApplicationVersionRequest;
import ru.t1.dkononov.tm.dto.response.ApplicationAboutResponse;
import ru.t1.dkononov.tm.dto.response.ApplicationVersionResponse;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

@WebService
public interface ISystemEndpoint extends IEndpoint {

    @NotNull
    String NAME = "SystemEndpoint";

    @NotNull
    String PART = NAME + "Service";

    @SneakyThrows
    @WebMethod(exclude = true)
    static ISystemEndpoint newInstance() {
        return newInstance(HOST,PORT);
    }

    @SneakyThrows
    @WebMethod(exclude = true)
    static ISystemEndpoint newInstance(@NotNull final IConnectionProvider connectionProvider) {
        return IEndpoint.newInstance(connectionProvider,NAME,SPACE,PART,ISystemEndpoint.class);
    }

    @SneakyThrows
    @WebMethod(exclude = true)
    static ISystemEndpoint newInstance(@NotNull final String host, @NotNull final String port) {
        return IEndpoint.newInstance(host,port,NAME,SPACE,PART,ISystemEndpoint.class);
    }

    @NotNull
    @WebMethod
    ApplicationAboutResponse getAbout(
            @WebParam(name = REQUEST, partName = REQUEST)
            @NotNull ApplicationAboutRequest request);

    @NotNull
    @WebMethod
    ApplicationVersionResponse getVersion(
            @WebParam(name = REQUEST, partName = REQUEST)
            @NotNull ApplicationVersionRequest request);
}
