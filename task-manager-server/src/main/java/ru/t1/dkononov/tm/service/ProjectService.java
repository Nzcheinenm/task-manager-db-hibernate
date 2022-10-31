package ru.t1.dkononov.tm.service;

import lombok.SneakyThrows;
import org.apache.ibatis.session.SqlSession;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.t1.dkononov.tm.api.repository.IProjectRepository;
import ru.t1.dkononov.tm.api.services.IConnectionService;
import ru.t1.dkononov.tm.api.services.IProjectService;
import ru.t1.dkononov.tm.enumerated.Sort;
import ru.t1.dkononov.tm.enumerated.Status;
import ru.t1.dkononov.tm.exception.AbstractException;
import ru.t1.dkononov.tm.exception.entity.ProjectNotFoundException;
import ru.t1.dkononov.tm.exception.field.*;
import ru.t1.dkononov.tm.dto.model.ProjectDTO;

import java.sql.SQLException;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;


public final class ProjectService implements IProjectService {

    @NotNull
    private final IConnectionService connectionService;

    public ProjectService(@NotNull final IConnectionService connectionService) {
        this.connectionService = connectionService;
    }


    @NotNull
    @Override
    @SneakyThrows
    public List<ProjectDTO> findAll(@Nullable final String userId) throws UserIdEmptyException {
        if (userId == null || userId.isEmpty()) throw new UserIdEmptyException();
        try (@NotNull final SqlSession sqlSession = connectionService.getSqlSession()) {
            @NotNull final IProjectRepository repository = sqlSession.getMapper(IProjectRepository.class);
            return repository.findAllWithUserId(userId);
        }
    }

    @NotNull
    @Override
    @SneakyThrows
    public List<ProjectDTO> findAll() {
        try (@NotNull final SqlSession sqlSession = connectionService.getSqlSession()) {
            @NotNull final IProjectRepository repository = sqlSession.getMapper(IProjectRepository.class);
            return repository.findAll();
        }
    }

    @Nullable
    @Override
    @SneakyThrows
    public List<ProjectDTO> findAll(
            @Nullable final String userId,
            @Nullable final Comparator comparator
    ) throws UserIdEmptyException {
        if (userId == null || userId.isEmpty()) throw new UserIdEmptyException();
        try (@NotNull final SqlSession sqlSession = connectionService.getSqlSession()) {
            @NotNull final IProjectRepository repository = sqlSession.getMapper(IProjectRepository.class);
            if (comparator == null) return findAll(userId);
            return repository.findAllWithUserId(userId);
        }
    }

    @NotNull
    @Override
    @SneakyThrows
    public List<ProjectDTO> findAll(
            @Nullable final String userId,
            @Nullable final Sort sort
    ) throws UserIdEmptyException {
        if (userId == null || userId.isEmpty()) throw new UserIdEmptyException();
        try (@NotNull final SqlSession sqlSession = connectionService.getSqlSession()) {
            if (sort == null) return findAll(userId);
            return findAll(userId, sort.getComparator());
        }
    }

    @Nullable
    @Override
    @SneakyThrows
    public ProjectDTO add(@Nullable final String userId, @Nullable final ProjectDTO model)
            throws ProjectNotFoundException, UserIdEmptyException {
        if (userId == null || userId.isEmpty()) throw new UserIdEmptyException();
        if (model == null) throw new ProjectNotFoundException();
        @NotNull final SqlSession sqlSession = connectionService.getSqlSession();
        @Nullable final ProjectDTO result;
        try {
            @NotNull final IProjectRepository repository = sqlSession.getMapper(IProjectRepository.class);
            model.setUserId(userId);
            repository.add(model);
            sqlSession.commit();
            result = findById(userId, model.getId());
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
    public Collection<ProjectDTO> add(@NotNull Collection<ProjectDTO> models) {
        if (models.isEmpty()) return Collections.emptyList();
        @NotNull final SqlSession sqlSession = connectionService.getSqlSession();
        try {
            @NotNull final IProjectRepository repository = sqlSession.getMapper(IProjectRepository.class);
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
    public Collection<ProjectDTO> set(@NotNull Collection<ProjectDTO> models) {
        if (models.isEmpty()) return Collections.emptyList();
        @NotNull final SqlSession sqlSession = connectionService.getSqlSession();
        try {
            @NotNull final IProjectRepository repository = sqlSession.getMapper(IProjectRepository.class);
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
        @Nullable final ProjectDTO result;
        try {
            @NotNull final IProjectRepository repository = sqlSession.getMapper(IProjectRepository.class);
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
            @NotNull final IProjectRepository repository = sqlSession.getMapper(IProjectRepository.class);
            return repository.findByIdWithUserId(userId, id) != null;
        }
    }

    @Nullable
    @Override
    @SneakyThrows
    public ProjectDTO remove(@NotNull final String userId, @Nullable final ProjectDTO model) throws UserIdEmptyException {
        if (userId.isEmpty()) throw new UserIdEmptyException();
        @NotNull final SqlSession sqlSession = connectionService.getSqlSession();
        try {
            @NotNull final IProjectRepository repository = sqlSession.getMapper(IProjectRepository.class);
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
    public ProjectDTO findById(@Nullable final String userId, @Nullable final String id)
            throws AbstractException {
        if (userId == null || userId.isEmpty()) throw new UserIdEmptyException();
        if (id == null || id.isEmpty()) throw new IdEmptyException();
        try (@NotNull final SqlSession sqlSession = connectionService.getSqlSession()) {
            @NotNull final IProjectRepository repository = sqlSession.getMapper(IProjectRepository.class);
            return repository.findByIdWithUserId(userId, id);
        }
    }

    @Nullable
    @Override
    @SneakyThrows
    public ProjectDTO findByIndex(@Nullable final String userId, @Nullable final Integer index)
            throws AbstractException {
        if (userId == null || userId.isEmpty()) throw new UserIdEmptyException();
        if (index == null || index < 0) throw new IndexIncorrectException();
        try (@NotNull final SqlSession sqlSession = connectionService.getSqlSession()) {
            @NotNull final IProjectRepository repository = sqlSession.getMapper(IProjectRepository.class);
            return repository.findByIndex(userId, index);
        }
    }

    @NotNull
    @Override
    @SneakyThrows
    public ProjectDTO removeById(@Nullable final String userId, @Nullable final String id) throws AbstractException {
        if (userId == null || userId.isEmpty()) throw new UserIdEmptyException();
        if (id == null || id.isEmpty()) throw new IdEmptyException();
        @NotNull final SqlSession sqlSession = connectionService.getSqlSession();
        @Nullable final ProjectDTO result;
        try {
            @NotNull final IProjectRepository repository = sqlSession.getMapper(IProjectRepository.class);
            result = repository.findByIdWithUserId(userId, id);
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
    public ProjectDTO removeByIndex(@Nullable final String userId, @Nullable final Integer index) throws AbstractException {
        if (userId == null || userId.isEmpty()) throw new UserIdEmptyException();
        if (index == null || index < 0) throw new IndexIncorrectException();
        @NotNull final SqlSession sqlSession = connectionService.getSqlSession();
        @Nullable final ProjectDTO result;
        try {
            @NotNull final IProjectRepository repository = sqlSession.getMapper(IProjectRepository.class);
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
            @NotNull final IProjectRepository repository = sqlSession.getMapper(IProjectRepository.class);
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
    @SneakyThrows
    public ProjectDTO create(
            @Nullable final String userId,
            @Nullable final String name,
            @Nullable final String description
    )
            throws AbstractFieldException {
        if (userId == null || userId.isEmpty()) throw new UserIdEmptyException();
        if (name == null || name.isEmpty()) throw new NameEmptyException();
        if (description == null || description.isEmpty()) throw new DescriptionEmptyException();
        @NotNull final SqlSession sqlSession = connectionService.getSqlSession();
        @Nullable final ProjectDTO result;
        try {
            @NotNull final IProjectRepository repository = sqlSession.getMapper(IProjectRepository.class);
            result = new ProjectDTO();
            result.setUserId(userId);
            result.setName(name);
            result.setDescription(description);
            repository.add(result);
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
    public ProjectDTO create(@Nullable final String userId, @Nullable final String name)
            throws AbstractFieldException, SQLException, ProjectNotFoundException {
        if (userId == null || userId.isEmpty()) throw new UserIdEmptyException();
        if (name == null || name.isEmpty()) throw new NameEmptyException();
        @NotNull ProjectDTO project = new ProjectDTO();
        project.setUserId(userId);
        project.setName(name);
        @NotNull final SqlSession sqlSession = connectionService.getSqlSession();
        try {
            @NotNull final IProjectRepository repository = sqlSession.getMapper(IProjectRepository.class);
            project.setUserId(userId);
            repository.add(project);
            sqlSession.commit();
        } catch (@NotNull final Exception e) {
            sqlSession.rollback();
            throw e;
        } finally {
            sqlSession.close();
        }
        return project;
    }

    @Override
    @Nullable
    @SneakyThrows
    public ProjectDTO updateById(
            @Nullable final String userId,
            @Nullable final String id,
            @Nullable final String name,
            @Nullable final String description
    )
            throws AbstractException {
        if (userId == null || userId.isEmpty()) throw new UserIdEmptyException();
        if (id == null || id.isEmpty()) throw new IdEmptyException();
        if (name == null || name.isEmpty()) throw new NameEmptyException();
        if (description == null || description.isEmpty()) throw new DescriptionEmptyException();
        @NotNull final SqlSession sqlSession = connectionService.getSqlSession();
        @Nullable final ProjectDTO project;
        try {
            @NotNull final IProjectRepository repository = sqlSession.getMapper(IProjectRepository.class);
            project = repository.findByIdWithUserId(userId, id);
            if (project == null) throw new ProjectNotFoundException();
            project.setName(name);
            project.setDescription(description);
            project.setUserId(userId);
            sqlSession.commit();
        } catch (@NotNull final Exception e) {
            sqlSession.rollback();
            throw e;
        } finally {
            sqlSession.close();
        }
        return project;
    }

    @Override
    @Nullable
    @SneakyThrows
    public ProjectDTO updateByIndex(
            @Nullable final String userId,
            @Nullable final Integer index,
            @Nullable final String name,
            @Nullable final String description
    )
            throws AbstractException {
        if (userId == null || userId.isEmpty()) throw new UserIdEmptyException();
        if (index == null || index < 0) throw new IndexIncorrectException();
        if (name == null || name.isEmpty()) throw new NameEmptyException();
        if (description == null || description.isEmpty()) throw new DescriptionEmptyException();
        @NotNull final SqlSession sqlSession = connectionService.getSqlSession();
        @Nullable final ProjectDTO project;
        try {
            @NotNull final IProjectRepository repository = sqlSession.getMapper(IProjectRepository.class);
            project = repository.findByIndex(userId, index);
            if (project == null) throw new ProjectNotFoundException();
            project.setName(name);
            project.setDescription(description);
            sqlSession.commit();
        } catch (@NotNull final Exception e) {
            sqlSession.rollback();
            throw e;
        } finally {
            sqlSession.close();
        }
        return project;
    }

    @Override
    @Nullable
    @SneakyThrows
    public ProjectDTO changeProjectStatusById(
            @Nullable final String userId,
            @Nullable final String id,
            @NotNull final Status status
    )
            throws AbstractException {
        if (userId == null || userId.isEmpty()) throw new UserIdEmptyException();
        if (id == null || id.isEmpty()) throw new IdEmptyException();
        @NotNull final SqlSession sqlSession = connectionService.getSqlSession();
        @Nullable final ProjectDTO project;
        try {
            @NotNull final IProjectRepository repository = sqlSession.getMapper(IProjectRepository.class);
            project = findById(userId, id);
            if (project == null) throw new ProjectNotFoundException();
            project.setStatus(status);
            project.setUserId(userId);
            sqlSession.commit();
        } catch (@NotNull final Exception e) {
            sqlSession.rollback();
            throw e;
        } finally {
            sqlSession.close();
        }
        return project;
    }

    @Override
    @Nullable
    @SneakyThrows
    public ProjectDTO changeProjectStatusByIndex(
            @Nullable final String userId,
            @Nullable final Integer index,
            @NotNull final Status status
    )
            throws AbstractException {
        if (userId == null || userId.isEmpty()) throw new UserIdEmptyException();
        if (index == null || index < 0) throw new IndexIncorrectException();
        @NotNull final SqlSession sqlSession = connectionService.getSqlSession();
        @Nullable final ProjectDTO project;
        try {
            @NotNull final IProjectRepository repository = sqlSession.getMapper(IProjectRepository.class);
            project = repository.findByIndex(userId, index);
            if (project == null) throw new ProjectNotFoundException();
            project.setStatus(status);
            project.setUserId(userId);
            sqlSession.commit();
        } catch (@NotNull final Exception e) {
            sqlSession.rollback();
            throw e;
        } finally {
            sqlSession.close();
        }
        return project;
    }

}
