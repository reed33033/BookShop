package com.mypack.service;

import com.mypack.domain.Category;
import com.mypack.domain.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 用户业务层接口
 */
public interface UserService {
    /**
     * 根据用户名和密码查询管理员账户
     *
     * @param username 用户名
     * @param password 密码
     * @return 查询到的用户，如果不存在则值为null
     */
    public User adminLogin(String username, String password);

    /**
     * 根据用户名和密码查询用户
     *
     * @param username 用户名
     * @param password 密码
     * @return 查询到的用户，如果不存在则值为null
     */
    public User userLogin(String username, String password);

    /**
     * 查询所有用户
     *
     * @return 查询到的用户集合
     * @throws Exception
     */
    List<User> findAll() throws Exception;

    /**
     * 分页查询所有用户
     *
     * @param page 分页值
     * @param size 每页的条数
     * @return 查询到的用户集合
     * @throws Exception
     */
    List<User> findAll(int page, int size) throws Exception;

    /**
     * 修改用户角色
     *
     * @param uid     用户id
     * @param manager 用户角色，0代表管理员， 代表普通用户
     * @return 结果>0表示修改成功，否则修改失败
     */
    boolean updateManager(Integer manager, String uid);

    /**
     * 通过用户编号查询用户
     *
     * @param uid 用户id
     * @return 查询到的用户，如果不存在则值为null
     */
    User findByUid(String uid);

    /**
     * 根据用户id删除用户
     *
     * @param uids 要删除的用户id
     * @throws Exception
     */
    void deleteByUid(String[] uids) throws Exception;

    /**
     * 根据电话号码查询用户
     *
     * @param phone 电话号码
     * @return 查询到的用户，如果不存在则值为null
     */
    User findByPhone(String phone);

    /**
     * 根据用户名查询用户
     *
     * @param username 用户名
     * @return 查询到的用户，如果不存在则值为null
     */
    User findByUserName(String username);

    /**
     * 添加用户
     *
     * @param user 要添加的用户信息
     * @return 结果>0表示修改成功，否则修改失败
     * @throws Exception
     */
    int save(User user) throws Exception;

    /**
     * 修改用户密码
     *
     * @param password 用户密码
     * @param uid      用户id
     * @return 结果>0表示修改成功，否则修改失败
     */
    int updatePassword(String password, String uid);

    /**
     * 修改用户地址
     *
     * @param area 地址
     * @param uid  用户id
     * @return 结果>0表示修改成功，否则修改失败
     */
    int updateArea(String area, String uid);
}
