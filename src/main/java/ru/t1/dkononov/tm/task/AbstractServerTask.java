package ru.t1.dkononov.tm.task;

import org.jetbrains.annotations.NotNull;
import ru.t1.dkononov.tm.component.Server;

public abstract class AbstractServerTask implements Runnable {

    @NotNull
    protected Server server;

    public AbstractServerTask(@NotNull final Server server) {
        this.server = server;
    }

}
