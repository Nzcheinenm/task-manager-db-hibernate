package ru.t1.dkononov.tm.task;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.t1.dkononov.tm.component.Server;

import java.net.Socket;

public abstract class AbstractServerSocketTask extends AbstractServerTask {

    @NotNull
    protected final Socket socket;
    
    @Nullable
    protected String userId = null;

    public AbstractServerSocketTask(
            @NotNull final Server server,
            @NotNull final Socket socket
    ) {
        super(server);
        this.socket = socket;
    }

    public AbstractServerSocketTask(
            @NotNull final Server server,
            @NotNull final Socket socket,
            @NotNull final String userId
    ) {
        super(server);
        this.socket = socket;
        this.userId = userId;
    }

}
