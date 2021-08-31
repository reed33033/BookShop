package com.mypack.controller;

import com.github.pagehelper.PageInfo;
import com.mypack.domain.Books;
import com.mypack.domain.Category;
import com.mypack.domain.User;
import com.mypack.service.BooksService;
import com.mypack.service.CateService;
import com.mypack.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {
@Autowired
    private UserService userService;

@Autowired
private CateService cateService;

@Autowired
private BooksService booksService;

    @RequestMapping(value = "/adminLogin",method = {RequestMethod.POST})
    public String login(String username, String password, HttpSession session){
        User user=userService.adminLogin(username,password);
        if(user!=null){
            //登录成功 将user存储到session
            session.setAttribute("user",user);
            session.setAttribute("msg","");
            return "redirect:/admin/main.jsp";
        }else{
            session.setAttribute("msg","用户名、密码输入错误，或者您没有登录权限");
            return "redirect:/admin/login.jsp";
        }

    }


    @RequestMapping("/findAll")
    public ModelAndView findAll(@RequestParam(name = "page", required = true, defaultValue = "1") Integer page, @RequestParam(name = "size", required = true, defaultValue = "4") Integer size) throws Exception {
        ModelAndView mv = new ModelAndView();
        List<User> userList = userService.findAll(page,size);
        //PageInfo就是一个分页Bean
        PageInfo pageInfo = new PageInfo(userList);
        mv.addObject("pageInfo", pageInfo);
        mv.setViewName("user_list");
        return mv;
    }

    @RequestMapping("/updateManager")
    public ModelAndView updateManager(@RequestParam(name = "manager", required = true) Integer manager, @RequestParam(name = "uid", required = true) String uid,@RequestParam(name = "page", required = true, defaultValue = "1") Integer page,@RequestParam(name = "size", required = true, defaultValue = "4") Integer size) throws Exception {
        userService.updateManager(manager,uid);
        ModelAndView mv = new ModelAndView();
        List<User> userList = userService.findAll(page,size);
        //PageInfo就是一个分页Bean
        PageInfo pageInfo = new PageInfo(userList);
        mv.addObject("pageInfo", pageInfo);
        mv.setViewName("user_list");
        return mv;
    }

    @RequestMapping("/logout")
    public String logout(HttpSession session){
        session.removeAttribute("user");
        return "login";
    }

    @RequestMapping("/findAllCate")
    public ModelAndView findAllCate(@RequestParam(name = "page", required = true, defaultValue = "1") Integer page, @RequestParam(name = "size", required = true, defaultValue = "4") Integer size) throws Exception {
        ModelAndView mv = new ModelAndView();
        List<Category> cateList = cateService.findAll(page,size);
        //PageInfo就是一个分页Bean
        PageInfo pageInfo = new PageInfo(cateList);
        mv.addObject("catePageInfo", pageInfo);
        mv.setViewName("category_list");
        return mv;
    }


    //查询指定id的用户
    @RequestMapping("/findByCid")
    public ModelAndView findByCId(String cid) throws Exception {
        ModelAndView mv = new ModelAndView();
        Category cate = cateService.findByCid(cid);
        mv.addObject("cate", cate);
        mv.setViewName("category_update");
        return mv;
    }

    @RequestMapping(value = "/cateSave",method = {RequestMethod.POST})
    public ModelAndView cateSave(Category category,HttpSession session) throws Exception {
        cateService.save(category);
        ModelAndView mv = new ModelAndView();
        List<Category> cateList = cateService.findAll(1,4);
        //PageInfo就是一个分页Bean
        PageInfo pageInfo = new PageInfo(cateList);
        mv.addObject("catePageInfo", pageInfo);
        mv.setViewName("category_list");
        return mv;
    }

    @RequestMapping(value = "/cateDelete")
    public ModelAndView cateDelete(String cid,HttpSession session) throws Exception {
        System.out.println(Arrays.asList(cid));
        String[] cids=cid.split(",");
        cateService.deleteByCid(cids);
        ModelAndView mv = new ModelAndView();
        List<Category> cateList = cateService.findAll(1,4);
        //PageInfo就是一个分页Bean
        PageInfo pageInfo = new PageInfo(cateList);
        mv.addObject("catePageInfo", pageInfo);
        mv.setViewName("category_list");
        return mv;
    }

    @RequestMapping(value = "/userDelete")
    public ModelAndView userDelete(String uid,HttpSession session) throws Exception {
        String[] uids=uid.split(",");
        userService.deleteByUid(uids);
        ModelAndView mv = new ModelAndView();
        List<User> userList = userService.findAll(1,4);
        //PageInfo就是一个分页Bean
        PageInfo pageInfo = new PageInfo(userList);
        mv.addObject("pageInfo", pageInfo);
        mv.setViewName("user_list");
        return mv;
    }

    @RequestMapping("/findAllBooks")
    public ModelAndView findAllBooks(@RequestParam(name = "page", required = true, defaultValue = "1") Integer page, @RequestParam(name = "size", required = true, defaultValue = "4") Integer size) throws Exception {
        ModelAndView mv = new ModelAndView();
        List<Books> booksList = booksService.findAll(page,size);
        //PageInfo就是一个分页Bean
        PageInfo booksPageInfo = new PageInfo(booksList);
        mv.addObject("booksPageInfo", booksPageInfo);
        mv.setViewName("goods_list");
        return mv;
    }
}
