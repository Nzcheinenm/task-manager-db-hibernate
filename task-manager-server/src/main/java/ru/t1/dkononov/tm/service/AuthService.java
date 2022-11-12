package ru.t1.dkononov.tm.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.t1.dkononov.tm.api.services.IAuthService;
import ru.t1.dkononov.tm.api.services.IPropertyService;
import ru.t1.dkononov.tm.api.services.dto.ISessionDTOService;
import ru.t1.dkononov.tm.api.services.dto.IUserDTOService;
import ru.t1.dkononov.tm.dto.model.SessionDTO;
import ru.t1.dkononov.tm.dto.model.UserDTO;
import ru.t1.dkononov.tm.enumerated.Role;
import ru.t1.dkononov.tm.exception.AbstractException;
import ru.t1.dkononov.tm.exception.entity.ProjectNotFoundException;
import ru.t1.dkononov.tm.exception.field.*;
import ru.t1.dkononov.tm.util.CryptUtil;
import ru.t1.dkononov.tm.util.HashUtil;

import java.util.Date;


public final class AuthService implements IAuthService {


    @Nullable
    private final IUserDTOService userService;

    @Nullable
    private final IPropertyService propertyService;

    @NotNull
    private final ISessionDTOService sessionService;

    @Nullable
    private String userId;

    public AuthService(
            @Nullable final IUserDTOService userService,
            @Nullable final IPropertyService propertyService,
            @NotNull final ISessionDTOService sessionService) {
        this.userService = userService;
        this.propertyService = propertyService;
        this.sessionService = sessionService;
    }

    @NotNull
    @Override
    @SneakyThrows
    public SessionDTO validateToken(@Nullable final String token) {
        if (token == null) throw new AccessDeniedException();
        @NotNull final String sessionKey = propertyService.getSessionKey();
        @NotNull String json;
        try {
            json = CryptUtil.decrypt(sessionKey, token);
        } catch (@NotNull final Exception e) {
            throw new AccessDeniedException();
        }
        @NotNull final ObjectMapper objectMapper = new ObjectMapper();
        @NotNull SessionDTO session = objectMapper.readValue(json, SessionDTO.class);

        @NotNull final Date currentDate = new Date();
        @NotNull final Date sessionDate = session.getDate();
        final long delta = (currentDate.getTime() - sessionDate.getTime()) / 1000;
        @NotNull final int timeout = Integer.parseInt(propertyService.getSessionTimeout());
        if (delta > timeout) throw new AccessDeniedException();

        if (!sessionService.existsById(session.getId())) throw new AccessDeniedException();

        return session;
    }

    @Override
    public void invalidate(@Nullable final SessionDTO session) throws UserIdEmptyException {
        if (session == null) return;
        sessionService.remove(session.getUserId(), session);
    }

    @NotNull
    @Override
    public String login(
            @Nullable final String login,
            @Nullable final String password
    ) throws Exception {
        if (login == null || login.isEmpty()) throw new LoginEmptyException();
        if (password == null || password.isEmpty()) throw new PasswordEmptyException();
        @Nullable final UserDTO user = userService.findByLogin(login);
        if (user == null) throw new AccessDeniedException();
        if (user.getLocked()) throw new AccessDeniedException();
        @Nullable final String hash = HashUtil.salt(propertyService, password);
        if (hash == null) throw new AccessDeniedException();
        if (!hash.equals(user.getPasswordHash())) throw new AccessDeniedException();
        return getToken(user);
    }

    @NotNull
    @SneakyThrows
    private String getToken(@NotNull final UserDTO user) {
        return getToken(createSession(user));
    }

    @NotNull
    @SneakyThrows
    private String getToken(@NotNull final SessionDTO session) {
        @NotNull final ObjectMapper objectMapper = new ObjectMapper();
        @NotNull final String token = objectMapper.writeValueAsString(session);
        @NotNull final String sessionKey = propertyService.getSessionKey();
        return CryptUtil.encrypt(sessionKey, token);
    }

    @NotNull
    private SessionDTO createSession(@NotNull final UserDTO user) throws UserIdEmptyException, ProjectNotFoundException {
        @NotNull final SessionDTO session = new SessionDTO();
        session.setUserId(user.getId());
        @NotNull final Role role = user.getRole();
        session.setRole(role);
        return sessionService.add(session);
    }


    @Nullable
    @Override
    public UserDTO registry(
            @NotNull final String login,
            @NotNull final String password,
            @NotNull final String email
    )
            throws AbstractException {
        return userService.create(login, password, email);
    }


    @Override
    public boolean isAuth() {
        return userId != null;
    }

    @Nullable
    @Override
    public String getUserId() throws AccessDeniedException {
        if (!isAuth()) throw new AccessDeniedException();
        return userId;
    }

    @NotNull
    @Override
    public UserDTO getUser() throws AbstractException {
        if (!isAuth()) throw new AccessDeniedException();
        @Nullable final UserDTO user = userService.findById(userId);
        if (user == null) throw new AccessDeniedException();
        return user;
    }

    @Override
    public @NotNull UserDTO check(String login, String password) throws AbstractException {
        if (login == null || login.isEmpty()) throw new LoginEmptyException();
        if (password == null || password.isEmpty()) throw new PasswordEmptyException();
        @Nullable final UserDTO user = userService.findByLogin(login);
        if (user == null) throw new PermissionException();
        if (user.getLocked()) throw new PermissionException();
        @Nullable final String hash = HashUtil.salt(propertyService, password);
        if (hash == null) throw new PermissionException();
        if (!hash.equals(user.getPasswordHash())) throw new PermissionException();
        return user;

    }
}
