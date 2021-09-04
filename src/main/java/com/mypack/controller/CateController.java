package com.mypack.controller;

import com.github.pagehelper.PageInfo;
import com.mypack.domain.Category;
import com.mypack.service.CateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * 图书分类的控制层
 */
@Controller
@RequestMapping("/cate")
public class CateController {
    /**
     * 注入的分类业务层对象
     */
    @Autowired
    private CateService cateService;

    /**
     * 查询所有图书分类
     *
     * @return 前台主页
     * @throws Exception
     */
    @RequestMapping("/findAll")
    public ModelAndView findAll() throws Exception {
        ModelAndView mv = new ModelAndView();
        List<Category> cateList = cateService.findAll();
        mv.addObject("cateList", cateList);
        mv.setViewName("index");
        return mv;
    }

    /**
     * 修改分类信息
     *
     * @param category 要修改的分类信息
     * @param session  当前的Session
     * @param page     页码值
     * @param size     每页的条数
     * @return 分类管理的界面
     * @throws Exception
     */
    @RequestMapping(value = "/update", method = {RequestMethod.POST})
    public ModelAndView update(Category category, HttpSession session, @RequestParam(name = "page", required = true, defaultValue = "1") Integer page, @RequestParam(name = "size", required = true, defaultValue = "4") Integer size) throws Exception {
        cateService.updateOne(category);
        ModelAndView mv = new ModelAndView();
        List<Category> cateList = cateService.findAll(page, size);
        //PageInfo就是一个分页Bean
        PageInfo pageInfo = new PageInfo(cateList);
        mv.addObject("catePageInfo", pageInfo);
        mv.setViewName("category_list");
        return mv;
    }
}
