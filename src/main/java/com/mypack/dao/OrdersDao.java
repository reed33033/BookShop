package com.mypack.dao;

import com.mypack.domain.Category;
import com.mypack.domain.Orders;
import com.mypack.domain.Trolley;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface OrdersDao {
    @Select("select * from orders")
    List<Orders> findAll() throws Exception;

    @Delete(" <script>" +"delete from orders where orders_number in <foreach collection='array' item='item' open='(' close=')' separator=',' >#{item}</foreach>"+"</script>")
    void deleteByOid(@Param("array") String[] array) throws Exception;

    @Insert("insert into orders(orders_number,uid,sumPrice,ordersName,goodsCount,create_time,state) values(#{orders_number},#{uid},#{sumPrice},#{ordersName},#{goodsCount},#{create_time},#{state})")
    void save(Orders orders) throws Exception;

    @Update("update orders set state = 1 where orders_number =#{orders_number}")
    int updateState(String orders_number);

    @Select("select * from orders where uid =#{uid}")
    List<Orders> findOrdersByUid(String uid);
}
