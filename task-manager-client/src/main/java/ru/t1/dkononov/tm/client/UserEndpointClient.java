package ru.t1.dkononov.tm.client;

import lombok.NoArgsConstructor;
import lombok.SneakyThrows;
import org.jetbrains.annotations.NotNull;
import ru.t1.dkononov.tm.api.client.IUserEndpointClient;
import ru.t1.dkononov.tm.dto.request.*;
import ru.t1.dkononov.tm.dto.response.*;
import ru.t1.dkononov.tm.exception.AbstractException;
import ru.t1.dkononov.tm.exception.field.AbstractFieldException;


@NoArgsConstructor
public final class UserEndpointClient extends AbstractEndpointClient implements IUserEndpointClient {

    public UserEndpointClient(@NotNull AbstractEndpointClient client) {
        super(client);
    }


    @Override
    @NotNull
    public UserLockResponse lockUser(@NotNull final UserLockRequest request) throws Exception {
        return call(request, UserLockResponse.class);
    }

    @Override
    @NotNull
    public UserUnlockResponse unlockUser(@NotNull final UserUnlockRequest request) throws Exception {
        return call(request, UserUnlockResponse.class);
    }

    @Override
    @NotNull
    public UserRemoveResponse removeUser(@NotNull final UserRemoveRequest request) throws Exception {
        return call(request, UserRemoveResponse.class);
    }

    @Override
    @NotNull
    @SneakyThrows
    public UserUpdateProfileResponse updateUserProfile(@NotNull final UserUpdateProfileRequest request) throws AbstractFieldException {
        return call(request, UserUpdateProfileResponse.class);
    }

    @Override
    @NotNull
    @SneakyThrows
    public UserChangePasswordResponse changeUserPassword(@NotNull final UserChangePasswordRequest request) throws AbstractFieldException {
        return call(request, UserChangePasswordResponse.class);
    }

    @Override
    @NotNull
    @SneakyThrows
    public UserRegistryResponse registryUser(@NotNull final UserRegistryRequest request) throws AbstractException {
        return call(request, UserRegistryResponse.class);
    }

}
