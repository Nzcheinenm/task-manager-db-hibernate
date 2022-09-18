package ru.t1.dkononov.tm.client;

import lombok.Getter;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;

import java.io.*;
import java.net.Socket;

@Getter
@Setter
public abstract class AbstractEndpoint {

    @NotNull
    private String host = "localhost";

    @NotNull
    private Integer port = 6060;

    @NotNull
    private Socket socket;

    public AbstractEndpoint() {
    }

    public AbstractEndpoint(
            @NotNull final String host,
            @NotNull final Integer port
    ) {
        this.host = host;
        this.port = port;
    }

    protected Object call(@NotNull final Object data) throws IOException, ClassNotFoundException {
        getObjectOutputStream().writeObject(data);
        return getObjectInputStream().readObject();
    }

    private ObjectOutputStream getObjectOutputStream() throws IOException {
        return new ObjectOutputStream(getOutputStream());
    }

    private ObjectInputStream getObjectInputStream() throws IOException {
        return new ObjectInputStream(getInputStream());
    }

    private OutputStream getOutputStream() throws IOException {
        return socket.getOutputStream();
    }

    private InputStream getInputStream() throws IOException {
        return socket.getInputStream();
    }

    public void connect() throws IOException {
        socket = new Socket(host, port);
    }

    public void disconnect() throws IOException {
        socket.close();
    }

}
