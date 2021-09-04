package com.mypack.service;

import com.mypack.domain.Category;
import com.mypack.domain.Orders;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 订单业务层接口
 */
public interface OrdersService {
    /**
     * 查询所有订单信息
     *
     * @return 查询到的订单集合
     * @throws Exception
     */
    List<Orders> findAll() throws Exception;

    /**
     * 分页查询所有订单信息
     *
     * @param page 页码值
     * @param size 每页的条数
     * @return 查询到的订单集合
     * @throws Exception
     */
    List<Orders> findAll(int page, int size) throws Exception;

    /**
     * 根据订单编号删除订单
     *
     * @param oids 要删除的订单编号
     * @throws Exception
     */
    public void deleteByOid(String[] oids) throws Exception;

    /**
     * 修改订单状态，修改为已支付
     *
     * @param orders_number 订单编号
     * @return 结果>0表示修改成功，否则修改失败
     */
    int updateState(String orders_number);

    /**
     * 根据用户id查询订单
     *
     * @param uid 用户id
     * @return 查询到的订单集合
     */
    List<Orders> findOrdersByUid(String uid);

    /**
     * 添加订单信息
     *
     * @param orders 要添加的订单信息
     * @throws Exception
     */
    void save(Orders orders) throws Exception;
}
