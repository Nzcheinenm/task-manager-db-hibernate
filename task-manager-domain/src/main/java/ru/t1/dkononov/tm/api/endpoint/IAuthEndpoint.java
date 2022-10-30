package ru.t1.dkononov.tm.api.endpoint;

import lombok.SneakyThrows;
import org.jetbrains.annotations.NotNull;
import ru.t1.dkononov.tm.dto.request.UserLoginRequest;
import ru.t1.dkononov.tm.dto.request.UserLogoutRequest;
import ru.t1.dkononov.tm.dto.request.UserProfileRequest;
import ru.t1.dkononov.tm.dto.response.UserLoginResponse;
import ru.t1.dkononov.tm.dto.response.UserLogoutResponse;
import ru.t1.dkononov.tm.dto.response.UserProfileResponse;
import ru.t1.dkononov.tm.exception.AbstractException;
import ru.t1.dkononov.tm.exception.field.AccessDeniedException;
import ru.t1.dkononov.tm.exception.field.IdEmptyException;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

@WebService
public interface IAuthEndpoint extends IEndpoint {

    @NotNull
    String NAME = "AuthEndpoint";

    @NotNull
    String PART = NAME + "Service";

    @SneakyThrows
    @WebMethod(exclude = true)
    static IAuthEndpoint newInstance() {
        return newInstance(HOST, PORT);
    }

    @SneakyThrows
    @WebMethod(exclude = true)
    static IAuthEndpoint newInstance(@NotNull final IConnectionProvider connectionProvider) {
        return IEndpoint.newInstance(connectionProvider, NAME, SPACE, PART, IAuthEndpoint.class);
    }

    @SneakyThrows
    @WebMethod(exclude = true)
    static IAuthEndpoint newInstance(@NotNull final String host, @NotNull final String port) {
        return IEndpoint.newInstance(host, port, NAME, SPACE, PART, IAuthEndpoint.class);
    }

    @NotNull
    @WebMethod
    UserLoginResponse login(
            @WebParam(name = REQUEST, partName = REQUEST)
            @NotNull UserLoginRequest request
    ) throws Exception;

    @NotNull
    @WebMethod
    UserLogoutResponse logout(
            @WebParam(name = REQUEST, partName = REQUEST)
            @NotNull UserLogoutRequest request
    ) throws Exception;

    @NotNull
    @WebMethod
    UserProfileResponse profile(
            @WebParam(name = REQUEST, partName = REQUEST)
            @NotNull UserProfileRequest request
    ) throws AbstractException;
}
