package com.mypack.test;

import com.mypack.dao.BooksDao;
import com.mypack.dao.TrolleyDao;
import com.mypack.dao.UserDao;
import com.mypack.domain.Books;
import com.mypack.domain.Trolley;
import com.mypack.domain.User;
import com.mypack.service.BooksService;
import com.mypack.service.TrolleyService;
import com.mypack.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import javax.annotation.Resource;
import java.util.List;

@WebAppConfiguration
@ContextConfiguration(locations = {"classpath:applicationContext.xml","classpath:spring-mvc.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
public class adminTest {

    @Resource
    private UserService userService;

    @Autowired
    private BooksDao booksDao;

    @Autowired
    private UserDao userDao;

    @Autowired
    private BooksService booksService;

    @Autowired
    private TrolleyService trolleyService;

    @Test
    public void test()
    {
        User user = userService.userLogin("qaz", "321");
        System.out.println(user);
    }

    @Test
    public void tetsBooksByIsbn() throws Exception {
        String isbn="9787115145543";
        Books booksByIsbn = booksService.findBooksByIsbn(isbn);
        System.out.println(booksByIsbn);
    }

    @Test
    public void testTrollery()
    {
//        List<Trolley> allTrolley = trolleyService.findAllTrolley("8");
//        for (Trolley trolley : allTrolley) {
//            System.out.println(trolley);
//        }

//        int i = trolleyService.updateNumber("6", "5");
//        System.out.println(i);
//        int x=20, y=5;
//        System.out.println(x+y +""+(x+y)+y);

    }
}
