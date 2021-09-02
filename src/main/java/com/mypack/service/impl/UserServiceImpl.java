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

@Service("userService")
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    public User adminLogin(String username, String password) {
        User user =null;
        user = userDao.findByUsernameAndPasswordAndManager(username, password);
        return user;
    }

    @Override
    public User userLogin(String username, String password) {
        return userDao.findByUsernameAndPassword(username,password);
    }

    public List<User> findAll() throws Exception {
        List<User> userList = userDao.findAll();
        return userList;
    }

    public List<User> findAll(int page, int size) throws Exception {
        //参数pageNum 是页码值   参数pageSize 代表是每页显示条数
        Page page1=PageHelper.startPage(page, size);
        int total = (int) page1.getTotal();
        page1.setTotal(total);
        return userDao.findAll();
    }

    public boolean updateManager(Integer manager, String uid) {
        int row = userDao.updateManager(uid, manager);
        if(row>0){
            return true;
        }
        return false;
    }

    public User findByUid(String uid) {
        return userDao.findByUid(uid);
    }

    @Override
    public void deleteByUid(String[] uids) throws Exception {
        userDao.deleteByUid(uids);
    }

    @Override
    public User findByPhone(String phone) {
        return userDao.findByPhone(phone);
    }

    @Override
    public User findByUserName(String username) {
        return userDao.findByUserName(username);
    }

    @Override
    public int save(User user) throws Exception {
        return userDao.save(user);
    }

    @Override
    public int updatePassword(String password, String uid) {
        return userDao.updatePassword(password,uid);
    }

    @Override
    public int updateArea(String area, String uid) {
        return userDao.updateArea(area,uid);
    }
}
