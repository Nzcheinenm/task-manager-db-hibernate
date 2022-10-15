package ru.t1.dkononov.tm.api.endpoint;

import lombok.SneakyThrows;
import org.jetbrains.annotations.NotNull;
import ru.t1.dkononov.tm.dto.request.*;
import ru.t1.dkononov.tm.dto.response.*;
import ru.t1.dkononov.tm.exception.AbstractException;
import ru.t1.dkononov.tm.exception.field.AbstractFieldException;

import javax.jws.WebMethod;
import javax.jws.WebService;

@WebService
public interface IUserEndpoint extends IEndpoint {

    @NotNull
    String NAME = "UserEndpoint";

    @NotNull
    String PART = NAME + "Service";

    @SneakyThrows
    @WebMethod(exclude = true)
    static IUserEndpoint newInstance() {
        return newInstance(HOST,PORT);
    }

    @SneakyThrows
    @WebMethod(exclude = true)
    static IUserEndpoint newInstance(@NotNull final IConnectionProvider connectionProvider) {
        return IEndpoint.newInstance(connectionProvider,NAME,SPACE,PART,IUserEndpoint.class);
    }

    @SneakyThrows
    @WebMethod(exclude = true)
    static IUserEndpoint newInstance(@NotNull final String host, @NotNull final String port) {
        return IEndpoint.newInstance(host,port,NAME,SPACE,PART,IUserEndpoint.class);
    }

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
