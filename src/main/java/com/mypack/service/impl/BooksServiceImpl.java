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

/**
 * 图书业务层接口实现类
 */
@Service("bookService")
@Transactional
public class BooksServiceImpl implements BooksService {
    /**
     * 注入的图书数据库操作层对象
     */
    @Autowired
    private BooksDao booksDao;

    /**
     * 查询所有图书
     *
     * @return 查询到的图书集合
     * @throws Exception
     */
    @Override
    public List<Books> findAll() throws Exception {

        return booksDao.findAll();
    }

    /**
     * 分页查询所有图书信息
     *
     * @param page 页码值
     * @param size 每页的条数
     * @return 查询到的图书集合
     * @throws Exception
     */
    @Override
    public List<Books> findAll(int page, int size) throws Exception {
        //参数pageNum 是页码值   参数pageSize 代表是每页显示条数
        Page page1 = PageHelper.startPage(page, size);
        int total = (int) page1.getTotal();
        page1.setTotal(total);
        return booksDao.findAll();
    }

    /**
     * 修改图书信息
     *
     * @param books 要修改的图书信息
     * @return 返回值>0代表修改成功，否则修改失败
     * @throws Exception
     */
    @Override
    public int updateOne(Books books) throws Exception {
        return booksDao.updateOne(books);
    }

    /**
     * 通过图书id查询图书信息
     *
     * @param bid 图书id
     * @return 查询结果，如果不存在则值为null
     */
    @Override
    public Books findByBid(String bid) {
        return booksDao.findByBid(bid);
    }

    /**
     * 添加图书信息
     *
     * @param books 要添加的图书信息
     * @throws Exception
     */
    @Override
    public void save(Books books) throws Exception {
        booksDao.save(books);
    }

    /**
     * 通过id删除图书信息
     *
     * @param array 要删除的图书的id
     * @throws Exception
     */
    @Override
    public void deleteByBid(String[] array) throws Exception {
        booksDao.deleteByBid(array);
    }

    /**
     * 查询图书总数
     *
     * @return 图书总数
     */
    public int selectBooksCount(int cid) {
        return booksDao.selectBooksCount(cid);
    }

    /**
     * 通过分类查询图书
     *
     * @param cid 分类id
     * @return 查询到的图书信息集合
     * @throws Exception
     */
    @Override
    public List<Books> findBooksByCid(String cid, int page, int size) throws Exception {
        //参数pageNum 是页码值   参数pageSize 代表是每页显示条数
        Page page1 = PageHelper.startPage(page, size);
        int total = (int) page1.getTotal();
        page1.setTotal(total);
        return booksDao.findBooksByCid(cid);
    }

    /**
     * 通过ISBN查询图书信息
     *
     * @param isbn 图书ISBN
     * @return 查询结果，不存在则值为null
     * @throws Exception
     */
    @Override
    public Books findBooksByIsbn(String isbn) throws Exception {
        Books booksByIsbn = booksDao.findBooksByIsbn(isbn);
        return booksByIsbn;
    }

    /**
     * 根据图书名称分页模糊查询图书信息
     *
     * @param bname 图书名称
     * @param page  页码值
     * @param size  每页的条数
     * @return 查询到的图书信息
     */
    @Override
    public List<Books> findBooksByBname(String bname, int page, int size) {
        //参数pageNum 是页码值   参数pageSize 代表是每页显示条数
        Page page1 = PageHelper.startPage(page, size);
        int total = (int) page1.getTotal();
        page1.setTotal(total);
        return booksDao.findBooksByBname(bname);
    }

    /**
     * 根据图书状态查询图书信息
     *
     * @param state 图书状态，0代表人气图书，0是正常图书
     * @param page  分页值
     * @param size
     * @return 查询到的图书信息
     */
    @Override
    public List<Books> findBooksByState(int state, int page, int size) {
        //参数pageNum 是页码值   参数pageSize 代表是每页显示条数
        Page page1 = PageHelper.startPage(page, size);
        int total = (int) page1.getTotal();
        page1.setTotal(total);
        return booksDao.findBooksByState(state);
    }
}
