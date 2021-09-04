package com.mypack.controller;

import com.github.pagehelper.PageInfo;
import com.mypack.domain.Category;
import com.mypack.domain.Orders;
import com.mypack.domain.Trolley;
import com.mypack.domain.User;
import com.mypack.service.TrolleyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.Arrays;
import java.util.List;

/**
 * 购物车的控制层
 */
@Controller
@RequestMapping("/trolley")
public class TrolleyController {
    /**
     * 注入的购物车业务层对象
     */
    @Autowired
    private TrolleyService trolleyService;

    /**
     * 将指定商品添加购物车
     *
     * @param bid     图书id
     * @param session
     * @return 用户不存在转发到登录界面，否则到加入购物车成功界面
     * @throws Exception
     */
    @RequestMapping("/trolleySave")
    public String trolleySave(String bid, HttpSession session) throws Exception {
        User user = (User) session.getAttribute("user");
        if (user == null) {
            return "redirect:/login.jsp";
        } else {
            Trolley trolley = new Trolley(user.getUid(), Integer.valueOf(bid));
            trolleyService.save(trolley);
            return "redirect:/addcartok.jsp";
        }

    }


    /**
     * 查询指定用户的购物车
     *
     * @param uid     用户id
     * @param session
     * @return 户不存在转发到登录界面，否则到显示购物车信息界面
     * @throws Exception
     */
    @RequestMapping("/findAllTrolley")
    public String findAllTrolley(String uid, HttpSession session) throws Exception {

        User user = (User) session.getAttribute("user");
        if (user == null) {
            return "redirect:/login.jsp";
        } else {
            List<Trolley> trolleys = trolleyService.findAllTrolley(uid);
            session.setAttribute("trolleys", trolleys);
            return "redirect:/showcart.jsp";
        }


    }

    /**
     * 修改购物车中指定商品的数量
     *
     * @param tid     图书id
     * @param number  数量
     * @param uid     用户id
     * @param session
     * @return 显示购物车信息的界面
     */
    @RequestMapping("/addOrDeleteNumber")
    public String addOrDeleteNumber(String tid, String number, String uid, HttpSession session) {
        trolleyService.updateNumber(tid, number);
        List<Trolley> trolleys = trolleyService.findAllTrolley(uid);
        session.setAttribute("trolleys", trolleys);
        return "redirect:/showcart.jsp";
    }

    /**
     * 删除购物车中的指定商品
     *
     * @param tid     图书id
     * @param uid     用户id
     * @param session
     * @return 示购物车信息的界面
     * @throws Exception
     */
    @RequestMapping(value = "/trolleyDelete")
    public String trolleyDelete(String tid, String uid, HttpSession session) throws Exception {
        trolleyService.deleteByTid(tid);
        List<Trolley> trolleys = trolleyService.findAllTrolley(uid);
        session.setAttribute("trolleys", trolleys);
        return "redirect:/showcart.jsp";
    }
}
