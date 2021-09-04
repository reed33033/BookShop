package com.mypack.service.impl;

import com.mypack.dao.TrolleyDao;
import com.mypack.domain.Trolley;
import com.mypack.service.TrolleyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 购物车业务层接口实现类
 */
@Service("trolleyService")
public class TrolleyServiceImpl implements TrolleyService {
    /**
     * 注入的购物车数据库操作层对象
     */
    @Autowired
    private TrolleyDao trolleyDao;

    /**
     * 查询购物车是否存在
     *
     * @param trolley 购物车信息
     * @return 查询到的购物车信息，如果不存在则值为null
     */
    @Override
    public Trolley confirmIsExist(Trolley trolley) {
        return trolleyDao.confirmIsExist(trolley);
    }

    /**
     * 添加购物车
     *
     * @param trolley 要添加的购物车信息
     * @throws Exception
     */
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

    /**
     * 根据图书id删除购物车信息
     *
     * @param tid 图书id
     */
    @Override
    public void deleteByTid(String tid) {
        trolleyDao.deleteByTid(tid);
    }

    /**
     * 修改购物车信息
     *
     * @param trolley 要修改的购物车信息
     * @return 结果>0表示修改成功，否则失败
     */
    @Override
    public int updateTrolley(Trolley trolley) {
        return trolleyDao.updateTrolley(trolley);
    }

    /**
     * 根据用户id查询购物车信息
     *
     * @param uid 用户id
     * @return 查询到的购物车信息集合
     */
    @Override
    public List<Trolley> findAllTrolley(String uid) {
        return trolleyDao.findAllTrolley(uid);
    }

    /**
     * 根据订单编号查询购物车信息
     *
     * @param orders_number 订单编号
     * @return 查询到的购物车集合
     */
    @Override
    public List<Trolley> findTrolleyByOrder(String orders_number) {
        return trolleyDao.findTrolleyByOrder(orders_number);
    }

    /**
     * 修改购物车中指定条目的数量
     *
     * @param tid    图书id
     * @param number 数量
     * @return 结果>0表示修改成功，否则失败
     */
    @Override
    public int updateNumber(String tid, String number) {
        return trolleyDao.updateNumber(tid, number);
    }

    /**
     * 修改用户的订单编号，结算了购物车中的商品才有编号
     *
     * @param orders_number 订单编号
     * @param uid           用户id
     * @return 结果>0表示修改成功，否则失败
     */
    @Override
    public int updateOrders(String orders_number, Integer uid) {
        return trolleyDao.updateOrders(orders_number, uid);
    }
}
