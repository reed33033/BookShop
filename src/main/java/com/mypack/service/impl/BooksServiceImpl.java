package com.mypack.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.mypack.dao.BooksDao;
import com.mypack.domain.Books;
import com.mypack.service.BooksService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("bookService")
@Transactional
public class BooksServiceImpl implements BooksService {
    @Autowired
    private BooksDao booksDao;
    @Override
    public List<Books> findAll() throws Exception {
        return booksDao.findAll();
    }

    @Override
    public List<Books> findAll(int page, int size) throws Exception {
        //参数pageNum 是页码值   参数pageSize 代表是每页显示条数
        Page page1= PageHelper.startPage(page, size);
        int total = (int) page1.getTotal();
        page1.setTotal(total);
        return booksDao.findAll();
    }

    @Override
    public int updateOne(Books books) throws Exception {
        return booksDao.updateOne(books);
    }

    @Override
    public Books findByBCid(String bid) {
        return booksDao.findByBCid(bid);
    }

    @Override
    public void save(Books books) throws Exception {
        booksDao.save(books);
    }

    @Override
    public void deleteByBid(String[] array) throws Exception {
        booksDao.deleteByBid(array);
    }
}
