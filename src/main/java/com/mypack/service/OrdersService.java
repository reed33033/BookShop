package com.mypack.service;

import com.mypack.domain.Category;
import com.mypack.domain.Orders;

import java.util.List;

public interface OrdersService {
    List<Orders> findAll() throws Exception;

    List<Orders> findAll(int page, int size) throws Exception;

    public void deleteByOid(String[] oids) throws Exception ;
}
