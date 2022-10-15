package ru.t1.dkononov.tm.endpoint;

import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.t1.dkononov.tm.api.endpoint.IAuthEndpoint;
import ru.t1.dkononov.tm.api.services.IAuthService;
import ru.t1.dkononov.tm.api.services.IServiceLocator;
import ru.t1.dkononov.tm.dto.request.UserLoginRequest;
import ru.t1.dkononov.tm.dto.request.UserLogoutRequest;
import ru.t1.dkononov.tm.dto.response.UserLoginResponse;
import ru.t1.dkononov.tm.dto.response.UserLogoutResponse;
import ru.t1.dkononov.tm.exception.field.AccessDeniedException;
import ru.t1.dkononov.tm.model.Session;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

import static ru.t1.dkononov.tm.api.endpoint.IEndpoint.REQUEST;

@WebService(endpointInterface = "ru.t1.dkononov.tm.api.endpoint.IAuthEndpoint")
public final class AuthEndpoint extends AbstractEndpoint implements IAuthEndpoint {

    public AuthEndpoint(@NotNull IServiceLocator serviceLocator) {
        super(serviceLocator);
    }

    @NotNull
    @Override
    @WebMethod
    public UserLoginResponse login(
            @WebParam(name = REQUEST, partName = REQUEST)
            @NotNull final UserLoginRequest request
    ) throws Exception {
        @NotNull final IAuthService authService = getServiceLocator().getAuthService();
        @NotNull final String token = authService.login(request.getLogin(), request.getPassword());
        return new UserLoginResponse(token);
    }

    @NotNull
    @Override
    @WebMethod
    public UserLogoutResponse logout(
            @WebParam(name = REQUEST, partName = REQUEST)
            @NotNull final UserLogoutRequest request
    ) throws Exception {
        final Session session = check(request);
        getServiceLocator().getAuthService().invalidate(session);
        return new UserLogoutResponse();
    }


}
