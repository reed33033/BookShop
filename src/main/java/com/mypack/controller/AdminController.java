package com.mypack.controller;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import com.mypack.domain.Books;
import com.mypack.domain.Category;
import com.mypack.domain.Orders;
import com.mypack.domain.User;
import com.mypack.service.BooksService;
import com.mypack.service.CateService;
import com.mypack.service.OrdersService;
import com.mypack.service.UserService;


import com.mypack.utils.EchartsBean;
import com.mypack.utils.LoadUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 管理员的控制层类
 */
@Controller
@RequestMapping("/admin")
public class AdminController {
    /**
     * 注入分用户业务层对象
     */
    @Autowired
    private UserService userService;
    /**
     * 注入的分类业务层对象
     */
    @Autowired
    private CateService cateService;
    /**
     * 注入的图书业务层对象
     */
    @Autowired
    private BooksService booksService;
    /**
     * 注入的订单业务层对象
     */
    @Autowired
    private OrdersService ordersService;

    /**
     * 管理员登录
     *
     * @param username 用户名
     * @param password 用户密码
     * @param session  当前的session
     * @return 登录成功转发到后台主页/admin/main.jsp，登录继续在登录页面
     */
    @RequestMapping(value = "/adminLogin", method = {RequestMethod.POST})
    public String login(String username, String password, HttpSession session) {
        User user = userService.adminLogin(username, password);
        if (user != null) {
            //登录成功 将user存储到session
            session.setAttribute("admin", user);
            session.setAttribute("msg", "");
            return "redirect:/admin/main.jsp";
        } else {
            session.setAttribute("msg", "用户名、密码输入错误，或者您没有登录权限");
            return "redirect:/admin/login.jsp";
        }

    }


    /**
     * 查询所有用户
     *
     * @param page 页码值
     * @param size
     * @return 每页显示条数
     * @throws Exception
     */
    @RequestMapping("/findAll")
    public ModelAndView findAll(@RequestParam(name = "page", required = true, defaultValue = "1") Integer page, @RequestParam(name = "size", required = true, defaultValue = "4") Integer size) throws Exception {
        ModelAndView mv = new ModelAndView();
        List<User> userList = userService.findAll(page, size);
        //PageInfo就是一个分页Bean
        PageInfo pageInfo = new PageInfo(userList);
        mv.addObject("pageInfo", pageInfo);
        mv.setViewName("user_list");
        return mv;
    }

    /**
     * 修改用户权限
     *
     * @param manager 用户权限，值为0代表管理员，值为1代表普通用户
     * @param uid     用户id
     * @param page    页面值
     * @param size    每页显示条数
     * @return 用户管理页面
     * @throws Exception
     */
    @RequestMapping("/updateManager")
    public ModelAndView updateManager(@RequestParam(name = "manager", required = true) Integer manager, @RequestParam(name = "uid", required = true) String uid, @RequestParam(name = "page", required = true, defaultValue = "1") Integer page, @RequestParam(name = "size", required = true, defaultValue = "4") Integer size) throws Exception {
        userService.updateManager(manager, uid);
        ModelAndView mv = new ModelAndView();
        List<User> userList = userService.findAll(page, size);
        //PageInfo就是一个分页Bean
        PageInfo pageInfo = new PageInfo(userList);
        mv.addObject("pageInfo", pageInfo);
        mv.setViewName("user_list");
        return mv;
    }

    /**
     * 管理员退出系统
     *
     * @param session 当前的Session
     * @return
     */
    @RequestMapping("/logout")
    public String logout(HttpSession session) {
        session.removeAttribute("admin");
        return "login";
    }

    /**
     * 查询所有分类
     *
     * @param page 页码值
     * @param size 每页显示的条数
     * @return 分页管理的页面
     * @throws Exception
     */
    @RequestMapping("/findAllCate")
    public ModelAndView findAllCate(@RequestParam(name = "page", required = true, defaultValue = "1") Integer page, @RequestParam(name = "size", required = true, defaultValue = "4") Integer size) throws Exception {
        ModelAndView mv = new ModelAndView();
        List<Category> cateList = cateService.findAll(page, size);
        //PageInfo就是一个分页Bean
        PageInfo pageInfo = new PageInfo(cateList);
        mv.addObject("catePageInfo", pageInfo);
        mv.setViewName("category_list");
        return mv;
    }


    /**
     * 根据cid查询用户
     *
     * @param cid 用户id
     * @return 用户信息修改界面
     * @throws Exception
     */
    @RequestMapping("/findByCid")
    public ModelAndView findByCId(String cid) throws Exception {
        ModelAndView mv = new ModelAndView();
        Category cate = cateService.findByCid(cid);
        mv.addObject("cate", cate);
        mv.setViewName("category_update");
        return mv;
    }

    /**
     * 添加分页
     *
     * @param category 要添加的的分类信息
     * @param session  当前的Session
     * @return 分类管理页面
     * @throws Exception
     */
    @RequestMapping(value = "/cateSave", method = {RequestMethod.POST})
    public ModelAndView cateSave(Category category, HttpSession session) throws Exception {
        cateService.save(category);
        ModelAndView mv = new ModelAndView();
        List<Category> cateList = cateService.findAll(1, 4);
        //PageInfo就是一个分页Bean
        PageInfo pageInfo = new PageInfo(cateList);
        mv.addObject("catePageInfo", pageInfo);
        mv.setViewName("category_list");
        return mv;
    }

    /**
     * 删除指定的分类
     *
     * @param cid     分类id
     * @param session 当前的Session
     * @return 分类管理的页面
     * @throws Exception
     */
    @RequestMapping(value = "/cateDelete")
    public ModelAndView cateDelete(String cid, HttpSession session) throws Exception {
        System.out.println(Arrays.asList(cid));
        String[] cids = cid.split(",");
        cateService.deleteByCid(cids);
        ModelAndView mv = new ModelAndView();
        List<Category> cateList = cateService.findAll(1, 4);
        //PageInfo就是一个分页Bean
        PageInfo pageInfo = new PageInfo(cateList);
        mv.addObject("catePageInfo", pageInfo);
        mv.setViewName("category_list");
        return mv;
    }

    /**
     * 删除指定id的用户
     *
     * @param uid     用户id
     * @param session 当前的Session
     * @return 用户管理的页面
     * @throws Exception
     */
    @RequestMapping(value = "/userDelete")
    public ModelAndView userDelete(String uid, HttpSession session) throws Exception {
        String[] uids = uid.split(",");
        userService.deleteByUid(uids);
        ModelAndView mv = new ModelAndView();
        List<User> userList = userService.findAll(1, 4);
        //PageInfo就是一个分页Bean
        PageInfo pageInfo = new PageInfo(userList);
        mv.addObject("pageInfo", pageInfo);
        mv.setViewName("user_list");
        return mv;
    }

    /**
     * 分页查询所有图书信息
     *
     * @param page 页码值
     * @param size 每页显示的条数
     * @return 图书管理界面
     * @throws Exception
     */
    @RequestMapping("/findAllBooks")
    public ModelAndView findAllBooks(@RequestParam(name = "page", required = true, defaultValue = "1") Integer page, @RequestParam(name = "size", required = true, defaultValue = "4") Integer size) throws Exception {
        ModelAndView mv = new ModelAndView();
        List<Books> booksList = booksService.findAll(page, size);
        //PageInfo就是一个分页Bean
        PageInfo booksPageInfo = new PageInfo(booksList);
        mv.addObject("booksPageInfo", booksPageInfo);
        mv.setViewName("goods_list");
        return mv;
    }

    /**
     * 查询指定的图书信息
     *
     * @param bid 图书id
     * @return 图书信息修改的界面
     * @throws Exception
     */
    @RequestMapping("/findByBid")
    public ModelAndView findByBId(String bid) throws Exception {
        ModelAndView mv = new ModelAndView();
        Books books = booksService.findByBid(bid);
        mv.addObject("books", books);
        mv.setViewName("goods_update");
        return mv;
    }

    /**
     * 添加图书信息
     *
     * @param books   要添加的图书信息
     * @param img     要添加到的图书的封面图片信息
     * @param session 当前的Session
     * @return 图书管理页面
     * @throws Exception
     */
    @RequestMapping(value = "/booksSave", method = {RequestMethod.POST})
    public ModelAndView booksSave(Books books, MultipartFile img, HttpSession session) throws Exception {
        if (img.getSize() != 0) {
            String pic = LoadUtil.uploadPhoto(img, session);
            books.setPic(pic);
        }
        booksService.save(books);
        ModelAndView mv = new ModelAndView();
        List<Books> booksList = booksService.findAll(1, 4);
        //PageInfo就是一个分页Bean
        PageInfo booksPageInfo = new PageInfo(booksList);
        mv.addObject("booksPageInfo", booksPageInfo);
        mv.setViewName("goods_list");
        return mv;
    }

    /**
     * 删除指定的图书信息
     *
     * @param bid     图书id
     * @param session 当前的Session
     * @return 图书管理页面
     * @throws Exception
     */
    @RequestMapping(value = "/booksDelete")
    public ModelAndView booksDelete(String bid, HttpSession session) throws Exception {
        String[] bids = bid.split(",");
        booksService.deleteByBid(bids);
        ModelAndView mv = new ModelAndView();
        List<Books> booksList = booksService.findAll(1, 4);
        //PageInfo就是一个分页Bean
        PageInfo booksPageInfo = new PageInfo(booksList);
        mv.addObject("booksPageInfo", booksPageInfo);
        mv.setViewName("goods_list");
        return mv;
    }

    /**
     * 图书分类添加信息
     *
     * @param session 当前Session
     * @return 图书显示分类统计的界面
     */
    @RequestMapping("/showByCate")
    public ModelAndView showByCate(HttpSession session) {
        List<EchartsBean> countByCate = new ArrayList<EchartsBean>();
        List<Category> cateList = null;
        try {
            cateList = cateService.findAll();
        } catch (Exception e) {
            e.printStackTrace();
        }

        for (Category category : cateList) {
            int count = booksService.selectBooksCount(category.getCid());
            EchartsBean bean = new EchartsBean(category.getCname(), count);
            countByCate.add(bean);
        }
        String json = JSONObject.toJSONString(countByCate);
        ModelAndView mv = new ModelAndView();
        mv.addObject("eclist", json);
        mv.setViewName("goods_chart");
        return mv;
    }

    /**
     * 查询所有订单信息
     *
     * @param page 页码值
     * @param size 每页的条数
     * @return 订单管理页面
     * @throws Exception
     */
    @RequestMapping("/findAllOrders")
    public ModelAndView findAllOrders(@RequestParam(name = "page", required = true, defaultValue = "1") Integer page, @RequestParam(name = "size", required = true, defaultValue = "4") Integer size) throws Exception {
        ModelAndView mv = new ModelAndView();
        List<Orders> ordersList = ordersService.findAll(page, size);
        //PageInfo就是一个分页Bean
        PageInfo ordersPageInfo = new PageInfo(ordersList);
        mv.addObject("ordersPageInfo", ordersPageInfo);
        mv.setViewName("order_list");
        return mv;
    }

    /**
     * 删除指定的订单
     *
     * @param oid     订单id
     * @param session 当前的Session
     * @return 订单管理页面
     * @throws Exception
     */
    @RequestMapping(value = "/ordersDelete")
    public ModelAndView ordersDelete(String oid, HttpSession session) throws Exception {
        String[] oids = oid.split(",");
        ordersService.deleteByOid(oids);
        ModelAndView mv = new ModelAndView();
        List<Orders> ordersList = ordersService.findAll(1, 4);
        //PageInfo就是一个分页Bean
        PageInfo ordersPageInfo = new PageInfo(ordersList);
        mv.addObject("ordersPageInfo", ordersPageInfo);
        mv.setViewName("order_list");
        return mv;
    }
}
