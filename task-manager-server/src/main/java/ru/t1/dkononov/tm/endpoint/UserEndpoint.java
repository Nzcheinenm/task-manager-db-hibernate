package ru.t1.dkononov.tm.endpoint;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.t1.dkononov.tm.api.endpoint.IUserEndpoint;
import ru.t1.dkononov.tm.api.services.IAuthService;
import ru.t1.dkononov.tm.api.services.IServiceLocator;
import ru.t1.dkononov.tm.api.services.dto.IUserDTOService;
import ru.t1.dkononov.tm.dto.model.SessionDTO;
import ru.t1.dkononov.tm.dto.model.UserDTO;
import ru.t1.dkononov.tm.dto.request.*;
import ru.t1.dkononov.tm.dto.response.*;
import ru.t1.dkononov.tm.enumerated.Role;
import ru.t1.dkononov.tm.exception.AbstractException;
import ru.t1.dkononov.tm.exception.field.AbstractFieldException;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

@WebService(endpointInterface = "ru.t1.dkononov.tm.api.endpoint.IUserEndpoint")
public final class UserEndpoint extends AbstractEndpoint implements IUserEndpoint {
    public UserEndpoint(@NotNull final IServiceLocator serviceLocator) {
        super(serviceLocator);
    }

    @NotNull
    private IUserDTOService getUserService() {
        return this.getServiceLocator().getUserService();
    }

    @NotNull
    @Override
    @WebMethod
    public UserLockResponse lockUser(
            @WebParam(name = REQUEST, partName = REQUEST)
            @NotNull final UserLockRequest request
    ) throws Exception {
        check(request, Role.ADMIN);
        @Nullable final String login = request.getLogin();
        @Nullable final UserDTO user = getUserService().lockUserByLogin(login);
        return new UserLockResponse(user);
    }

    @NotNull
    @Override
    @WebMethod
    public UserUnlockResponse unlockUser(
            @WebParam(name = REQUEST, partName = REQUEST)
            @NotNull final UserUnlockRequest request
    ) throws Exception {
        check(request, Role.ADMIN);
        @Nullable final String login = request.getLogin();
        @Nullable final UserDTO user = getUserService().unlockUserByLogin(login);
        return new UserUnlockResponse(user);
    }

    @NotNull
    @Override
    @WebMethod
    public UserRemoveResponse removeUser(
            @WebParam(name = REQUEST, partName = REQUEST)
            @NotNull final UserRemoveRequest request
    ) throws Exception {
        check(request, Role.ADMIN);
        @Nullable final String login = request.getLogin();
        @Nullable final UserDTO user = getUserService().removeByLogin(login);
        return new UserRemoveResponse(user);
    }

    @NotNull
    @Override
    @WebMethod
    public UserUpdateProfileResponse updateUserProfile(
            @WebParam(name = REQUEST, partName = REQUEST)
            @NotNull final UserUpdateProfileRequest request
    ) throws AbstractFieldException {
        @NotNull final SessionDTO session = check(request);
        @Nullable final String userId = session.getUserId();
        @Nullable final String firstName = request.getFirstName();
        @Nullable final String middleName = request.getMiddleName();
        @Nullable final String lastName = request.getLastName();
        @Nullable final UserDTO user = getUserService().updateUser(
                userId, firstName, lastName, middleName
        );
        return new UserUpdateProfileResponse(user);
    }

    @NotNull
    @Override
    @WebMethod
    public UserChangePasswordResponse changeUserPassword(
            @WebParam(name = REQUEST, partName = REQUEST)
            @NotNull final UserChangePasswordRequest request
    ) throws AbstractFieldException {
        @NotNull final SessionDTO session = check(request);
        @Nullable final String userId = session.getUserId();
        @Nullable final String password = request.getPassword();
        @Nullable final UserDTO user = getUserService().setPassword(userId, password);
        return new UserChangePasswordResponse(user);
    }

    @NotNull
    @Override
    @WebMethod
    public UserRegistryResponse registryUser(
            @WebParam(name = REQUEST, partName = REQUEST)
            @NotNull final UserRegistryRequest request
    ) throws AbstractException {
        @Nullable final String login = request.getLogin();
        @Nullable final String password = request.getPassword();
        @Nullable final String email = request.getEmail();
        @NotNull final IAuthService authService = getServiceLocator().getAuthService();
        @Nullable final UserDTO user = authService.registry(login, password, email);
        return new UserRegistryResponse(user);
    }

}
