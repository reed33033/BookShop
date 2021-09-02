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

@Controller
@RequestMapping("/orders")
public class OrdersController {
    @Autowired
    private OrdersService ordersService;

    @Autowired
    private TrolleyService trolleyService;

    @RequestMapping("/ordersSave")
    public String ordersSave(String sumPrice,String goodsCount, HttpSession session) throws Exception {
        User user = (User) session.getAttribute("user");
        String orders_number = UUID.randomUUID().toString();
        String ordersName = user.getUname() + "的订单";//订单名称
        Date create_time = new Date();//订单创建日期
        Orders orders = new Orders(orders_number, user.getUid(), Double.valueOf(sumPrice), ordersName, Integer.valueOf(goodsCount), create_time);
        ordersService.save(orders);
        trolleyService.updateOrders(orders_number,user.getUid());

        session.setAttribute("orders",orders);

        return "redirect:/pay/index.jsp";

    }

    @RequestMapping("/ordersStateUpdate")
    public String ordersStateUpdate(String orders_number, HttpSession session) throws Exception {
        User user = (User) session.getAttribute("user");
        ordersService.updateState(orders_number);
        return "redirect:/index.jsp";
            }

    @RequestMapping("/findOrdersByUid")
    public String findOrdersByUid(String uid,HttpSession session){
        User user = (User)session.getAttribute("user");
        if(user==null){
            return "redirect:/login.jsp";
        }else {
            List<Orders> orders = ordersService.findOrdersByUid(uid);
            session.setAttribute("olist",orders);
            return "redirect:/user/userorder.jsp";
        }

    }

}
