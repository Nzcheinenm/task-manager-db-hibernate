package ru.t1.dkononov.tm.service;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.t1.dkononov.tm.api.services.IAuthService;
import ru.t1.dkononov.tm.api.services.IPropertyService;
import ru.t1.dkononov.tm.api.services.IUserService;
import ru.t1.dkononov.tm.exception.AbstractException;
import ru.t1.dkononov.tm.exception.field.*;
import ru.t1.dkononov.tm.model.User;
import ru.t1.dkononov.tm.util.HashUtil;


public final class AuthService implements IAuthService {


    @Nullable
    private final IUserService userService;

    @Nullable
    private final IPropertyService propertyService;

    @Nullable
    private String userId;

    public AuthService(
            @Nullable final IUserService userService,
            @Nullable IPropertyService propertyService
    ) {
        this.userService = userService;
        this.propertyService = propertyService;
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
    public @NotNull User check(String login, String password) throws AbstractException {
        if (login == null || login.isEmpty()) throw new LoginEmptyException();
        if (password == null || password.isEmpty()) throw new PasswordEmptyException();
        @Nullable final User user = userService.findByLogin(login);
        if (user == null) throw new PermissionException();
        if (user.getLocked()) throw new PermissionException();
        @Nullable final String hash = HashUtil.salt(propertyService, password);
        if (hash == null) throw new PermissionException();
        if (!hash.equals(user.getPasswordHash())) throw new PermissionException();
        return user;

    }
}
