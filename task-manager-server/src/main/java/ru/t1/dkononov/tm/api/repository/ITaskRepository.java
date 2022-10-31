package ru.t1.dkononov.tm.api.repository;

import org.apache.ibatis.annotations.*;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.t1.dkononov.tm.dto.model.TaskDTO;

import java.util.List;

public interface ITaskRepository {

    @Insert("INSERT INTO tm.tm_task (id,created,name,description,status,user_id,project_id) " +
            "VALUES (#{id},#{created},#{name},#{description},#{status},#{userId},#{projectId})")
    void add(@NotNull TaskDTO task);

    @Delete("DELETE FROM tm.tm_task WHERE user_id = #{userId}")
    void clear(@NotNull @Param("userId") String userId);

    @Select("SELECT * FROM tm.tm_task WHERE user_id = #{userId}")
    @Results(value = {
            @Result(property = "userId", column = "user_id")
    })
    @Nullable List<TaskDTO> findAllWithUserId(@NotNull @Param("userId") String userId);

    @Select("SELECT * FROM tm.tm_task WHERE user_id = #{userId} ORDER BY 'created'")
    @Results(value = {
            @Result(property = "userId", column = "user_id")
    })
    @Nullable List<TaskDTO> findAllOrderByCreated(@NotNull @Param("userId") String userId);

    @Select("SELECT * FROM tm.tm_task")
    @Nullable List<TaskDTO> findAll();

    @Select("SELECT * FROM tm.tm_task WHERE project_id = #{projectId} AND user_id = #{userId} LIMIT 1")
    @Results(value = {
            @Result(property = "userId", column = "user_id")
    })
    @NotNull List<TaskDTO> findAllByProjectId(@NotNull String userId, @NotNull String projectId);

    @Select("SELECT * FROM tm.tm_task WHERE id = #{taskId} AND user_id = #{userId} AND project_id = #{projectId} LIMIT 1")
    @Results(value = {
            @Result(property = "userId", column = "user_id")
    })
    @NotNull TaskDTO findTaskIdByProjectId(@NotNull String userId, @NotNull String projectId, @NotNull String taskId);

    @Select("SELECT * FROM tm.tm_task WHERE id = #{id} AND user_id = #{userId} LIMIT 1")
    @Results(value = {
            @Result(property = "userId", column = "user_id")
    })
    @Nullable TaskDTO findById(@NotNull @Param("userId") String userId, @NotNull @Param("id") String id);

    @Select("SELECT * FROM tm.tm_task WHERE user_id = #{userId} LIMIT 1 OFFSET #{index}")
    @Results(value = {
            @Result(property = "userId", column = "user_id")
    })
    @Nullable TaskDTO findByIndex(@NotNull @Param("userId") String userId, @NotNull @Param("index") Integer index);

    @Delete("DELETE FROM tm.tm_task WHERE user_id = #{userId} AND id = #{id}")
    void remove(@NotNull TaskDTO task);

    @Update("UPDATE tm.tm_task SET name = #{name}, created = #{created}, name = #{name}," +
            "description = #{description}, status = #{status}, user_id = #{userId}, project_id = #{projectId} WHERE id = #{id}")
    TaskDTO update(@NotNull TaskDTO task);

}
