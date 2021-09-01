package com.mypack.dao;

import com.mypack.domain.Category;
import com.mypack.domain.Orders;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface OrdersDao {
    @Select("select * from orders")
    List<Orders> findAll() throws Exception;

    @Delete(" <script>" +"delete from orders where orders_number in <foreach collection='array' item='item' open='(' close=')' separator=',' >#{item}</foreach>"+"</script>")
    void deleteByOid(@Param("array") String[] array) throws Exception;
}
