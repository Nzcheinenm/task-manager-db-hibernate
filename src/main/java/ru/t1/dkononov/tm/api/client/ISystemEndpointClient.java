package ru.t1.dkononov.tm.api.client;

import org.jetbrains.annotations.NotNull;
import ru.t1.dkononov.tm.dto.request.ServerAboutRequest;
import ru.t1.dkononov.tm.dto.request.ServerVersionRequest;
import ru.t1.dkononov.tm.dto.response.ServerAboutResponse;
import ru.t1.dkononov.tm.dto.response.ServerVersionResponse;

import java.io.IOException;

public interface ISystemEndpointClient {
    @NotNull ServerAboutResponse getAbout(@NotNull ServerAboutRequest request)
            throws IOException, ClassNotFoundException;

    @NotNull ServerVersionResponse getVersion(@NotNull ServerVersionRequest request)
            throws IOException, ClassNotFoundException;
}
