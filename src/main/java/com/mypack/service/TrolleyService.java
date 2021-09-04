package com.mypack.service;

import com.mypack.domain.Trolley;

import java.util.List;

/**
 * 购物车业务层接口
 */
public interface TrolleyService {
    /**
     * 查询购物车是否存在
     *
     * @param trolley 购物车信息
     * @return 查询到的购物车信息，如果不存在则值为null
     */
    Trolley confirmIsExist(Trolley trolley);

    /**
     * 添加购物车
     *
     * @param trolley 要添加的购物车信息
     * @throws Exception
     */
    void save(Trolley trolley) throws Exception;

    /**
     * 根据图书id删除购物车信息
     *
     * @param tid 图书id
     */
    void deleteByTid(String tid);

    /**
     * 修改购物车信息
     *
     * @param trolley 要修改的购物车信息
     * @return 结果>0表示修改成功，否则失败
     */
    int updateTrolley(Trolley trolley);

    /**
     * 根据用户id查询购物车信息
     *
     * @param uid 用户id
     * @return 查询到的购物车信息集合
     */
    List<Trolley> findAllTrolley(String uid);

    /**
     * 根据订单编号查询购物车信息
     *
     * @param orders_number 订单编号
     * @return 查询到的购物车集合
     */
    List<Trolley> findTrolleyByOrder(String orders_number);

    /**
     * 修改购物车中指定条目的数量
     *
     * @param tid    图书id
     * @param number 数量
     * @return 结果>0表示修改成功，否则失败
     */
    int updateNumber(String tid, String number);

    /**
     * 修改用户的订单编号，结算了购物车中的商品才有编号
     *
     * @param orders_number 订单编号
     * @param uid           用户id
     * @return 结果>0表示修改成功，否则失败
     */
    int updateOrders(String orders_number, Integer uid);

}
