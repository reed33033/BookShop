package com.mypack.dao;

import com.mypack.domain.Books;
import com.mypack.domain.Category;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * 图书的数据库操作层
 */
public interface BooksDao {
    /**
     * 查询所有图书
     *
     * @return 查询到的图书列表
     * @throws Exception
     */
    @Select("select * from books")
    @Results({
            @Result(id = true, property = "bid", column = "bid"),
            @Result(property = "cid", column = "cid"),
            @Result(property = "bname", column = "bname"),
            @Result(property = "author", column = "author"),
            @Result(property = "publish", column = "publish"),
            @Result(property = "isbn", column = "isbn"),
            @Result(property = "words", column = "words"),
            @Result(property = "price", column = "price"),
            @Result(property = "description", column = "description"),
            @Result(property = "full_description", column = "full_description"),
            @Result(property = "pic", column = "pic"),
            @Result(property = "state", column = "state"),
            @Result(property = "version", column = "version"),
            @Result(property = "product_date", column = "product_date"),
            @Result(property = "category", column = "cid", javaType = Category.class, one = @One(select = "com.mypack.dao.CateDao.findByCid")),
    })
    List<Books> findAll() throws Exception;


    /**
     * 修改图书信息
     *
     * @param books 要修改的图书信息
     * @return 返回值>0代表修改成功，否则修改失败
     * @throws Exception
     */
    @Update("update books set cid=#{cid},bname=#{bname},author=#{author},publish=#{publish},isbn=#{isbn},words=#{words},price=#{price},description=#{description},full_description=#{full_description},pic=#{pic},state=#{state},version=#{version},product_date=#{product_date} where bid=#{bid}")
    int updateOne(Books books) throws Exception;

    /**
     * 通过图书id查询图书信息
     *
     * @param bid 图书id
     * @return 查询结果，如果不存在则值为null
     */
    @Select("select * from books where bid=#{bid}")
    @Results({
            @Result(id = true, property = "bid", column = "bid"),
            @Result(property = "cid", column = "cid"),
            @Result(property = "bname", column = "bname"),
            @Result(property = "author", column = "author"),
            @Result(property = "publish", column = "publish"),
            @Result(property = "isbn", column = "isbn"),
            @Result(property = "words", column = "words"),
            @Result(property = "price", column = "price"),
            @Result(property = "description", column = "description"),
            @Result(property = "full_description", column = "full_description"),
            @Result(property = "pic", column = "pic"),
            @Result(property = "state", column = "state"),
            @Result(property = "version", column = "version"),
            @Result(property = "product_date", column = "product_date"),
            @Result(property = "category", column = "cid", javaType = Category.class, one = @One(select = "com.mypack.dao.CateDao.findByCid")),
    })
    Books findByBid(String bid);

    /**
     * 根据分类查询图书数量
     *
     * @param cid 图书id
     * @return 查询到的数量
     */
    @Select("select count(*) from books where cid=#{cid}")
    int selectBooksCount(int cid);

    /**
     * 添加图书信息
     *
     * @param books 要添加的图书信息
     * @throws Exception
     */
    @Insert("insert into books(bid,cid,bname,author,publish,isbn,words,price,description,full_description,pic,state,version,product_date) values(#{bid},#{cid},#{bname},#{author},#{publish},#{isbn},#{words},#{price},#{description},#{full_description},#{pic},#{state},#{version},#{product_date})")
    void save(Books books) throws Exception;

    /**
     * 通过id删除图书信息
     *
     * @param array 要删除的图书的id
     * @throws Exception
     */
    @Delete(" <script>" + "delete from books where bid in <foreach collection='array' item='item' open='(' close=')' separator=',' >#{item}</foreach>" + "</script>")
    void deleteByBid(@Param("array") String[] array) throws Exception;

    /**
     * 查询图书总数
     *
     * @return 图书总数
     */
    @Select("select count(*) from books")
    int countBooks();

    /**
     * 通过分类查询图书
     *
     * @param cid 分类id
     * @return 查询到的图书信息集合
     * @throws Exception
     */
    @Select("select * from books where cid=#{cid}")
    List<Books> findBooksByCid(String cid) throws Exception;

    /**
     * 通过ISBN查询图书信息
     *
     * @param isbn 图书ISBN
     * @return 查询结果，不存在则值为null
     * @throws Exception
     */
    @Select("select * from books where isbn=#{isbn}")
    Books findBooksByIsbn(String isbn) throws Exception;

    /**
     * 根据图书名称模糊查询图书信息
     *
     * @param bname 图书名称
     * @return 查询到的图书信息
     */
    @Select("select * from books where bname like CONCAT('%',#{bname},'%')")
    List<Books> findBooksByBname(String bname);

    /**
     * 根据图书状态查询图书信息
     *
     * @param state 图书状态，0代表人气图书，0是正常图书
     * @return 查询到的图书信息
     */
    @Select("select * from books where state=#{state}")
    List<Books> findBooksByState(int state);
}
