package com.mypack.dao;

import com.mypack.domain.Books;
import com.mypack.domain.Category;
import com.mypack.domain.Trolley;
import com.mypack.domain.User;
import org.apache.ibatis.annotations.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.awt.print.Book;
import java.util.List;

/**
 * 购物车的数据库操作层
 */
public interface TrolleyDao {

    /**
     * 查询购物车是否存在
     *
     * @param trolley 购物车信息
     * @return 查询到的购物车信息，如果不存在则值为null
     */
    @Select("select * from trolley where uid =#{uid} and bid =#{bid} and orders_number is null")
    Trolley confirmIsExist(Trolley trolley);

    /**
     * 添加购物车
     *
     * @param trolley 要添加的购物车信息
     * @throws Exception
     */
    @Insert("insert into trolley(tid,uid,bid,number,orders_number) values(#{tid},#{uid},#{bid},#{number},#{orders_number})")
    void save(Trolley trolley) throws Exception;


    /**
     * 根据图书id删除购物车信息
     *
     * @param tid 图书id
     */
    @Delete("delete from trolley where tid =#{tid}")
    void deleteByTid(String tid);

    /**
     * 修改购物车信息
     *
     * @param trolley 要修改的购物车信息
     * @return 结果>0表示修改成功，否则失败
     */
    @Update("update trolley set uid=#{uid},bid=#{bid},number=#{number},orders_number=#{orders_number} where tid=#{tid}")
    int updateTrolley(Trolley trolley);

    /**
     * 根据用户id查询购物车信息
     *
     * @param uid 用户id
     * @return 查询到的购物车信息集合
     */
    @Select("select * from trolley where uid=#{uid} and orders_number is null")
    @Results({
            @Result(id = true, property = "tid", column = "tid"),
            @Result(property = "uid", column = "uid"),
            @Result(property = "bid", column = "bid"),
            @Result(property = "number", column = "number"),
            @Result(property = "orders_number", column = "orders_number"),
            @Result(property = "user", column = "uid", javaType = User.class, one = @One(select = "com.mypack.dao.UserDao.findByUid")),
            @Result(property = "books", column = "bid", javaType = Books.class, one = @One(select = "com.mypack.dao.BooksDao.findByBid"))
    })
    List<Trolley> findAllTrolley(String uid);

    /**
     * 根据订单编号查询购物车信息
     *
     * @param orders_number 订单编号
     * @return 查询到的购物车集合
     */
    @Select("select * from trolley where orders_number =#{orders_number}")
    List<Trolley> findTrolleyByOrder(String orders_number);

    /**
     * 修改购物车中指定条目的数量
     *
     * @param tid    图书id
     * @param number 数量
     * @return 结果>0表示修改成功，否则失败
     */
    @Update("update trolley set number=#{number} where tid=#{tid}")
    int updateNumber(@Param("tid") String tid, @Param("number") String number);

    /**
     * 修改用户的订单编号，结算了购物车中的商品才有编号
     *
     * @param orders_number 订单编号
     * @param uid           用户id
     * @return 结果>0表示修改成功，否则失败
     */
    @Update("update trolley set orders_number =#{orders_number} where uid =#{uid} and orders_number is null")
    int updateOrders(@Param("orders_number") String orders_number, @Param("uid") Integer uid);
}
