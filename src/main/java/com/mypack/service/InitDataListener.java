package com.mypack.service;

import com.mypack.domain.Books;
import com.mypack.domain.Category;
import com.mypack.domain.User;
import com.mypack.service.CateService;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;
import org.springframework.web.context.ServletContextAware;

import javax.annotation.Resource;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.util.List;

@Component
public class InitDataListener implements InitializingBean, ServletContextAware {

    @Resource
    private CateService cateService;

    @Resource
    private BooksService booksService;

    public void afterPropertiesSet() throws Exception {


    }

    public void setServletContext(ServletContext servletContext) {
        List<Category> cateList = null;
        List<Books> booksByState=null;
        try {
            cateList = cateService.findAll();
            booksByState = booksService.findBooksByState(1, 1, 4);

        } catch (Exception e) {
            e.printStackTrace();
        }
        servletContext.setAttribute("cateList",cateList);
        servletContext.setAttribute("hotBList",booksByState);
        System.out.println(cateList);
    }
}
