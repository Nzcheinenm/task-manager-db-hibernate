package ru.t1.dkononov.tm.api.repository;

import org.apache.ibatis.annotations.*;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.t1.dkononov.tm.model.User;

import java.util.List;

public interface IUserRepository {

    @Insert("INSERT INTO tm.tm_user (id,login,password,first_name,middle_name,last_name,email,role,locked) " +
            "VALUES (#{id},#{login},#{passwordHash},#{firstName},#{middleName},#{lastName},#{email},#{role},#{locked})")
    void add(@NotNull User user);

    @Delete("DELETE FROM tm.tm_user")
    void clear();

    @Select("SELECT * FROM tm.tm_user")
    @Nullable List<User> findAll();

    @Select("SELECT * FROM tm.tm_user ORDER BY created")
    @Nullable List<User> findAllOrderByCreated(@NotNull @Param("userId") String userId);

    @Select("SELECT * FROM tm.tm_user WHERE id = #{id} LIMIT 1")
    @Results(value = {
            @Result(property = "passwordHash", column = "password"),
            @Result(property = "firstName", column = "first_name"),
            @Result(property = "lastName", column = "last_name"),
            @Result(property = "middleName", column = "middle_name")
    })
    @Nullable User findById(@NotNull @Param("id") String id);

    @Select("SELECT * FROM tm.tm_user WHERE login = #{login} LIMIT 1")
    @Results(value = {
            @Result(property = "passwordHash", column = "password"),
            @Result(property = "firstName", column = "first_name"),
            @Result(property = "lastName", column = "last_name"),
            @Result(property = "middleName", column = "middle_name")

    })
    @Nullable User findByLogin(@NotNull String login);

    @Select("SELECT * FROM tm.tm_user WHERE email = #{email} LIMIT 1")
    @Results(value = {
            @Result(property = "passwordHash", column = "password"),
            @Result(property = "firstName", column = "first_name"),
            @Result(property = "lastName", column = "last_name"),
            @Result(property = "middleName", column = "middle_name"),
            @Result(property = "email", column = "email")
    })
    @Nullable User findByEmail(@NotNull String email);

    @Delete("DELETE FROM tm.tm_user WHERE id = #{id}")
    void remove(@NotNull User user);

    @Update("UPDATE tm.tm_user SET login = #{login}, password = #{passwordHash}, first_name = #{firstName}," +
            "middle_name = #{middleName}, last_name = #{lastName}, email = #{email}, role = #{role}, locked = #{locked} WHERE id = #{id}")
    User update(@NotNull User user);

}
