package ru.t1.dkononov.tm.task;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.t1.dkononov.tm.component.Server;

import java.net.ServerSocket;
import java.net.Socket;

public final class ServerAcceptTask extends AbstractServerTask {

    public ServerAcceptTask(@NotNull final Server server) {
        super(server);
    }

    @Override
    public void run() {
        @Nullable final ServerSocket serverSocket = server.getSocketServer();
        if (serverSocket == null) return;
        @NotNull final Socket socket;
        try {
            socket = serverSocket.accept();
            server.submit(new ServerRequestTask(server, socket));
            server.submit(new ServerAcceptTask(server));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
