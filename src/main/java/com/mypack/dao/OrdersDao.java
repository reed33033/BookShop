package com.mypack.dao;

import com.mypack.domain.*;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface OrdersDao {
    @Select("select * from orders")
    @Results({
            @Result(property = "orders_number", column = "orders_number"),
            @Result(property = "uid", column = "uid"),
            @Result(property = "sumPrice", column = "sumPrice"),
            @Result(property = "goodsCount", column = "goodsCount"),
            @Result(property = "ordersName", column = "ordersName"),
            @Result(property = "create_time", column = "create_time"),
            @Result(property = "state", column = "state"),
            @Result(property = "user",column = "uid",javaType = User.class, one = @One(select = "com.mypack.dao.UserDao.findByUid"))
    })
    List<Orders> findAll() throws Exception;

    @Delete(" <script>" +"delete from orders where orders_number in <foreach collection='array' item='item' open='(' close=')' separator=',' >#{item}</foreach>"+"</script>")
    void deleteByOid(@Param("array") String[] array) throws Exception;

    @Insert("insert into orders(orders_number,uid,sumPrice,ordersName,goodsCount,create_time,state) values(#{orders_number},#{uid},#{sumPrice},#{ordersName},#{goodsCount},#{create_time},#{state})")
    void save(Orders orders) throws Exception;

    @Update("update orders set state = 1 where orders_number =#{orders_number}")
    int updateState(String orders_number);

    @Select("select * from orders where uid =#{uid}")
    @Results({
            @Result(property = "orders_number", column = "orders_number"),
            @Result(property = "uid", column = "uid"),
            @Result(property = "sumPrice", column = "sumPrice"),
            @Result(property = "goodsCount", column = "goodsCount"),
            @Result(property = "ordersName", column = "ordersName"),
            @Result(property = "create_time", column = "create_time"),
            @Result(property = "state", column = "state"),
            @Result(property = "user",column = "uid",javaType = User.class, one = @One(select = "com.mypack.dao.UserDao.findByUid"))
    })
    List<Orders> findOrdersByUid(String uid);
}
