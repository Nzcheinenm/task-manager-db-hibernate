package ru.t1.dkononov.tm.service;

import lombok.SneakyThrows;
import org.apache.ibatis.session.SqlSession;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.t1.dkononov.tm.api.repository.ISessionRepository;
import ru.t1.dkononov.tm.api.services.IConnectionService;
import ru.t1.dkononov.tm.api.services.ISessionService;
import ru.t1.dkononov.tm.exception.AbstractException;
import ru.t1.dkononov.tm.exception.entity.ProjectNotFoundException;
import ru.t1.dkononov.tm.exception.field.IdEmptyException;
import ru.t1.dkononov.tm.exception.field.UserIdEmptyException;
import ru.t1.dkononov.tm.model.Session;
import ru.t1.dkononov.tm.model.Task;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class SessionService implements ISessionService {

    @NotNull
    private final IConnectionService connectionService;

    public SessionService(@NotNull final IConnectionService connectionService) {
        this.connectionService = connectionService;
    }

    @Nullable
    @Override
    @SneakyThrows
    public Session findById(@Nullable final String userId, @Nullable final String id)
            throws AbstractException {
        if (userId == null || userId.isEmpty()) throw new UserIdEmptyException();
        if (id == null || id.isEmpty()) throw new IdEmptyException();
        try (@NotNull final SqlSession sqlSession = connectionService.getSqlSession()) {
            @NotNull final ISessionRepository repository = sqlSession.getMapper(ISessionRepository.class);
            return repository.findById(id);
        }
    }

    @NotNull
    @Override
    @SneakyThrows
    public List<Session> findAll() {
        try (@NotNull final SqlSession sqlSession = connectionService.getSqlSession()) {
            @NotNull final ISessionRepository repository = sqlSession.getMapper(ISessionRepository.class);
            return repository.findAll();
        }
    }

    @Nullable
    @Override
    @SneakyThrows
    public Session add(@Nullable final Session model)
            throws ProjectNotFoundException, UserIdEmptyException {
        if (model == null) throw new ProjectNotFoundException();
        @NotNull final SqlSession sqlSession = connectionService.getSqlSession();
        @Nullable final Session result;
        try {
            @NotNull final ISessionRepository repository = sqlSession.getMapper(ISessionRepository.class);
            repository.add(model);
            result = repository.findById(model.getId());
            sqlSession.commit();
        } catch (@NotNull final Exception e) {
            sqlSession.rollback();
            throw e;
        } finally {
            sqlSession.close();
        }
        return result;
    }

    @Nullable
    @Override
    @SneakyThrows
    public Collection<Session> add(@NotNull Collection<Session> models) {
        if (models.isEmpty()) return Collections.emptyList();
        @NotNull final SqlSession sqlSession = connectionService.getSqlSession();
        try {
            @NotNull final ISessionRepository repository = sqlSession.getMapper(ISessionRepository.class);
            models.forEach(repository::add);
            sqlSession.commit();
        } catch (@NotNull final Exception e) {
            sqlSession.rollback();
            throw e;
        } finally {
            sqlSession.close();
        }
        return models;
    }

    @Override
    @SneakyThrows
    public void clear(@Nullable final String userId) {
        if (userId == null || userId.isEmpty()) throw new UserIdEmptyException();
        @NotNull final SqlSession sqlSession = connectionService.getSqlSession();
        @Nullable final Task result;
        try {
            @NotNull final ISessionRepository repository = sqlSession.getMapper(ISessionRepository.class);
            repository.clear(userId);
            sqlSession.commit();
        } catch (@NotNull final Exception e) {
            sqlSession.rollback();
            throw e;
        } finally {
            sqlSession.close();
        }
    }

    @Nullable
    @Override
    @SneakyThrows
    public Session remove(@NotNull final String userId, @Nullable final Session model) {
        if (userId.isEmpty()) throw new UserIdEmptyException();
        @NotNull final SqlSession sqlSession = connectionService.getSqlSession();
        try {
            @NotNull final ISessionRepository repository = sqlSession.getMapper(ISessionRepository.class);
            model.setUserId(userId);
            repository.remove(model);
            sqlSession.commit();
        } catch (@NotNull final Exception e) {
            sqlSession.rollback();
            throw e;
        } finally {
            sqlSession.close();
        }
        return model;
    }

    @Override
    @SneakyThrows
    public boolean existsById(@Nullable final String id) {
        if (id == null || id.isEmpty()) return false;
        try (@NotNull final SqlSession sqlSession = connectionService.getSqlSession()) {
            @NotNull final ISessionRepository repository = sqlSession.getMapper(ISessionRepository.class);
            return repository.findById(id) != null;
        }
    }

    @Override
    @NotNull
    public List<Session> findAll(@Nullable String userId) throws UserIdEmptyException {
        if (userId == null || userId.isEmpty()) throw new UserIdEmptyException();
        try (@NotNull final SqlSession sqlSession = connectionService.getSqlSession()) {
            @NotNull final ISessionRepository repository = sqlSession.getMapper(ISessionRepository.class);
            return repository.findAllWithUserId(userId);
        }
    }


}
