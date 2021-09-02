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

@Service("ordersService")
@Transactional
public class OrdersServiceImpl implements OrdersService {

    @Autowired
    private OrdersDao ordersDao;

    @Override
    public List<Orders> findAll() throws Exception {
        return ordersDao.findAll();
    }

    @Override
    public List<Orders> findAll(int page, int size) throws Exception {
        //参数pageNum 是页码值   参数pageSize 代表是每页显示条数
        Page page1= PageHelper.startPage(page, size);
        int total = (int) page1.getTotal();
        page1.setTotal(total);
        return ordersDao.findAll();
    }

    @Override
    public void deleteByOid(String[] oids) throws Exception {
        ordersDao.deleteByOid(oids);
    }

    @Override
    public int updateState(String orders_number) {
        return ordersDao.updateState(orders_number);
    }

    @Override
    public List<Orders> findOrdersByUid(String uid) {
        return ordersDao.findOrdersByUid(uid);
    }

    @Override
    public void save(Orders orders) throws Exception {
        ordersDao.save(orders);
    }
}
