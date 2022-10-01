package ru.t1.dkononov.tm.component;

import lombok.Getter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.t1.dkononov.tm.api.Operation;
import ru.t1.dkononov.tm.api.services.IAuthService;
import ru.t1.dkononov.tm.api.services.IPropertyService;
import ru.t1.dkononov.tm.dto.request.AbstractRequest;
import ru.t1.dkononov.tm.dto.response.AbstractResponse;
import ru.t1.dkononov.tm.task.AbstractServerTask;
import ru.t1.dkononov.tm.task.ServerAcceptTask;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public final class Server {

    @NotNull
    private final ExecutorService executorService = Executors.newCachedThreadPool();

    @NotNull
    private final Dispatcher dispatcher = new Dispatcher();

    @Getter
    @Nullable
    private ServerSocket socketServer;

    @NotNull
    private final Bootstrap bootstrap;

    public Server(@NotNull Bootstrap bootstrap) {
        this.bootstrap = bootstrap;
    }

    public void submit(@NotNull final AbstractServerTask task) {
        executorService.submit(task);
    }

    public void start() throws IOException {
        @NotNull final IPropertyService propertyService = bootstrap.getPropertyService();
        @NotNull final Integer port = propertyService.getServerPort();
        socketServer = new ServerSocket(port);
        submit(new ServerAcceptTask(this));
    }

    public <RQ extends AbstractRequest, RS extends AbstractResponse> void registry(
            @NotNull final Class<RQ> reqClass, @NotNull final Operation<RQ, RS> operation
    ) {
        dispatcher.registry(reqClass, operation);
    }

    @NotNull
    public Object call(@NotNull final AbstractRequest request) {
        return dispatcher.call(request);
    }

    public void stop() throws IOException {
        if (socketServer == null) return;
        executorService.shutdown();
        socketServer.close();
    }

    public Bootstrap getBootstrap() {
        return bootstrap;
    }
}
