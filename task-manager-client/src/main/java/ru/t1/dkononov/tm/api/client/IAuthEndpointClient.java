package ru.t1.dkononov.tm.api.client;

import lombok.SneakyThrows;
import org.jetbrains.annotations.NotNull;
import ru.t1.dkononov.tm.dto.request.UserLoginRequest;
import ru.t1.dkononov.tm.dto.request.UserLogoutRequest;
import ru.t1.dkononov.tm.dto.request.UserProfileRequest;
import ru.t1.dkononov.tm.dto.response.UserLoginResponse;
import ru.t1.dkononov.tm.dto.response.UserLogoutResponse;
import ru.t1.dkononov.tm.dto.response.UserProfileResponse;

public interface IAuthEndpointClient {
    @NotNull
    @SneakyThrows
    UserLoginResponse login(@NotNull UserLoginRequest request);

    @NotNull
    @SneakyThrows
    UserLogoutResponse logout(@NotNull UserLogoutRequest request);

    @NotNull
    @SneakyThrows
    UserProfileResponse profile(@NotNull UserProfileRequest request);
}
