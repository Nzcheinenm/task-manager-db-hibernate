package ru.t1.dkononov.tm.service;


import lombok.SneakyThrows;
import org.apache.ibatis.session.SqlSession;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.t1.dkononov.tm.api.repository.ITaskRepository;
import ru.t1.dkononov.tm.api.services.IConnectionService;
import ru.t1.dkononov.tm.api.services.ITaskService;
import ru.t1.dkononov.tm.enumerated.Sort;
import ru.t1.dkononov.tm.enumerated.Status;
import ru.t1.dkononov.tm.exception.AbstractException;
import ru.t1.dkononov.tm.exception.entity.ProjectNotFoundException;
import ru.t1.dkononov.tm.exception.field.*;
import ru.t1.dkononov.tm.dto.model.TaskDTO;

import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public final class TaskService implements ITaskService {

    @NotNull
    private final IConnectionService connectionService;

    public TaskService(@NotNull final IConnectionService connectionService) {
        this.connectionService = connectionService;
    }

    @NotNull
    @Override
    @SneakyThrows
    public List<TaskDTO> findAll(@Nullable final String userId) throws UserIdEmptyException {
        if (userId == null || userId.isEmpty()) throw new UserIdEmptyException();
        try (@NotNull final SqlSession sqlSession = connectionService.getSqlSession()) {
            @NotNull final ITaskRepository repository = sqlSession.getMapper(ITaskRepository.class);
            return repository.findAllWithUserId(userId);
        }

    }

    @Nullable
    @Override
    @SneakyThrows
    public List<TaskDTO> findAll(
            @Nullable final String userId,
            @Nullable final Comparator comparator
    ) throws UserIdEmptyException {
        if (userId == null || userId.isEmpty()) throw new UserIdEmptyException();
        try (@NotNull final SqlSession sqlSession = connectionService.getSqlSession()) {
            @NotNull final ITaskRepository repository = sqlSession.getMapper(ITaskRepository.class);
            if (comparator == null) return findAll(userId);
            return repository.findAllWithUserId(userId);
        }
    }

    @NotNull
    @Override
    @SneakyThrows
    public List<TaskDTO> findAll(
            @Nullable final String userId,
            @Nullable final Sort sort
    ) throws UserIdEmptyException {
        if (userId == null || userId.isEmpty()) throw new UserIdEmptyException();
        if (sort == null) return findAll(userId);
        return findAll(userId, sort.getComparator());
    }

    @NotNull
    @Override
    @SneakyThrows
    public List<TaskDTO> findAll() {
        try (@NotNull final SqlSession sqlSession = connectionService.getSqlSession()) {
            @NotNull final ITaskRepository repository = sqlSession.getMapper(ITaskRepository.class);
            return repository.findAll();
        }
    }

    @Nullable
    @Override
    @SneakyThrows
    public TaskDTO add(@Nullable final String userId, @Nullable final TaskDTO model)
            throws ProjectNotFoundException, UserIdEmptyException {
        if (userId == null || userId.isEmpty()) throw new UserIdEmptyException();
        if (model == null) throw new ProjectNotFoundException();
        @NotNull final SqlSession sqlSession = connectionService.getSqlSession();
        try {
            @NotNull final ITaskRepository repository = sqlSession.getMapper(ITaskRepository.class);
            model.setUserId(userId);
            repository.add(model);
            sqlSession.commit();
        } catch (@NotNull final Exception e) {
            sqlSession.rollback();
            throw e;
        } finally {
            sqlSession.close();
        }
        return model;
    }

    @Nullable
    @Override
    @SneakyThrows
    public Collection<TaskDTO> add(@NotNull Collection<TaskDTO> models) {
        if (models.isEmpty()) return Collections.emptyList();
        @NotNull final SqlSession sqlSession = connectionService.getSqlSession();
        try {
            @NotNull final ITaskRepository repository = sqlSession.getMapper(ITaskRepository.class);
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
    @NotNull
    @SneakyThrows
    public Collection<TaskDTO> set(@NotNull Collection<TaskDTO> models) {
        if (models.isEmpty()) return Collections.emptyList();
        @NotNull final SqlSession sqlSession = connectionService.getSqlSession();
        try {
            @NotNull final ITaskRepository repository = sqlSession.getMapper(ITaskRepository.class);
            models.forEach(repository::update);
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
    public void clear(@Nullable final String userId) throws UserIdEmptyException {
        if (userId == null || userId.isEmpty()) throw new UserIdEmptyException();
        @NotNull final SqlSession sqlSession = connectionService.getSqlSession();
        @Nullable final TaskDTO result;
        try {
            @NotNull final ITaskRepository repository = sqlSession.getMapper(ITaskRepository.class);
            repository.clear(userId);
            sqlSession.commit();
        } catch (@NotNull final Exception e) {
            sqlSession.rollback();
            throw e;
        } finally {
            sqlSession.close();
        }
    }

    @Override
    @SneakyThrows
    public boolean existsById(@Nullable final String userId, @Nullable final String id) throws UserIdEmptyException {
        if (userId == null || userId.isEmpty()) throw new UserIdEmptyException();
        if (id == null || id.isEmpty()) return false;
        try (@NotNull final SqlSession sqlSession = connectionService.getSqlSession()) {
            @NotNull final ITaskRepository repository = sqlSession.getMapper(ITaskRepository.class);
            return repository.findById(userId, id) != null;
        }
    }

    @Nullable
    @Override
    @SneakyThrows
    public TaskDTO remove(@NotNull final String userId, @Nullable final TaskDTO model) throws UserIdEmptyException {
        if (userId.isEmpty()) throw new UserIdEmptyException();
        @NotNull final SqlSession sqlSession = connectionService.getSqlSession();
        try {
            @NotNull final ITaskRepository repository = sqlSession.getMapper(ITaskRepository.class);
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

    @Nullable
    @Override
    @SneakyThrows
    public TaskDTO findById(@Nullable final String userId, @Nullable final String id)
            throws AbstractException {
        if (userId == null || userId.isEmpty()) throw new UserIdEmptyException();
        if (id == null || id.isEmpty()) throw new IdEmptyException();
        try (@NotNull final SqlSession sqlSession = connectionService.getSqlSession()) {
            @NotNull final ITaskRepository repository = sqlSession.getMapper(ITaskRepository.class);
            return repository.findById(userId, id);
        }
    }

    @Nullable
    @Override
    @SneakyThrows
    public TaskDTO findByIndex(@Nullable final String userId, @Nullable final Integer index)
            throws AbstractException {
        if (userId == null || userId.isEmpty()) throw new UserIdEmptyException();
        if (index == null || index < 0) throw new IndexIncorrectException();
        try (@NotNull final SqlSession sqlSession = connectionService.getSqlSession()) {
            @NotNull final ITaskRepository repository = sqlSession.getMapper(ITaskRepository.class);
            return repository.findByIndex(userId, index);
        }
    }

    @NotNull
    @Override
    @SneakyThrows
    public TaskDTO removeById(@Nullable final String userId, @Nullable final String id) throws AbstractException {
        if (userId == null || userId.isEmpty()) throw new UserIdEmptyException();
        if (id == null || id.isEmpty()) throw new IdEmptyException();
        @NotNull final SqlSession sqlSession = connectionService.getSqlSession();
        @Nullable final TaskDTO result;
        try {
            @NotNull final ITaskRepository repository = sqlSession.getMapper(ITaskRepository.class);
            result = repository.findById(userId, id);
            remove(userId, result);
            sqlSession.commit();
        } catch (@NotNull final Exception e) {
            sqlSession.rollback();
            throw e;
        } finally {
            sqlSession.close();
        }
        return result;
    }

    @NotNull
    @Override
    @SneakyThrows
    public TaskDTO removeByIndex(@Nullable final String userId, @Nullable final Integer index) throws AbstractException {
        if (userId == null || userId.isEmpty()) throw new UserIdEmptyException();
        if (index == null || index < 0) throw new IndexIncorrectException();
        @NotNull final SqlSession sqlSession = connectionService.getSqlSession();
        @Nullable final TaskDTO result;
        try {
            @NotNull final ITaskRepository repository = sqlSession.getMapper(ITaskRepository.class);
            result = repository.findByIndex(userId, index);
            result.setUserId(userId);
            repository.remove(result);
            sqlSession.commit();
        } catch (@NotNull final Exception e) {
            sqlSession.rollback();
            throw e;
        } finally {
            sqlSession.close();
        }
        return result;
    }

    @Override
    @SneakyThrows
    public void removeAll(@Nullable final String userId) throws UserIdEmptyException {
        if (userId == null || userId.isEmpty()) throw new UserIdEmptyException();
        @NotNull final SqlSession sqlSession = connectionService.getSqlSession();
        try {
            @NotNull final ITaskRepository repository = sqlSession.getMapper(ITaskRepository.class);
            repository.clear(userId);
            sqlSession.commit();
        } catch (@NotNull final Exception e) {
            sqlSession.rollback();
            throw e;
        } finally {
            sqlSession.close();
        }
    }

    @NotNull
    @Override
    public List<TaskDTO> findAllByProjectId(@Nullable final String userId, @Nullable final String projectId)
            throws Exception {
        if (userId == null || userId.isEmpty()) throw new UserIdEmptyException();
        if (projectId == null || projectId.isEmpty()) return Collections.emptyList();
        try (@NotNull final SqlSession sqlSession = connectionService.getSqlSession()) {
            @NotNull final ITaskRepository repository = sqlSession.getMapper(ITaskRepository.class);
            return repository.findAllByProjectId(userId, projectId);
        }
    }

    @NotNull
    @Override
    public TaskDTO create(@Nullable final String userId, @Nullable final String name, @Nullable final String description)
            throws Exception {
        if (userId == null || userId.isEmpty()) throw new UserIdEmptyException();
        if (name == null || name.isEmpty()) throw new NameEmptyException();
        if (description == null || description.isEmpty()) throw new DescriptionEmptyException();
        @NotNull final SqlSession sqlSession = connectionService.getSqlSession();
        @Nullable final TaskDTO task;
        try {
            @NotNull final ITaskRepository repository = sqlSession.getMapper(ITaskRepository.class);
            task = new TaskDTO();
            task.setUserId(userId);
            task.setName(name);
            task.setDescription(description);
            repository.add(task);
            sqlSession.commit();
        } catch (@NotNull final Exception e) {
            sqlSession.rollback();
            throw e;
        } finally {
            sqlSession.close();
        }
        return task;
    }

    @NotNull
    @Override
    public TaskDTO create(@Nullable final String userId, @Nullable final String name) throws Exception {
        if (userId == null || userId.isEmpty()) throw new UserIdEmptyException();
        if (name == null || name.isEmpty()) throw new NameEmptyException();
        @NotNull final SqlSession sqlSession = connectionService.getSqlSession();
        @Nullable final TaskDTO task;
        try {
            @NotNull final ITaskRepository repository = sqlSession.getMapper(ITaskRepository.class);
            task = new TaskDTO();
            task.setUserId(userId);
            task.setName(name);
            repository.add(task);
            sqlSession.commit();
        } catch (@NotNull final Exception e) {
            sqlSession.rollback();
            throw e;
        } finally {
            sqlSession.close();
        }
        return task;
    }

    @Override
    @Nullable
    public TaskDTO updateById(
            @Nullable final String userId,
            @Nullable final String id,
            @Nullable final String name,
            @Nullable final String description
    )
            throws Exception {
        if (userId == null || userId.isEmpty()) throw new UserIdEmptyException();
        if (id == null || id.isEmpty()) throw new IdEmptyException();
        if (name == null || name.isEmpty()) throw new NameEmptyException();
        if (description == null || description.isEmpty()) throw new DescriptionEmptyException();
        @NotNull final SqlSession sqlSession = connectionService.getSqlSession();
        @Nullable final TaskDTO task;
        try {
            @NotNull final ITaskRepository repository = sqlSession.getMapper(ITaskRepository.class);
            task = repository.findById(userId, id);
            if (task == null) throw new TaskIdEmptyException();
            task.setName(name);
            task.setDescription(description);
            task.setUserId(userId);
            sqlSession.commit();
        } catch (@NotNull final Exception e) {
            sqlSession.rollback();
            throw e;
        } finally {
            sqlSession.close();
        }
        return task;
    }

    @Override
    @Nullable
    public TaskDTO updateByIndex(
            @Nullable final String userId,
            @Nullable final Integer index,
            @Nullable final String name,
            @Nullable final String description
    )
            throws Exception {
        if (userId == null || userId.isEmpty()) throw new UserIdEmptyException();
        if (index == null || index < 0) throw new IndexIncorrectException();
        if (name == null || name.isEmpty()) throw new NameEmptyException();
        if (description == null || description.isEmpty()) throw new DescriptionEmptyException();
        @NotNull final SqlSession sqlSession = connectionService.getSqlSession();
        @Nullable final TaskDTO task;
        try {
            @NotNull final ITaskRepository repository = sqlSession.getMapper(ITaskRepository.class);
            task = repository.findByIndex(userId, index);
            if (task == null) throw new TaskIdEmptyException();
            task.setName(name);
            task.setDescription(description);
            task.setUserId(userId);
            sqlSession.commit();
        } catch (@NotNull final Exception e) {
            sqlSession.rollback();
            throw e;
        } finally {
            sqlSession.close();
        }
        return task;
    }

    @Override
    @Nullable
    public TaskDTO changeTaskStatusById(
            @Nullable final String userId,
            @Nullable final String id,
            @Nullable final Status status
    )
            throws Exception {
        if (userId == null || userId.isEmpty()) throw new UserIdEmptyException();
        if (id == null || id.isEmpty()) throw new IdEmptyException();
        @NotNull final SqlSession sqlSession = connectionService.getSqlSession();
        @Nullable final TaskDTO task;
        try {
            @NotNull final ITaskRepository repository = sqlSession.getMapper(ITaskRepository.class);
            task = findById(userId, id);
            if (task == null) throw new TaskIdEmptyException();
            task.setStatus(status);
            task.setUserId(userId);
            sqlSession.commit();
        } catch (@NotNull final Exception e) {
            sqlSession.rollback();
            throw e;
        } finally {
            sqlSession.close();
        }
        return task;
    }

    @Override
    @Nullable
    public TaskDTO changeTaskStatusByIndex(
            @Nullable final String userId,
            @Nullable final Integer index,
            @NotNull final Status status
    ) throws Exception {
        if (userId == null || userId.isEmpty()) throw new UserIdEmptyException();
        if (index == null || index < 0) throw new IndexIncorrectException();
        @NotNull final SqlSession sqlSession = connectionService.getSqlSession();
        @Nullable final TaskDTO task;
        try {
            @NotNull final ITaskRepository repository = sqlSession.getMapper(ITaskRepository.class);
            task = findByIndex(userId, index);
            if (task == null) throw new TaskIdEmptyException();
            task.setStatus(status);
            task.setUserId(userId);
            sqlSession.commit();
        } catch (@NotNull final Exception e) {
            sqlSession.rollback();
            throw e;
        } finally {
            sqlSession.close();
        }
        return task;
    }

    @Override
    public void updateProjectIdById(@NotNull String userId, @Nullable String taskId, @Nullable String projectId) throws Exception {
        if (userId == null || userId.isEmpty()) throw new UserIdEmptyException();
        if (taskId == null || taskId.isEmpty()) throw new TaskIdEmptyException();
        if (projectId == null || projectId.isEmpty()) throw new ProjectIdEmptyException();

        @NotNull final SqlSession sqlSession = connectionService.getSqlSession();
        @Nullable final TaskDTO task;
        try {
            @NotNull final ITaskRepository repository = sqlSession.getMapper(ITaskRepository.class);
            task = repository.findTaskIdByProjectId(userId, taskId, projectId);
            if (task == null) throw new TaskIdEmptyException();
            task.setProjectId(projectId);
            sqlSession.commit();
        } catch (@NotNull final Exception e) {
            sqlSession.rollback();
            throw e;
        } finally {
            sqlSession.close();
        }
    }

}
