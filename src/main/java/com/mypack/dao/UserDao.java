package com.mypack.dao;

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


    @Select("select * from user where username=#{username} and password=#{password}")
    public User findByUsernameAndPassword(@Param("username") String username, @Param("password") String password);

    @Select("select * from user where uid=#{uid}")
    User findByUid(String uid);

    @Select("select * from user where username=#{username}")
    User findByUserName(String username);

    @Select("select * from user where phone=#{phone}")
    User findByPhone(String phone);


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

    @Update("update user set password=#{password} where uid=#{uid}")
    int updatePassword(@Param("password")String password,@Param("uid")String uid);

    @Update("update user set area=#{area} where uid=#{uid}")
    int updateArea(@Param("area")String area,@Param("uid")String uid);

    @Delete(" <script>" +"delete from user where uid in <foreach collection='array' item='item' open='(' close=')' separator=',' >#{item}</foreach>"+"</script>")
    int deleteByUid(@Param("array") String[] array) throws Exception;

    @Insert("insert into user(uid,uname, gender, phone, area, username, password, photo, create_time) values(#{uid},#{uname},#{gender},#{phone},#{area},#{username},#{password},#{photo},#{create_time})")
    int save(User user) throws Exception;


}
