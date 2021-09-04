package com.mypack.controller;

import com.mypack.domain.Orders;
import com.mypack.domain.Trolley;
import com.mypack.domain.User;
import com.mypack.service.OrdersService;
import com.mypack.service.TrolleyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * 订单的控制层
 */
@Controller
@RequestMapping("/orders")
public class OrdersController {
    /**
     * 注入的订单业务层对象
     */
    @Autowired
    private OrdersService ordersService;
    /**
     * 注入的购物车业务层对象
     */
    @Autowired
    private TrolleyService trolleyService;

    /**
     * 添加订单
     *
     * @param sumPrice   总价
     * @param goodsCount 商品数量
     * @param session    当前的Session
     * @return 支付的主页
     * @throws Exception
     */
    @RequestMapping("/ordersSave")
    public String ordersSave(String sumPrice, String goodsCount, HttpSession session) throws Exception {
        User user = (User) session.getAttribute("user");
        String orders_number = UUID.randomUUID().toString();
        String ordersName = user.getUname() + "的订单";//订单名称
        Date create_time = new Date();//订单创建日期
        Orders orders = new Orders(orders_number, user.getUid(), Double.valueOf(sumPrice), ordersName, Integer.valueOf(goodsCount), create_time);
        ordersService.save(orders);
        trolleyService.updateOrders(orders_number, user.getUid());

        session.setAttribute("orders", orders);

        return "redirect:/pay/index.jsp";

    }

    /**
     * 修改订单状态，未支付改为已支付
     *
     * @param orders_number 订单编号
     * @param session       当前的Session
     * @return 主页
     * @throws Exception
     */
    @RequestMapping("/ordersStateUpdate")
    public String ordersStateUpdate(String orders_number, HttpSession session) throws Exception {
        User user = (User) session.getAttribute("user");
        ordersService.updateState(orders_number);
        return "redirect:/index.jsp";
    }


    /**
     * 查询指定用户的订单
     *
     * @param uid     用户id
     * @param session 当前Session
     * @return 用户我的订单界面
     */
    @RequestMapping("/findOrdersByUid")
    public String findOrdersByUid(String uid, HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (user == null) {
            return "redirect:/login.jsp";
        } else {
            List<Orders> orders = ordersService.findOrdersByUid(uid);
            session.setAttribute("olist", orders);
            return "redirect:/user/userorder.jsp";
        }

    }

}
