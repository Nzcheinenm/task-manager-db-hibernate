package ru.t1.dkononov.tm.task;

import lombok.SneakyThrows;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.t1.dkononov.tm.component.Server;
import ru.t1.dkononov.tm.dto.request.AbstractRequest;

import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;

public final class ServerRequestTask extends AbstractServerSocketTask {

    public ServerRequestTask(
            @NotNull final Server server,
            @NotNull final Socket socket
    ) {
        super(server, socket);
    }

    @SneakyThrows
    @Override
    public void run() {
        @NotNull final InputStream inputStream;
        inputStream = socket.getInputStream();
        @NotNull final ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);
        @NotNull final Object object = objectInputStream.readObject();
        @NotNull final AbstractRequest request = (AbstractRequest) object;
        @Nullable Object response = server.call(request);
        @NotNull final OutputStream outputStream = socket.getOutputStream();
        @NotNull final ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
        objectOutputStream.writeObject(response);
        server.submit(new ServerRequestTask(server, socket));
    }

}
