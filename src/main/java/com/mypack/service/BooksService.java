package com.mypack.service;

import com.mypack.domain.Books;
import com.mypack.domain.Category;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 图书业务层接口
 */
public interface BooksService {
    /**
     * 查询所有图书
     *
     * @return 查询到的图书集合
     * @throws Exception
     */
    List<Books> findAll() throws Exception;

    /**
     * 分页查询所有图书信息
     *
     * @param page 页码值
     * @param size 每页的条数
     * @return 查询到的图书集合
     * @throws Exception
     */
    public List<Books> findAll(int page, int size) throws Exception;

    /**
     * 修改图书信息
     *
     * @param books 要修改的图书信息
     * @return 返回值>0代表修改成功，否则修改失败
     * @throws Exception
     */
    int updateOne(Books books) throws Exception;

    /**
     * 通过图书id查询图书信息
     *
     * @param bid 图书id
     * @return 查询结果，如果不存在则值为null
     */
    Books findByBid(String bid);

    /**
     * 添加图书信息
     *
     * @param books 要添加的图书信息
     * @throws Exception
     */
    void save(Books books) throws Exception;

    /**
     * 通过id删除图书信息
     *
     * @param array 要删除的图书的id
     * @throws Exception
     */
    void deleteByBid(String[] array) throws Exception;

    /**
     * 查询图书总数
     *
     * @return 图书总数
     */
    int selectBooksCount(int cid);

    /**
     * 通过分类查询图书
     *
     * @param cid 分类id
     * @return 查询到的图书信息集合
     * @throws Exception
     */
    List<Books> findBooksByCid(String cid, int page, int size) throws Exception;

    /**
     * 通过ISBN查询图书信息
     *
     * @param isbn 图书ISBN
     * @return 查询结果，不存在则值为null
     * @throws Exception
     */
    Books findBooksByIsbn(String isbn) throws Exception;

    /**
     * 根据图书名称分页模糊查询图书信息
     *
     * @param bname 图书名称
     * @param page  页码值
     * @param size  每页的条数
     * @return 查询到的图书信息
     */
    List<Books> findBooksByBname(String bname, int page, int size);

    /**
     * 根据图书状态查询图书信息
     *
     * @param state 图书状态，0代表人气图书，0是正常图书
     * @param page  分页值
     * @param size
     * @return 查询到的图书信息
     */
    List<Books> findBooksByState(int state, int page, int size);
}
