package ru.t1.dkononov.tm.endpoint;

import lombok.Getter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.t1.dkononov.tm.api.services.IServiceLocator;
import ru.t1.dkononov.tm.api.services.IUserService;
import ru.t1.dkononov.tm.dto.request.AbstractUserRequest;
import ru.t1.dkononov.tm.enumerated.Role;
import ru.t1.dkononov.tm.exception.field.AccessDeniedException;
import ru.t1.dkononov.tm.model.User;

public abstract class AbstractEndpoint {

    protected AbstractEndpoint(@NotNull final IServiceLocator serviceLocator) {
        this.serviceLocator = serviceLocator;
    }

    protected void check(
            @Nullable final AbstractUserRequest request,
            @Nullable final Role role
    ) throws Exception {
        if (request == null) throw new AccessDeniedException();
        if (role == null) throw new AccessDeniedException();
        @Nullable final String userId = request.getUserId();
        if (userId == null || userId.isEmpty()) throw new AccessDeniedException();
        @NotNull final IUserService userService = serviceLocator.getUserService();
        @Nullable final User user = userService.findById(userId);
        if (user == null) throw new AccessDeniedException();
        @Nullable final Role roleUser = user.getRole();
        final boolean check = roleUser == role;
        if (!check) throw new AccessDeniedException();
    }

    protected void check(@Nullable final AbstractUserRequest request)
            throws AccessDeniedException {
        if (request == null) throw new AccessDeniedException();
        @Nullable final String userId = request.getUserId();
        if (userId == null || userId.isEmpty()) throw new AccessDeniedException();
    }

    @Getter
    @NotNull
    protected final IServiceLocator serviceLocator;

}
