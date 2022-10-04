package ru.t1.dkononov.tm.client;

import lombok.NoArgsConstructor;
import lombok.SneakyThrows;
import org.jetbrains.annotations.NotNull;
import ru.t1.dkononov.tm.api.client.IAuthEndpointClient;
import ru.t1.dkononov.tm.dto.request.UserLoginRequest;
import ru.t1.dkononov.tm.dto.request.UserLogoutRequest;
import ru.t1.dkononov.tm.dto.request.UserProfileRequest;
import ru.t1.dkononov.tm.dto.response.UserLoginResponse;
import ru.t1.dkononov.tm.dto.response.UserLogoutResponse;
import ru.t1.dkononov.tm.dto.response.UserProfileResponse;

@NoArgsConstructor
public final class AuthEndpointClient extends AbstractEndpointClient implements IAuthEndpointClient {

    public AuthEndpointClient(@NotNull AbstractEndpointClient client) {
        super(client);
    }

    @Override
    @NotNull
    @SneakyThrows
    public UserLoginResponse login(@NotNull UserLoginRequest request) {
        return call(request, UserLoginResponse.class);
    }

    @Override
    @NotNull
    @SneakyThrows
    public UserLogoutResponse logout(@NotNull UserLogoutRequest request) {
        return call(request, UserLogoutResponse.class);
    }

    @Override
    @NotNull
    @SneakyThrows
    public UserProfileResponse profile(@NotNull UserProfileRequest request) {
        return call(request, UserProfileResponse.class);
    }

    @SneakyThrows
    public static void main(String[] args) {
        @NotNull final AuthEndpointClient authEndpointClient = new AuthEndpointClient();
        authEndpointClient.connect();
        System.out.println(authEndpointClient.profile(new UserProfileRequest()).getUser());
        System.out.println(authEndpointClient.login(new UserLoginRequest("test2","test2")).getSuccess());
        authEndpointClient.disconnect();
    }

}
