package ru.t1.dkononov.tm.client;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.t1.dkononov.tm.api.client.IEndpointClient;
import ru.t1.dkononov.tm.dto.response.ApplicationErrorResponse;
import ru.t1.dkononov.tm.dto.response.UserLoginResponse;

import java.io.*;
import java.net.Socket;

@Getter
@Setter
@NoArgsConstructor
public abstract class AbstractEndpointClient implements IEndpointClient {

    @NotNull
    private String host = "localhost";

    @NotNull
    private Integer port = 6060;

    @NotNull
    private Socket socket;

    public AbstractEndpointClient(
            @NotNull final String host,
            @NotNull final Integer port
    ) {
        this.host = host;
        this.port = port;
    }

    public AbstractEndpointClient(
            @NotNull final AbstractEndpointClient client
    ) {
        this.host = client.host;
        this.port = client.port;
        this.socket = client.socket;
    }

    protected Object call(@NotNull final Object data) throws Exception {
        getObjectOutputStream().writeObject(data);
        return getObjectInputStream().readObject();
    }

    protected <T> T call(@NotNull final Object data, @NotNull final Class<T> clazz) throws Exception {
        getObjectOutputStream().writeObject(data);
        @NotNull final Object result = getObjectInputStream().readObject();

        if (result instanceof ApplicationErrorResponse) {
            @NotNull final ApplicationErrorResponse response = (ApplicationErrorResponse) result;
            throw new RuntimeException(response.getMessage());
        }

        if (result instanceof UserLoginResponse) {
            @NotNull final UserLoginResponse response = (UserLoginResponse) result;
            if (!response.getSuccess())
                throw new RuntimeException(response.getMessage());
        }

        return (T) result;
    }

    private ObjectOutputStream getObjectOutputStream() throws IOException {
        return new ObjectOutputStream(getOutputStream());
    }

    private ObjectInputStream getObjectInputStream() throws IOException {
        return new ObjectInputStream(getInputStream());
    }

    private OutputStream getOutputStream() throws IOException {
        if (socket == null) return null;
        return socket.getOutputStream();
    }

    private InputStream getInputStream() throws IOException {
        if (socket == null) return null;
        return socket.getInputStream();
    }

    @Override
    @Nullable
    public Socket connect() throws IOException {
        socket = new Socket(host, port);
        return socket;
    }

    @Override
    public void disconnect() throws IOException {
        socket.close();
    }

}
