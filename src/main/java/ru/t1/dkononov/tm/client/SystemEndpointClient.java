package ru.t1.dkononov.tm.client;

import org.jetbrains.annotations.NotNull;
import ru.t1.dkononov.tm.api.client.ISystemEndpointClient;
import ru.t1.dkononov.tm.dto.request.ApplicationAboutRequest;
import ru.t1.dkononov.tm.dto.request.ApplicationVersionRequest;
import ru.t1.dkononov.tm.dto.response.ApplicationAboutResponse;
import ru.t1.dkononov.tm.dto.response.ApplicationVersionResponse;

public final class SystemEndpointClient extends AbstractEndpoint implements ISystemEndpointClient {

    @Override
    @NotNull
    public ApplicationAboutResponse getAbout(@NotNull ApplicationAboutRequest request)
            throws Exception {
        return (ApplicationAboutResponse) call(request);
    }

    @Override
    @NotNull
    public ApplicationVersionResponse getVersion(@NotNull ApplicationVersionRequest request)
            throws Exception {
        return (ApplicationVersionResponse) call(request);
    }

    public static void main(String[] args)
            throws Exception {
        @NotNull final SystemEndpointClient client = new SystemEndpointClient();
        client.connect();
        @NotNull final ApplicationAboutResponse serverAboutResponse = client.getAbout(new ApplicationAboutRequest());
        System.out.println(serverAboutResponse.getEmail());
        System.out.println(serverAboutResponse.getName());

        @NotNull final ApplicationVersionResponse applicationVersionResponse = client.getVersion(new ApplicationVersionRequest());
        System.out.println(applicationVersionResponse.getVersion());

        client.disconnect();
    }

}
