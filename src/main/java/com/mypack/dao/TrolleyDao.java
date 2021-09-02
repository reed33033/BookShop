package com.mypack.dao;

import com.mypack.domain.Books;
import com.mypack.domain.Category;
import com.mypack.domain.Trolley;
import com.mypack.domain.User;
import org.apache.ibatis.annotations.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.awt.print.Book;
import java.util.List;

public interface TrolleyDao {

    /**
     * 查询购物车是否存在
     * @param trolley 购物车信息
     * @return 查询到的购物车信息，如果不存在则值为null
     */
    @Select("select * from trolley where uid =#{uid} and bid =#{bid} and orders_number is null")
    Trolley confirmIsExist(Trolley trolley);

    @Insert("insert into trolley(tid,uid,bid,number,orders_number) values(#{tid},#{uid},#{bid},#{number},#{orders_number})")
    void save(Trolley trolley) throws Exception;


    @Delete("delete from trolley where tid =#{tid}")
    void deleteByTid(String tid);

    @Update("update trolley set uid=#{uid},bid=#{bid},number=#{number},orders_number=#{orders_number} where tid=#{tid}")
    int updateTrolley(Trolley trolley);

    @Select("select * from trolley where uid=#{uid} and orders_number is null")
    @Results({
            @Result(id = true, property = "tid", column = "tid"),
            @Result(property = "uid", column = "uid"),
            @Result(property = "bid", column = "bid"),
            @Result(property = "number", column = "number"),
            @Result(property = "orders_number", column = "orders_number"),
            @Result(property = "user",column = "uid",javaType = User.class, one = @One(select = "com.mypack.dao.UserDao.findByUid")),
            @Result(property = "books",column = "bid",javaType = Books.class, one = @One(select = "com.mypack.dao.BooksDao.findByBid"))
    })
    List<Trolley> findAllTrolley(String uid);

    @Select("select * from trolley where orders_number =#{orders_number}")
    List<Trolley> findTrolleyByOrder(String orders_number);

    @Update("update trolley set number=#{number} where tid=#{tid}")
    int updateNumber(@Param("tid")String tid,@Param("number")String number);

    @Update("update trolley set orders_number =#{orders_number} where uid =#{uid} and orders_number is null")
    int updateOrders(@Param("orders_number")String orders_number, @Param("uid")Integer uid);
}
