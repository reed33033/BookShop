package com.mypack.controller;

import com.mypack.domain.Orders;
import com.mypack.domain.User;
import com.mypack.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.List;

@RequestMapping("/user")
@Controller
public class UserController {
    @Autowired
    private UserService userService;
    @RequestMapping("/findAll")
    public ModelAndView findAll() throws Exception {
        ModelAndView mv = new ModelAndView();
        List<User> userList = userService.findAll();
        mv.addObject("userList", userList);
        mv.setViewName("index");
        return mv;
    }

    @RequestMapping("/logout")
    public String logout(HttpSession session){
        session.removeAttribute("user");
        return "redirect:/login.jsp";
    }

    @RequestMapping(value = "/login",method = {RequestMethod.POST})
    public String login(String loginId, String loginPwd, HttpSession session, HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        User user=userService.userLogin(loginId,loginPwd);
        if(user!=null){
            //登录成功 将user存储到session
            session.setAttribute("user",user);
            session.setAttribute("msg","");
            return "redirect:/index.jsp";
        }else{
//            session.setAttribute("msg","用户名、密码输入错误，或者您没有登录权限");
            //登录失败，携带错误信息回到后台登录页面重新登录
            PrintWriter writer = response.getWriter();
            writer.write("<script>");
            writer.write(" alert('用户名或密码错误！');");
            writer.write("window.location.href='../login.jsp'");
            writer.write("</script>");
            writer.flush();
            writer.close();
            return "redirect:/login.jsp";
        }

    }


    @RequestMapping("/findByUsername")
    @ResponseBody
    public void findByUsernameI(String username,HttpServletResponse response) throws IOException {
        User user = userService.findByUserName(username);
        boolean isRegist =false;
        if (user!=null){
            isRegist=true;

        }
        //响应给页面请求
        response.getWriter().print(isRegist);
    }

    @RequestMapping("/findByPhone")
    @ResponseBody
    public void findByPhone(String phone,HttpServletResponse response) throws IOException {
        User user = userService.findByPhone(phone);
        boolean isRegist =false;
        if (user!=null){
            isRegist=true;

        }
        //响应给页面请求
        response.getWriter().print(isRegist);
    }

    @RequestMapping("registUser")
    public String registUser(User user, HttpSession session, HttpServletRequest request, HttpServletResponse response) throws Exception {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        user.setCreate_time(new Date());
        int save = userService.save(user);
        if(save>0){
            return "redirect:/login.jsp";
        }else{
//            session.setAttribute("msg","用户名、密码输入错误，或者您没有登录权限");
            //登录失败，携带错误信息回到后台登录页面重新登录
            PrintWriter writer = response.getWriter();
            writer.write("<script>");
            writer.write(" alert('注册失败，请检查是否信息有误！');");
            writer.write("window.location.href='../login.jsp'");
            writer.write("</script>");
            writer.flush();
            writer.close();
            return "redirect:/register.jsp";
        }

    }


    @RequestMapping("/updatePassword")
    public String updatePassword(String password,HttpSession session) throws IOException {
        User user = (User)session.getAttribute("user");
        if(user==null){
            return "redirect:/login.jsp";
        }else {
            userService.updatePassword(password,user.getUid().toString());
            User byUid = userService.findByUid(user.getUid().toString());
            session.setAttribute("user",byUid);
            return "redirect:/user/user.jsp";
        }
    }

    @RequestMapping("/updateArea")
    public String updateArea(String area,HttpSession session) throws IOException {
        User user = (User)session.getAttribute("user");
        if(user==null){
            return "redirect:/login.jsp";
        }else {
            userService.updateArea(area,user.getUid().toString());
            User byUid = userService.findByUid(user.getUid().toString());
            session.setAttribute("user",byUid);
            return "redirect:/user/user.jsp";
        }
    }


    @RequestMapping("/userInfo")
    public String userInfo(HttpSession session) throws IOException {
        User user = (User)session.getAttribute("user");
        if(user==null){
            return "redirect:/login.jsp";
        }else {
            return "redirect:/user/user.jsp";
        }
    }

}
