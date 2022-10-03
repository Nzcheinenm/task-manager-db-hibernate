package ru.t1.dkononov.tm.api.client;

import org.jetbrains.annotations.NotNull;
import ru.t1.dkononov.tm.dto.request.ApplicationAboutRequest;
import ru.t1.dkononov.tm.dto.request.ApplicationVersionRequest;
import ru.t1.dkononov.tm.dto.response.ApplicationAboutResponse;
import ru.t1.dkononov.tm.dto.response.ApplicationVersionResponse;

public interface ISystemEndpointClient {
    @NotNull ApplicationAboutResponse getAbout(@NotNull ApplicationAboutRequest request)
            throws Exception;

    @NotNull ApplicationVersionResponse getVersion(@NotNull ApplicationVersionRequest request)
            throws Exception;
}
