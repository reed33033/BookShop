package com.mypack.dao;

import com.mypack.domain.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * 用户的数据库操作层
 */
public interface UserDao {

    /**
     * 根据用户名和密码查询管理员账户
     *
     * @param username 用户名
     * @param password 密码
     * @return 查询到的用户，如果不存在则值为null
     */
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


    /**
     * 根据用户名和密码查询用户
     *
     * @param username 用户名
     * @param password 密码
     * @return 查询到的用户，如果不存在则值为null
     */
    @Select("select * from user where username=#{username} and password=#{password}")
    public User findByUsernameAndPassword(@Param("username") String username, @Param("password") String password);

    /**
     * 通过用户编号查询用户
     *
     * @param uid 用户id
     * @return 查询到的用户，如果不存在则值为null
     */
    @Select("select * from user where uid=#{uid}")
    User findByUid(String uid);

    /**
     * 根据用户名查询用户
     *
     * @param username 用户名
     * @return 查询到的用户，如果不存在则值为null
     */
    @Select("select * from user where username=#{username}")
    User findByUserName(String username);

    /**
     * 根据电话号码查询用户
     *
     * @param phone 电话号码
     * @return 查询到的用户，如果不存在则值为null
     */
    @Select("select * from user where phone=#{phone}")
    User findByPhone(String phone);

    /**
     * 查询所有用户
     *
     * @return 查询到的用户集合
     * @throws Exception
     */
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


    /**
     * 修改用户角色
     *
     * @param uid     用户id
     * @param manager 用户角色，0代表管理员， 代表普通用户
     * @return 结果>0表示修改成功，否则修改失败
     */
    @Update("update user set manager=#{manager} where uid=#{uid}")
    int updateManager(@Param("uid") String uid, @Param("manager") Integer manager);

    /**
     * 修改用户密码
     *
     * @param password 用户密码
     * @param uid      用户id
     * @return 结果>0表示修改成功，否则修改失败
     */
    @Update("update user set password=#{password} where uid=#{uid}")
    int updatePassword(@Param("password") String password, @Param("uid") String uid);

    /**
     * 修改用户地址
     *
     * @param area 地址
     * @param uid  用户id
     * @return 结果>0表示修改成功，否则修改失败
     */
    @Update("update user set area=#{area} where uid=#{uid}")
    int updateArea(@Param("area") String area, @Param("uid") String uid);

    /**
     * 根据用户id删除用户
     *
     * @param array 要删除的用户id
     * @return 结果>0表示修改成功，否则修改失败
     * @throws Exception
     */
    @Delete(" <script>" + "delete from user where uid in <foreach collection='array' item='item' open='(' close=')' separator=',' >#{item}</foreach>" + "</script>")
    int deleteByUid(@Param("array") String[] array) throws Exception;

    /**
     * 添加用户
     *
     * @param user 要添加的用户信息
     * @return 结果>0表示修改成功，否则修改失败
     * @throws Exception
     */
    @Insert("insert into user(uid,uname, gender, phone, area, username, password, photo, create_time) values(#{uid},#{uname},#{gender},#{phone},#{area},#{username},#{password},#{photo},#{create_time})")
    int save(User user) throws Exception;


}
