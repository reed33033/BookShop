package com.mypack.service.impl;

import com.mypack.dao.TrolleyDao;
import com.mypack.domain.Trolley;
import com.mypack.service.TrolleyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("trolleyService")
public class TrolleyServiceImpl implements TrolleyService {
    @Autowired
    private TrolleyDao trolleyDao;

    @Override
    public Trolley confirmIsExist(Trolley trolley) {
        return trolleyDao.confirmIsExist(trolley);
    }

    @Override
    public void save(Trolley trolley) throws Exception {
        /*
		 	现在参数trolley中只有uid、gid有值
		 	主键tid自增长无需考虑
		 	订单编号orders_number 默认为null也无需考虑
		 	但是商品数量number需要在此赋值
		 */
		/*
		 	当前添加的商品对应的购物车对象是否存在，是我们需要考虑的问题
		 	如果存在，我们只需要累加number商品数量即可	sql执行修改update操作	返回true
		 	如果不存在，将number赋值为1	sql执行insert插入操作	返回false
		 */
        //查询数据库，查看当前商品对应的购物车记录是否存在
        Trolley t = trolleyDao.confirmIsExist(trolley);
        if (t == null) {
            //走添加操作
            trolley.setNumber(1);
            trolleyDao.save(trolley);
        } else {
            //走修改操作
            //累加number
            t.setNumber(t.getNumber() + 1);
            trolleyDao.updateTrolley(t);
        }
    }

    @Override
    public void deleteByTid(String tid) {
trolleyDao.deleteByTid(tid);
    }

    @Override
    public int updateTrolley(Trolley trolley) {
        return trolleyDao.updateTrolley(trolley);
    }

    @Override
    public List<Trolley> findAllTrolley(String uid) {
        return trolleyDao.findAllTrolley(uid);
    }

    @Override
    public List<Trolley> findTrolleyByOrder(String orders_number) {
        return trolleyDao.findTrolleyByOrder(orders_number);
    }

    @Override
    public int updateNumber(String tid, String number) {
        return trolleyDao.updateNumber(tid,number);
    }

    @Override
    public int updateOrders(String orders_number, Integer uid) {
        return trolleyDao.updateOrders(orders_number,uid);
    }
}
