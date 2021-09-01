package com.mypack.service;

import com.mypack.domain.Category;
import com.mypack.domain.User;
import org.apache.ibatis.annotations.Delete;

import java.util.List;

public interface CateService {
    List<Category> findAll() throws Exception;

    List<Category> findAll(int page, int size) throws Exception;

    boolean updateOne(Category category) throws Exception;

    public Category findByCid(String cid);

    public void save(Category category) throws Exception;

    public void deleteByCid(String[] cids) throws Exception ;

    int countCate();

}
