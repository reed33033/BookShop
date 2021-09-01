package com.mypack.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.mypack.dao.CateDao;
import com.mypack.domain.Category;
import com.mypack.service.CateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("cateService")
@Transactional
public class CateServiceImpl implements CateService {
    @Autowired
    private CateDao cateDao;

    @Override
    public List<Category> findAll() throws Exception {
        return cateDao.findAll();
    }

    @Override
    public List<Category> findAll(int page, int size) throws Exception {
        //参数pageNum 是页码值   参数pageSize 代表是每页显示条数
        Page page1=PageHelper.startPage(page, size);
        int total = (int) page1.getTotal();
        page1.setTotal(total);
        return cateDao.findAll();
    }

    @Override
    public boolean updateOne(Category category) throws Exception {
        int row = cateDao.updateOne(category);
        if(row>0){
            return true;
        }
        return false;
    }

    @Override
    public  Category findByCid(String cid){
        Category cate = cateDao.findByCid(cid);
        return cate;
    }

    @Override
    public void save(Category category) throws Exception {
        cateDao.save(category);
    }

    @Override
    public void deleteByCid(String[] cids) throws Exception {
        cateDao.deleteByCid(cids);
    }

    @Override
    public int countCate() {
        return cateDao.countCate();
    }
}
