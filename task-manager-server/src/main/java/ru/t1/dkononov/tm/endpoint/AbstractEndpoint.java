package ru.t1.dkononov.tm.endpoint;

import lombok.Getter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.t1.dkononov.tm.api.services.IServiceLocator;
import ru.t1.dkononov.tm.api.services.IUserService;
import ru.t1.dkononov.tm.dto.request.AbstractUserRequest;
import ru.t1.dkononov.tm.enumerated.Role;
import ru.t1.dkononov.tm.exception.field.AccessDeniedException;
import ru.t1.dkononov.tm.model.Session;
import ru.t1.dkononov.tm.model.User;

public abstract class AbstractEndpoint {

    protected AbstractEndpoint(@NotNull final IServiceLocator serviceLocator) {
        this.serviceLocator = serviceLocator;
    }

    protected Session check(
            @Nullable final AbstractUserRequest request,
            @Nullable final Role role
    ) throws Exception {
        if (request == null) throw new AccessDeniedException();
        if (role == null) throw new AccessDeniedException();
        @Nullable final String token = request.getToken();
        @Nullable final Session session = serviceLocator.getAuthService().validateToken(token);
       if (session.getRole() == null) throw new AccessDeniedException();
       if (!session.getRole().equals(role)) throw new AccessDeniedException();
       return session;
    }

    protected Session check(@Nullable final AbstractUserRequest request)
            throws AccessDeniedException {
        if (request == null) throw new AccessDeniedException();
        @Nullable final String token = request.getToken();
        if (token == null || token.isEmpty()) throw new AccessDeniedException();
        return serviceLocator.getAuthService().validateToken(token);
    }

    @Getter
    @NotNull
    protected final IServiceLocator serviceLocator;

}
