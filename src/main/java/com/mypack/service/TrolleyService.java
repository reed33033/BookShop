package com.mypack.service;

import com.mypack.domain.Trolley;

import java.util.List;

public interface TrolleyService {
    Trolley confirmIsExist(Trolley trolley);
    void save(Trolley trolley) throws Exception;
    void deleteByTid(String tid);
    int updateTrolley(Trolley trolley);
    List<Trolley> findAllTrolley(String uid);
    List<Trolley> findTrolleyByOrder(String orders_number);
    int updateNumber(String tid,String number);
    int updateOrders(String orders_number, Integer uid);

}
