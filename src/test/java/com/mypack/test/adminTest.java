package com.mypack.test;

import com.mypack.dao.BooksDao;
import com.mypack.dao.UserDao;
import com.mypack.domain.User;
import com.mypack.service.UserService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.Resource;
import java.util.List;

public class adminTest {

    @Resource
    private UserService userService;

    @Autowired
    private BooksDao booksDao;

    @Autowired
    private UserDao userDao;

    @Test
    public void loginAdmin(){
        String username="admin";
        String password="123456";
       // User user = userService.adminLogin(username,password);
//        List<User> all = userService.findAll();
//        System.out.println(all);

    }

    @Test
    public void testCountBooksByCid(){
        int i = booksDao.selectBooksCount("3");
        System.out.println(i);
    }
}
