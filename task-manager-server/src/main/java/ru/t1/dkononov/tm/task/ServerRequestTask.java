package ru.t1.dkononov.tm.task;

import lombok.SneakyThrows;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.t1.dkononov.tm.api.services.IAuthService;
import ru.t1.dkononov.tm.api.services.IUserService;
import ru.t1.dkononov.tm.component.Server;
import ru.t1.dkononov.tm.dto.request.*;
import ru.t1.dkononov.tm.dto.response.*;
import ru.t1.dkononov.tm.exception.field.IdEmptyException;
import ru.t1.dkononov.tm.model.User;

import java.io.*;
import java.net.Socket;

public final class ServerRequestTask extends AbstractServerSocketTask {

    @Nullable
    private AbstractRequest request;

    @Nullable
    private AbstractResponse response;

    public ServerRequestTask(
            @NotNull final Server server,
            @NotNull final Socket socket
    ) {
        super(server, socket);
    }

    public ServerRequestTask(
            @NotNull final Server server,
            @NotNull final Socket socket,
            @NotNull final String userId
    ) {
        super(server, socket, userId);
    }

    @SneakyThrows
    @Override
    public void run() {
        processInput();

        processUserId();
        processLogin();
        processProfile();
        processLogout();

        processOperation();
        processOutput();
    }

    private void processOutput() throws IOException {
        @NotNull final OutputStream outputStream = socket.getOutputStream();
        @NotNull final ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
        objectOutputStream.writeObject(response);
        server.submit(new ServerRequestTask(server, socket, userId));
    }

    private void processOperation() {
        if (response != null) return;
        try {
            @Nullable final Object result = server.call(request);
            response = (AbstractResponse) result;
        } catch (@NotNull final Exception e) {
            response = new ApplicationErrorResponse(e);
        }
    }

    private void processLogout() {
        if (response != null) return;
        if (!(request instanceof UserLogoutRequest)) return;
        userId = null;
        response = new UserLogoutResponse();
    }

    private void processProfile() throws IdEmptyException {
        if (response != null) return;
        if (!(request instanceof UserProfileRequest)) return;
        if (userId == null) {
            response = new UserProfileResponse();
            return;
        }
        @NotNull final IUserService userService = server.getBootstrap().getUserService();
        @Nullable final User user = userService.findById(userId);
        response = new UserProfileResponse(user);
    }

    private void processLogin() {
        if (response != null) return;
        if (!(request instanceof UserLoginRequest)) return;
        try {
            @NotNull final UserLoginRequest userLoginRequest = (UserLoginRequest) request;
            @Nullable final String login = userLoginRequest.getLogin();
            @Nullable final String password = userLoginRequest.getPassword();
            @NotNull final IAuthService authService = server.getBootstrap().getAuthService();
            @NotNull final User user = authService.check(login, password);
            userId = user.getId();
            response = new UserLoginResponse();
        } catch (@NotNull final Exception e) {
            response = new UserLoginResponse(e);
        }
    }

    private void processUserId() {
        if (!(request instanceof AbstractUserRequest)) return;
        @NotNull final AbstractUserRequest abstractUserRequest = (AbstractUserRequest) request;
        abstractUserRequest.setUserId(userId);
    }

    private void processInput() throws Exception {
        @NotNull final InputStream inputStream = socket.getInputStream();
        @NotNull final ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);
        @NotNull final Object object = objectInputStream.readObject();
        request = (AbstractRequest) object;
    }

}
