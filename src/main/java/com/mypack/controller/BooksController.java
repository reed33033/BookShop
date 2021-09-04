package com.mypack.controller;

import com.github.pagehelper.PageInfo;
import com.mypack.domain.Books;
import com.mypack.domain.Category;
import com.mypack.service.BooksService;
import com.mypack.service.CateService;
import com.mypack.utils.LoadUtil;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

/**
 * 图书管理的控制层
 */
@Controller
@RequestMapping("/books")
public class BooksController {
    /**
     * 注入的图书信息业务层
     */
    @Autowired
    private BooksService booksService;
    /**
     * 注入的分类业务层
     */
    @Autowired
    private CateService cateService;

    /**
     * 修改图书信息
     *
     * @param books   要修改的图书信息
     * @param img     要修改的图书封面图片信息
     * @param session 当前的Session
     * @param page    页码值
     * @param size    每页显示的条数
     * @return 视商品管理页面
     * @throws Exception
     */
    @RequestMapping(value = "/update", method = {RequestMethod.POST})
    @ModelAttribute
    public ModelAndView update(Books books, MultipartFile img, HttpSession session, @RequestParam(name = "page", required = true, defaultValue = "1") Integer page, @RequestParam(name = "size", required = true, defaultValue = "4") Integer size) throws Exception {
        if (img.getSize() != 0) {
            String pic = LoadUtil.uploadPhoto(img, session);
            books.setPic(pic);
        }
        booksService.updateOne(books);
        ModelAndView mv = new ModelAndView();
        List<Books> booksList = booksService.findAll(page, size);
        //PageInfo就是一个分页Bean
        PageInfo booksPageInfo = new PageInfo(booksList);
        mv.addObject("booksPageInfo", booksPageInfo);
        mv.setViewName("goods_list");
        return mv;
    }


    /**
     * 根据条件查询图书信息
     *
     * @param info    要查询的具体条件信息
     * @param option  要进行查询的条件字段，cid代表按分类查询，booknamediam按图书名称查询，ISBN代表按图书ISBN查询
     * @param session 当前的Session
     * @param page    页码值
     * @param size    每页显示是条数
     * @return 查询结果页面
     * @throws Exception
     */
    @RequestMapping("/findBooks")
    public String findBooks(String info, String option, HttpSession session, @RequestParam(name = "page", required = true, defaultValue = "1") Integer page, @RequestParam(name = "size", required = true, defaultValue = "4") Integer size) throws Exception {
        ModelAndView mv = new ModelAndView();
        List<Books> booksList = new ArrayList<Books>();
        if (option.equals("cid")) {
            booksList = booksService.findBooksByCid(info, page, size);
        } else if (option.equals("bookname")) {
            booksList = booksService.findBooksByBname(info, page, size);
        } else if (option.equals("ISBN")) {
            Books booksByIsbn = booksService.findBooksByIsbn(info.trim().toString());
            if (booksByIsbn != null) {
                booksList.add(booksByIsbn);
            }
        }

        PageInfo searchBooks = new PageInfo(booksList);
        session.setAttribute("searchBooks", searchBooks);
        session.setAttribute("option", option);
        session.setAttribute("info", info);
        return "redirect:/search.jsp";
    }

    /**
     * 查询指定的图书信息
     *
     * @param bid     图书id
     * @param session 当前的Session
     * @return 图书详细信息的界面
     */
    @RequestMapping("/findBooksBybid")
    public String findBooksBybid(String bid, HttpSession session) {
        Books byBid = booksService.findByBid(bid);
        session.setAttribute("buyBook", byBid);
        return "redirect:/buy.jsp";
    }


}
