package ru.t1.dkononov.tm.api.repository;

import org.apache.ibatis.annotations.*;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.t1.dkononov.tm.model.Project;
import ru.t1.dkononov.tm.model.Task;

import java.util.List;

public interface IProjectRepository {

    @Insert("INSERT INTO tm.tm_project (id,created,name,description,status,user_id) " +
            "VALUES (#{id},#{created},#{name},#{description},#{status},#{userId})")
    void add(@NotNull Project project);

    @Delete("DELETE FROM tm.tm_project WHERE user_id = #{userId}")
    void clear(@NotNull @Param("userId") String userId);

    @Select("SELECT * FROM tm.tm_project WHERE user_id = #{userId}")
    @Results(value = {
            @Result(property = "userId", column = "user_id")
    })
    @Nullable List<Project> findAllWithUserId(@NotNull @Param("userId") String userId);

    @Select("SELECT * FROM tm.tm_project")
    @Nullable List<Project> findAll();

    @Select("SELECT * FROM tm.tm_project WHERE user_id = #{userId} ORDER BY created")
    @Results(value = {
            @Result(property = "userId", column = "user_id")
    })
    @Nullable List<Project> findAllOrderByCreated(@NotNull @Param("userId") String userId);

    @Select("SELECT * FROM tm.tm_project WHERE id = #{id} AND user_id = #{userId} LIMIT 1")
    @Results(value = {
            @Result(property = "userId", column = "user_id")
    })
    @Nullable Project findByIdWithUserId(@NotNull @Param("userId") String userId, @NotNull @Param("id") String id);

    @Select("SELECT * FROM tm.tm_project WHERE id = #{id} LIMIT 1")
    @Nullable Project findById( @NotNull @Param("id") String id);

    @Select("SELECT * FROM tm.tm_project WHERE user_id = #{userId} LIMIT 1 OFFSET #{index}")
    @Results(value = {
            @Result(property = "userId", column = "user_id")
    })
    @Nullable Project findByIndex(@NotNull @Param("userId") String userId, @NotNull @Param("index") Integer index);

    @Delete("DELETE FROM tm.tm_project WHERE user_id = #{userId} AND id = #{id}")
    void remove(@NotNull Project project);

    @Update("UPDATE tm.tm_project SET name = #{name}, created = #{created}, name = #{name}," +
            "description = #{description}, status = #{status}, user_id = #{userId} WHERE id = #{id}")
    Project update(@NotNull Project project);

}
