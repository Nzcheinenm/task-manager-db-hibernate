package ru.t1.dkononov.tm.client;

import org.jetbrains.annotations.NotNull;
import ru.t1.dkononov.tm.api.client.ISystemEndpointClient;
import ru.t1.dkononov.tm.dto.request.ServerAboutRequest;
import ru.t1.dkononov.tm.dto.request.ServerVersionRequest;
import ru.t1.dkononov.tm.dto.response.ServerAboutResponse;
import ru.t1.dkononov.tm.dto.response.ServerVersionResponse;

import java.io.IOException;

public final class SystemEndpointClient extends AbstractEndpoint implements ISystemEndpointClient {

    @Override
    @NotNull
    public ServerAboutResponse getAbout(@NotNull ServerAboutRequest request)
            throws IOException, ClassNotFoundException {
        return (ServerAboutResponse) call(request);
    }

    @Override
    @NotNull
    public ServerVersionResponse getVersion(@NotNull ServerVersionRequest request)
            throws IOException, ClassNotFoundException {
        return (ServerVersionResponse) call(request);
    }

    public static void main(String[] args)
            throws IOException, ClassNotFoundException {
        @NotNull final SystemEndpointClient client = new SystemEndpointClient();
        client.connect();
        @NotNull final ServerAboutResponse serverAboutResponse = client.getAbout(new ServerAboutRequest());
        System.out.println(serverAboutResponse.getEmail());
        System.out.println(serverAboutResponse.getName());

        @NotNull final ServerVersionResponse serverVersionResponse = client.getVersion(new ServerVersionRequest());
        System.out.println(serverVersionResponse.getVersion());

        client.disconnect();
    }

}
