package ru.t1.dkononov.tm.api.client;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.IOException;
import java.net.Socket;

public interface IEndpointClient {
    @Nullable
    Socket connect() throws IOException;

    void disconnect() throws IOException;

    @NotNull
    String getHost();

    @NotNull
    Integer getPort();

}
