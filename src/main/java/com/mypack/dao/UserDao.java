package com.mypack.dao;

import com.mypack.domain.Category;
import com.mypack.domain.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface UserDao {

    @Select("select * from user where username=#{username} and password=#{password} and manager=0")
    @Results({
            @Result(id = true, property = "uid", column = "uid"),
            @Result(property = "uname", column = "uname"),
            @Result(property = "gender", column = "gender"),
            @Result(property = "phone", column = "phone"),
            @Result(property = "area", column = "area"),
            @Result(property = "manager", column = "manager"),
            @Result(property = "username", column = "username"),
            @Result(property = "password", column = "password"),
            @Result(property = "photo", column = "photo"),
            @Result(property = "create_time", column = "create_time")
    })
    public User findByUsernameAndPasswordAndManager(@Param("username") String username, @Param("password") String password);

    @Select("select * from user where uid=#{uid}")
    User findByUid(String uid);

    @Select("select * from user")
    @Results({
            @Result(id = true, property = "uid", column = "uid"),
            @Result(property = "uname", column = "uname"),
            @Result(property = "gender", column = "gender"),
            @Result(property = "phone", column = "phone"),
            @Result(property = "area", column = "area"),
            @Result(property = "manager", column = "manager"),
            @Result(property = "username", column = "username"),
            @Result(property = "password", column = "password"),
            @Result(property = "photo", column = "photo"),
            @Result(property = "create_time", column = "create_time")
    })
    List<User> findAll() throws Exception;


    @Update("update user set manager=#{manager} where uid=#{uid}")
    int updateManager(@Param("uid") String uid,@Param("manager") Integer manager);

    @Delete(" <script>" +"delete from user where uid in <foreach collection='array' item='item' open='(' close=')' separator=',' >#{item}</foreach>"+"</script>")
    void deleteByUid(@Param("array") String[] array) throws Exception;


}
