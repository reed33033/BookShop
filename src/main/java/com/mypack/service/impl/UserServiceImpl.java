package com.mypack.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.mypack.dao.UserDao;
import com.mypack.domain.Category;
import com.mypack.domain.User;
import com.mypack.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 用户业务层接口实现类
 */
@Service("userService")
@Transactional
public class UserServiceImpl implements UserService {

    /**
     * 注入的用户数据库操作层
     */
    @Autowired
    private UserDao userDao;

    /**
     * 根据用户名和密码查询管理员账户
     *
     * @param username 用户名
     * @param password 密码
     * @return 查询到的用户，如果不存在则值为null
     */
    public User adminLogin(String username, String password) {
        User user = null;
        user = userDao.findByUsernameAndPasswordAndManager(username, password);
        return user;
    }

    /**
     * 根据用户名和密码查询用户
     *
     * @param username 用户名
     * @param password 密码
     * @return 查询到的用户，如果不存在则值为null
     */
    @Override
    public User userLogin(String username, String password) {
        return userDao.findByUsernameAndPassword(username, password);
    }

    /**
     * 查询所有用户
     *
     * @return 查询到的用户集合
     * @throws Exception
     */
    public List<User> findAll() throws Exception {
        List<User> userList = userDao.findAll();
        return userList;
    }

    /**
     * 分页查询所有用户
     *
     * @param page 分页值
     * @param size 每页的条数
     * @return 查询到的用户集合
     * @throws Exception
     */
    public List<User> findAll(int page, int size) throws Exception {
        //参数pageNum 是页码值   参数pageSize 代表是每页显示条数
        Page page1 = PageHelper.startPage(page, size);
        int total = (int) page1.getTotal();
        page1.setTotal(total);
        return userDao.findAll();
    }

    /**
     * 修改用户角色
     *
     * @param uid     用户id
     * @param manager 用户角色，0代表管理员， 代表普通用户
     * @return 结果>0表示修改成功，否则修改失败
     */
    public boolean updateManager(Integer manager, String uid) {
        int row = userDao.updateManager(uid, manager);
        if (row > 0) {
            return true;
        }
        return false;
    }

    /**
     * 通过用户编号查询用户
     *
     * @param uid 用户id
     * @return 查询到的用户，如果不存在则值为null
     */
    public User findByUid(String uid) {
        return userDao.findByUid(uid);
    }

    /**
     * 根据用户id删除用户
     *
     * @param uids 要删除的用户id
     * @throws Exception
     */
    @Override
    public void deleteByUid(String[] uids) throws Exception {
        userDao.deleteByUid(uids);
    }

    /**
     * 根据电话号码查询用户
     *
     * @param phone 电话号码
     * @return 查询到的用户，如果不存在则值为null
     */
    @Override
    public User findByPhone(String phone) {
        return userDao.findByPhone(phone);
    }

    /**
     * 根据用户名查询用户
     *
     * @param username 用户名
     * @return 查询到的用户，如果不存在则值为null
     */
    @Override
    public User findByUserName(String username) {
        return userDao.findByUserName(username);
    }

    /**
     * 添加用户
     *
     * @param user 要添加的用户信息
     * @return 结果>0表示修改成功，否则修改失败
     * @throws Exception
     */
    @Override
    public int save(User user) throws Exception {
        return userDao.save(user);
    }

    /**
     * 修改用户密码
     *
     * @param password 用户密码
     * @param uid      用户id
     * @return 结果>0表示修改成功，否则修改失败
     */
    @Override
    public int updatePassword(String password, String uid) {
        return userDao.updatePassword(password, uid);
    }

    /**
     * 修改用户地址
     *
     * @param area 地址
     * @param uid  用户id
     * @return 结果>0表示修改成功，否则修改失败
     */
    @Override
    public int updateArea(String area, String uid) {
        return userDao.updateArea(area, uid);
    }
}
