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

@Controller
@RequestMapping("/trolley")
public class TrolleyController {
    @Autowired
    private TrolleyService trolleyService;

    @RequestMapping("/trolleySave")
    public String trolleySave(String bid,HttpSession session) throws Exception {
        User user = (User) session.getAttribute("user");
        if(user==null){
            return "redirect:/login.jsp";
        }else{
            Trolley trolley = new Trolley(user.getUid(), Integer.valueOf(bid));
            trolleyService.save(trolley);
            return "redirect:/addcartok.jsp";
        }

    }


    @RequestMapping("/findAllTrolley")
    public String findAllTrolley(String uid,HttpSession session) throws Exception {

        User user = (User)session.getAttribute("user");
        if(user==null){
            return "redirect:/login.jsp";
        }else {
            List<Trolley> trolleys = trolleyService.findAllTrolley(uid);
            session.setAttribute("trolleys",trolleys);
            return "redirect:/showcart.jsp";
        }



    }

    @RequestMapping("/addOrDeleteNumber")
    public String addOrDeleteNumber(String tid,String number,String uid,HttpSession session){
        trolleyService.updateNumber(tid, number);
        List<Trolley> trolleys = trolleyService.findAllTrolley(uid);
        session.setAttribute("trolleys",trolleys);
        return "redirect:/showcart.jsp";
    }

    @RequestMapping(value = "/trolleyDelete")
    public String trolleyDelete(String tid,String uid,HttpSession session) throws Exception {
       trolleyService.deleteByTid(tid);
        List<Trolley> trolleys = trolleyService.findAllTrolley(uid);
        session.setAttribute("trolleys",trolleys);
        return "redirect:/showcart.jsp";
    }
}
