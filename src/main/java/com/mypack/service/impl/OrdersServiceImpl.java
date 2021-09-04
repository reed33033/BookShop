package com.mypack.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.mypack.dao.OrdersDao;
import com.mypack.domain.Orders;
import com.mypack.service.OrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 订单业务层接口实现类
 */
@Service("ordersService")
@Transactional
public class OrdersServiceImpl implements OrdersService {

    /**
     * 注入的订单数据库操作层对象
     */
    @Autowired
    private OrdersDao ordersDao;

    /**
     * 查询所有订单信息
     *
     * @return 查询到的订单集合
     * @throws Exception
     */
    @Override
    public List<Orders> findAll() throws Exception {
        return ordersDao.findAll();
    }

    /**
     * 分页查询所有订单信息
     *
     * @param page 页码值
     * @param size 每页的条数
     * @return 查询到的订单集合
     * @throws Exception
     */
    @Override
    public List<Orders> findAll(int page, int size) throws Exception {
        //参数pageNum 是页码值   参数pageSize 代表是每页显示条数
        Page page1 = PageHelper.startPage(page, size);
        int total = (int) page1.getTotal();
        page1.setTotal(total);
        return ordersDao.findAll();
    }

    /**
     * 根据订单编号删除订单
     *
     * @param oids 要删除的订单编号
     * @throws Exception
     */
    @Override
    public void deleteByOid(String[] oids) throws Exception {
        ordersDao.deleteByOid(oids);
    }

    /**
     * 修改订单状态，修改为已支付
     *
     * @param orders_number 订单编号
     * @return 结果>0表示修改成功，否则修改失败
     */
    @Override
    public int updateState(String orders_number) {
        return ordersDao.updateState(orders_number);
    }

    /**
     * 根据用户id查询订单
     *
     * @param uid 用户id
     * @return 查询到的订单集合
     */
    @Override
    public List<Orders> findOrdersByUid(String uid) {
        return ordersDao.findOrdersByUid(uid);
    }

    /**
     * 添加订单信息
     *
     * @param orders 要添加的订单信息
     * @throws Exception
     */
    @Override
    public void save(Orders orders) throws Exception {
        orders.setState(0);
        ordersDao.save(orders);
    }
}
