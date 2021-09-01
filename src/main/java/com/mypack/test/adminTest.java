package com.mypack.test;

import com.mypack.dao.BooksDao;
import com.mypack.dao.UserDao;
import com.mypack.domain.Books;
import com.mypack.domain.Category;
import com.mypack.domain.User;
import com.mypack.service.CateService;
import com.mypack.service.UserService;
import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.TestExecutionListeners;

import javax.annotation.Resource;
import java.util.List;

public class adminTest {

    @Autowired
    private UserService userService;

    @Autowired
    private UserDao userDao;

    @Autowired
    private BooksDao booksDao;

    @Autowired
    private CateService cateService;

    @Test
    public void loginAdmin() throws Exception {
        String username="admin";
        String password="123456";
//        User user = userService.adminLogin(username,password);
//        System.out.println(user);

        List<User> all = userService.findAll();
        System.out.println(all);

        User user = userService.adminLogin(username, password);
        System.out.println(user);

        List<Category> all1 = cateService.findAll();
        System.out.println(all1);

    }

    @Test
    public void testCountBooksByCid(){
        int i = booksDao.countBooks();
        System.out.println(i);
    }

    @Test
    public void tetsBooksByIsbn() throws Exception {
        String isbn="9787115145543";
        Books booksByIsbn = booksDao.findBooksByIsbn(isbn);
        System.out.println(booksByIsbn);
    }
}
