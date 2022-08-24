package ru.t1.dkononov.tm.service;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.t1.dkononov.tm.api.services.IUserService;
import ru.t1.dkononov.tm.enumerated.Role;
import ru.t1.dkononov.tm.exception.AbstractException;
import ru.t1.dkononov.tm.exception.field.*;
import ru.t1.dkononov.tm.model.User;
import ru.t1.dkononov.tm.util.HashUtil;

import java.util.Arrays;


public class AuthService implements ru.t1.dkononov.tm.api.services.IAuthService {


    @Nullable
    private final IUserService userService;

    @Nullable
    private String userId;

    public AuthService(@Nullable final IUserService userService) {
        this.userService = userService;
    }

    @Nullable
    @Override
    public User registry(
            @NotNull final String login,
            @NotNull final String password,
            @NotNull final String email
    )
            throws AbstractException {
        return userService.create(login, password, email);
    }

    @Override
    public void login(
            @Nullable final String login,
            @Nullable final String password
    )
            throws AbstractFieldException {
        if (login == null || login.isEmpty()) throw new LoginEmptyException();
        if (password == null || password.isEmpty()) throw new PasswordEmptyException();
        @Nullable final User user = userService.findByLogin(login);
        if (user == null) throw new AccessDeniedException();
        final boolean locked = user.isLocked() == null || user.isLocked();
        if (locked) throw new AccessDeniedException();
        @NotNull final String hash = HashUtil.salt(password);
        if (!hash.equals(user.getPasswordHash())) throw new AccessDeniedException();
        userId = user.getId();
    }

    @Override
    public void logout() {
        userId = null;
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
    public User getUser() throws AbstractFieldException {
        if (!isAuth()) throw new AccessDeniedException();
        @Nullable final User user = userService.findById(userId);
        if (user == null) throw new AccessDeniedException();
        return user;
    }

    @Override
    public void checkRoles(@Nullable final Role[] roles) throws AbstractException {
        if (roles == null) return;
        @NotNull final User user = getUser();
        @Nullable final Role role = user.getRole();
        if (role == null) throw new PermissionException();
        final boolean hasRole = Arrays.asList(roles).contains(role);
        if (!hasRole) throw new PermissionException();
    }

}
