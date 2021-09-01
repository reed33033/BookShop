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

@Controller
@RequestMapping("/books")
public class BooksController {
    @Autowired
    private BooksService booksService;
    @Autowired
    private CateService cateService;

    @RequestMapping(value = "/update",method = {RequestMethod.POST})
    @ModelAttribute
    public ModelAndView update(Books books, MultipartFile img,HttpSession session, @RequestParam(name = "page", required = true, defaultValue = "1") Integer page, @RequestParam(name = "size", required = true, defaultValue = "4") Integer size) throws Exception {
        if(img.getSize()!=0) {
            String pic = LoadUtil.uploadPhoto(img, session);
            books.setPic(pic);
        }
        booksService.updateOne(books);
        ModelAndView mv = new ModelAndView();
        List<Books> booksList = booksService.findAll(page,size);
        //PageInfo就是一个分页Bean
        PageInfo booksPageInfo = new PageInfo(booksList);
        mv.addObject("booksPageInfo", booksPageInfo);
        mv.setViewName("goods_list");
        return mv;
    }


    //查询指定id的用户
    @RequestMapping("/findBooks")
    public String findBooks(String info,String option,HttpSession session, @RequestParam(name = "page", required = true, defaultValue = "1") Integer page, @RequestParam(name = "size", required = true, defaultValue = "4") Integer size) throws Exception {
        ModelAndView mv = new ModelAndView();
        List<Books> booksList=new ArrayList<Books>();
        if(option.equals("cid")){
            booksList = booksService.findBooksByCid(info,page,size);
        }else if(option.equals("bookname")){
            booksList=booksService.findBooksByBname(info,page,size);
        }else if(option.equals("ISBN")){
            Books booksByIsbn = booksService.findBooksByIsbn(info.trim().toString());
            if(booksByIsbn!=null) {
                booksList.add(booksByIsbn);
            }
        }

        PageInfo searchBooks = new PageInfo(booksList);
        session.setAttribute("searchBooks",searchBooks);
        session.setAttribute("option",option);
        session.setAttribute("info",info);
        return "redirect:/search.jsp";
    }

    //查询指定id的用户
    @RequestMapping("/findBooksBybid")
    public String findBooksBybid(String bid,HttpSession session){
        Books byBid = booksService.findByBid(bid);
        session.setAttribute("buyBook",byBid);
        return "redirect:/buy.jsp";
    }


}
