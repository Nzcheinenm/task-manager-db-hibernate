package ru.t1.dkononov.tm.service;

import org.jetbrains.annotations.NotNull;
import ru.t1.dkononov.tm.api.repository.ISessionRepository;
import ru.t1.dkononov.tm.model.Session;

public class SessionService extends AbstractUserOwnedService<Session, ISessionRepository> {

    public SessionService(@NotNull ISessionRepository repository) {
        super(repository);
    }

}
