package ru.t1.dkononov.tm.endpoint;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.t1.dkononov.tm.api.endpoint.IAuthEndpoint;
import ru.t1.dkononov.tm.api.services.IAuthService;
import ru.t1.dkononov.tm.api.services.IServiceLocator;
import ru.t1.dkononov.tm.api.services.dto.IUserDTOService;
import ru.t1.dkononov.tm.dto.model.SessionDTO;
import ru.t1.dkononov.tm.dto.model.UserDTO;
import ru.t1.dkononov.tm.dto.request.UserLoginRequest;
import ru.t1.dkononov.tm.dto.request.UserLogoutRequest;
import ru.t1.dkononov.tm.dto.request.UserProfileRequest;
import ru.t1.dkononov.tm.dto.response.UserLoginResponse;
import ru.t1.dkononov.tm.dto.response.UserLogoutResponse;
import ru.t1.dkononov.tm.dto.response.UserProfileResponse;
import ru.t1.dkononov.tm.exception.AbstractException;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

@WebService(endpointInterface = "ru.t1.dkononov.tm.api.endpoint.IAuthEndpoint")
public final class AuthEndpoint extends AbstractEndpoint implements IAuthEndpoint {

    public AuthEndpoint(@NotNull IServiceLocator serviceLocator) {
        super(serviceLocator);
    }

    public IUserDTOService getUserService() {
        return getServiceLocator().getUserService();
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
        @NotNull final SessionDTO session = check(request);
        getServiceLocator().getAuthService().invalidate(session);
        return new UserLogoutResponse();
    }

    @NotNull
    @Override
    @WebMethod
    public UserProfileResponse profile(
            @WebParam(name = REQUEST, partName = REQUEST)
            @NotNull final UserProfileRequest request
    ) throws AbstractException {
        @NotNull final SessionDTO session = check(request);
        @Nullable final String userId = session.getUserId();
        @Nullable final UserDTO user = getUserService().findById(userId);
        return new UserProfileResponse(user);
    }


}
