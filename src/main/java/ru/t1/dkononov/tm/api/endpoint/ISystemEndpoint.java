package ru.t1.dkononov.tm.api.endpoint;

import org.jetbrains.annotations.NotNull;
import ru.t1.dkononov.tm.dto.request.ServerAboutRequest;
import ru.t1.dkononov.tm.dto.request.ServerVersionRequest;
import ru.t1.dkononov.tm.dto.response.ServerAboutResponse;
import ru.t1.dkononov.tm.dto.response.ServerVersionResponse;

public interface ISystemEndpoint {
    @NotNull ServerAboutResponse getAbout(@NotNull ServerAboutRequest request);

    @NotNull ServerVersionResponse getVersion(@NotNull ServerVersionRequest request);
}
