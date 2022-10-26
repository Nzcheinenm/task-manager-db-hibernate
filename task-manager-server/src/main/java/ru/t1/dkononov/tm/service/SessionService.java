package ru.t1.dkononov.tm.service;

import org.jetbrains.annotations.NotNull;
import ru.t1.dkononov.tm.api.repository.IProjectRepository;
import ru.t1.dkononov.tm.api.repository.ISessionRepository;
import ru.t1.dkononov.tm.api.services.IConnectionService;
import ru.t1.dkononov.tm.api.services.ISessionService;
import ru.t1.dkononov.tm.model.Session;
import ru.t1.dkononov.tm.repository.ProjectRepository;
import ru.t1.dkononov.tm.repository.SessionRepository;

import java.sql.Connection;

public class SessionService extends AbstractUserOwnedService<Session, ISessionRepository> implements ISessionService {


    public SessionService(@NotNull final IConnectionService connectionService) {
        super(connectionService);
    }

    @NotNull
    public ISessionRepository getRepository(@NotNull Connection connection) {
        return new SessionRepository(connection);
    }

}
