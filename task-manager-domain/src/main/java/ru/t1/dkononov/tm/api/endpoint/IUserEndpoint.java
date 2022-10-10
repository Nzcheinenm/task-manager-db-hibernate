package ru.t1.dkononov.tm.api.endpoint;

import org.jetbrains.annotations.NotNull;
import ru.t1.dkononov.tm.dto.request.*;
import ru.t1.dkononov.tm.dto.response.*;
import ru.t1.dkononov.tm.exception.AbstractException;
import ru.t1.dkononov.tm.exception.field.AbstractFieldException;

import javax.jws.WebMethod;
import javax.jws.WebService;

@WebService
public interface IUserEndpoint {
    @NotNull
    @WebMethod
    UserLockResponse lockUser(@NotNull UserLockRequest request) throws Exception;

    @NotNull
    @WebMethod
    UserUnlockResponse unlockUser(@NotNull UserUnlockRequest request) throws Exception;

    @NotNull
    @WebMethod
    UserRemoveResponse removeUser(@NotNull UserRemoveRequest request) throws Exception;

    @NotNull
    @WebMethod
    UserUpdateProfileResponse updateUserProfile(@NotNull UserUpdateProfileRequest request) throws AbstractFieldException;

    @NotNull
    @WebMethod
    UserChangePasswordResponse changeUserPassword(@NotNull UserChangePasswordRequest request) throws AbstractFieldException;

    @NotNull
    @WebMethod
    UserRegistryResponse registryUser(@NotNull UserRegistryRequest request) throws AbstractException;
}
