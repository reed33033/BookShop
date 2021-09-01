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

@Controller
@RequestMapping("/cate")
public class CateController {
    @Autowired
    private CateService cateService;

    @RequestMapping("/findAll")
    public ModelAndView findAll() throws Exception {
        ModelAndView mv = new ModelAndView();
        List<Category> cateList = cateService.findAll();
        mv.addObject("cateList", cateList);
        mv.setViewName("index");
        return mv;
    }

    @RequestMapping(value = "/update",method = {RequestMethod.POST})
    public ModelAndView update(Category category, HttpSession session,@RequestParam(name = "page", required = true, defaultValue = "1") Integer page, @RequestParam(name = "size", required = true, defaultValue = "4") Integer size) throws Exception {
        cateService.updateOne(category);
        ModelAndView mv = new ModelAndView();
        List<Category> cateList = cateService.findAll(page,size);
        //PageInfo就是一个分页Bean
        PageInfo pageInfo = new PageInfo(cateList);
        mv.addObject("catePageInfo", pageInfo);
        mv.setViewName("category_list");
        return mv;
    }
}
