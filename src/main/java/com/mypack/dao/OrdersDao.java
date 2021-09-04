package com.mypack.dao;

import com.mypack.domain.*;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * 订单的数据库操作层
 */
public interface OrdersDao {
    /**
     * 查询所有订单信息
     *
     * @return 查询到的订单集合
     * @throws Exception
     */
    @Select("select * from orders")
    @Results({
            @Result(property = "orders_number", column = "orders_number"),
            @Result(property = "uid", column = "uid"),
            @Result(property = "sumPrice", column = "sumPrice"),
            @Result(property = "goodsCount", column = "goodsCount"),
            @Result(property = "ordersName", column = "ordersName"),
            @Result(property = "create_time", column = "create_time"),
            @Result(property = "state", column = "state"),
            @Result(property = "user", column = "uid", javaType = User.class, one = @One(select = "com.mypack.dao.UserDao.findByUid"))
    })
    List<Orders> findAll() throws Exception;

    /**
     * 根据订单编号删除订单
     *
     * @param array 要删除的订单编号
     * @throws Exception
     */
    @Delete(" <script>" + "delete from orders where orders_number in <foreach collection='array' item='item' open='(' close=')' separator=',' >#{item}</foreach>" + "</script>")
    void deleteByOid(@Param("array") String[] array) throws Exception;

    /**
     * 添加订单信息
     *
     * @param orders 要添加的订单信息
     * @throws Exception
     */
    @Insert("insert into orders(orders_number,uid,sumPrice,ordersName,goodsCount,create_time,state) values(#{orders_number},#{uid},#{sumPrice},#{ordersName},#{goodsCount},#{create_time},#{state})")
    void save(Orders orders) throws Exception;

    /**
     * 修改订单状态，修改为已支付
     *
     * @param orders_number 订单编号
     * @return 结果>0表示修改成功，否则修改失败
     */
    @Update("update orders set state = 1 where orders_number =#{orders_number}")
    int updateState(String orders_number);

    /**
     * 根据用户id查询订单
     *
     * @param uid 用户id
     * @return 查询到的订单集合
     */
    @Select("select * from orders where uid =#{uid}")
    @Results({
            @Result(property = "orders_number", column = "orders_number"),
            @Result(property = "uid", column = "uid"),
            @Result(property = "sumPrice", column = "sumPrice"),
            @Result(property = "goodsCount", column = "goodsCount"),
            @Result(property = "ordersName", column = "ordersName"),
            @Result(property = "create_time", column = "create_time"),
            @Result(property = "state", column = "state"),
            @Result(property = "user", column = "uid", javaType = User.class, one = @One(select = "com.mypack.dao.UserDao.findByUid"))
    })
    List<Orders> findOrdersByUid(String uid);
}
