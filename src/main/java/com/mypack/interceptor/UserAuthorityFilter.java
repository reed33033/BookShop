package com.mypack.interceptor;

import com.mypack.domain.User;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * 访问权限过滤器
 *
 */
//urlPatterns 过滤器拦截的url
@WebFilter(
        urlPatterns = { "/admin/*" },
        initParams = { @WebInitParam(name = "loginPage", value = "admin/login.jsp") },
        dispatcherTypes = { DispatcherType.REQUEST, DispatcherType.FORWARD })
public class UserAuthorityFilter implements Filter {

    private String loginPage = "admin/login.jsp";

    public UserAuthorityFilter() {

    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

        // 获取当请求被拦截时转向的页面
        loginPage = filterConfig.getInitParameter("loginPage");
        if (null == loginPage) {
            loginPage = "admin/login.jsp";
        }
    }


    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        //业务逻辑
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;
        HttpSession session = req.getSession();
        User user=(User)session.getAttribute("admin");
        String path = req.getRequestURI();

//        if(path.contains("css") || path.contains("js") || path.contains("png")|| path.contains("jpg")|| path.contains("images")||path.contains("editor")){
//            //如果发现是css或者js文件，直接放行
//            filterChain.doFilter(servletRequest, servletResponse);
//        }

        // 判断被拦截的请求用户是否处于登录状态
        if (session.getAttribute("admin") == null) {
            if(path.endsWith("login.jsp")||path.endsWith("adminLogin")){
                filterChain.doFilter(servletRequest, servletResponse);

                return;
            }else {
                resp.sendRedirect("/" + loginPage);
            }

        } else {
            //将当前拦截的请求放行，让请求继续访问他要访问的资源
            filterChain.doFilter(servletRequest, servletResponse);
            return;
        }
    }

    @Override
    public void destroy() {
        this.loginPage = null;

    }
}
