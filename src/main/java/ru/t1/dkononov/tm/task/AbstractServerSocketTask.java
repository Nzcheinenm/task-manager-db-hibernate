package ru.t1.dkononov.tm.task;

import org.jetbrains.annotations.NotNull;
import ru.t1.dkononov.tm.component.Server;

import java.net.Socket;

public abstract class AbstractServerSocketTask extends AbstractServerTask {

    @NotNull
    protected final Socket socket;

    public AbstractServerSocketTask(
            @NotNull final Server server,
            @NotNull final Socket socket
    ) {
        super(server);
        this.socket = socket;
    }

}
