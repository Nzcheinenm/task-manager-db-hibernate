package ru.t1.dkononov.tm.api.endpoint;

import org.jetbrains.annotations.NotNull;
import ru.t1.dkononov.tm.dto.request.*;
import ru.t1.dkononov.tm.dto.response.*;
import ru.t1.dkononov.tm.exception.AbstractException;
import ru.t1.dkononov.tm.exception.field.AbstractFieldException;

public interface IUserEndpoint {
    @NotNull UserLockResponse lockUser(@NotNull UserLockRequest request) throws Exception;

    @NotNull UserUnlockResponse unlockUser(@NotNull UserUnlockRequest request) throws Exception;

    @NotNull UserRemoveResponse removeUser(@NotNull UserRemoveRequest request) throws Exception;

    @NotNull UserUpdateProfileResponse updateUserProfile(@NotNull UserUpdateProfileRequest request) throws AbstractFieldException;

    @NotNull UserChangePasswordResponse changeUserPassword(@NotNull UserChangePasswordRequest request) throws AbstractFieldException;

    @NotNull UserRegistryResponse registryUser(@NotNull UserRegistryRequest request) throws AbstractException;
}
