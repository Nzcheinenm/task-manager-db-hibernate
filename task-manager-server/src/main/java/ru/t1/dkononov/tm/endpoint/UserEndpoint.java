package ru.t1.dkononov.tm.endpoint;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.t1.dkononov.tm.api.endpoint.IUserEndpoint;
import ru.t1.dkononov.tm.api.services.IAuthService;
import ru.t1.dkononov.tm.api.services.IServiceLocator;
import ru.t1.dkononov.tm.api.services.IUserService;
import ru.t1.dkononov.tm.dto.request.*;
import ru.t1.dkononov.tm.dto.response.*;
import ru.t1.dkononov.tm.enumerated.Role;
import ru.t1.dkononov.tm.exception.AbstractException;
import ru.t1.dkononov.tm.exception.field.AbstractFieldException;
import ru.t1.dkononov.tm.model.User;

public final class UserEndpoint extends AbstractEndpoint implements IUserEndpoint {
    public UserEndpoint(@NotNull final IServiceLocator serviceLocator) {
        super(serviceLocator);
    }

    @NotNull
    private IUserService getUserService() {
        return this.getServiceLocator().getUserService();
    }

    @Override
    @NotNull
    public UserLockResponse lockUser(@NotNull final UserLockRequest request) throws Exception {
        check(request, Role.ADMIN);
        @Nullable final String login = request.getLogin();
        @Nullable final User user = getUserService().lockUserByLogin(login);
        return new UserLockResponse(user);
    }

    @Override
    @NotNull
    public UserUnlockResponse unlockUser(@NotNull final UserUnlockRequest request) throws Exception {
        check(request, Role.ADMIN);
        @Nullable final String login = request.getLogin();
        @Nullable final User user = getUserService().unlockUserByLogin(login);
        return new UserUnlockResponse(user);
    }

    @Override
    @NotNull
    public UserRemoveResponse removeUser(@NotNull final UserRemoveRequest request) throws Exception {
        check(request, Role.ADMIN);
        @Nullable final String login = request.getLogin();
        @Nullable final User user = getUserService().removeByLogin(login);
        return new UserRemoveResponse(user);
    }

    @Override
    @NotNull
    public UserUpdateProfileResponse updateUserProfile(@NotNull final UserUpdateProfileRequest request) throws AbstractFieldException {
        check(request);
        @Nullable final String userId = request.getUserId();
        @Nullable final String firstName = request.getFirstName();
        @Nullable final String middleName = request.getMiddleName();
        @Nullable final String lastName = request.getLastName();
        @Nullable final User user = getUserService().updateUser(
                userId, firstName, lastName, middleName
        );
        return new UserUpdateProfileResponse(user);
    }

    @Override
    @NotNull
    public UserChangePasswordResponse changeUserPassword(@NotNull final UserChangePasswordRequest request) throws AbstractFieldException {
        check(request);
        @Nullable final String userId = request.getUserId();
        @Nullable final String password = request.getPassword();
        @Nullable final User user = getUserService().setPassword(userId, password);
        return new UserChangePasswordResponse(user);
    }

    @Override
    @NotNull
    public UserRegistryResponse registryUser(@NotNull final UserRegistryRequest request) throws AbstractException {
        @Nullable final String login = request.getLogin();
        @Nullable final String password = request.getPassword();
        @Nullable final String email = request.getEmail();
        @NotNull final IAuthService authService = getServiceLocator().getAuthService();
        @Nullable final User user = authService.registry(login, password, email);
        return new UserRegistryResponse(user);
    }

}
