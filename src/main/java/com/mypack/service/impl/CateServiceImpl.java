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

/**
 * 图书分类业务层接口实现类
 */
@Service("cateService")
@Transactional
public class CateServiceImpl implements CateService {
    /**
     * 注入的分类数据库操作层对象
     */
    @Autowired
    private CateDao cateDao;

    /**
     * 查询所有分类信息
     *
     * @return 查询到的分类集合
     * @throws Exception
     */
    @Override
    public List<Category> findAll() throws Exception {
        return cateDao.findAll();
    }

    /**
     * 分页查询所有分类信息
     *
     * @param page 页码值
     * @param size 每页的条数
     * @return 查询到的分类集合
     * @throws Exception
     */
    @Override
    public List<Category> findAll(int page, int size) throws Exception {
        //参数pageNum 是页码值   参数pageSize 代表是每页显示条数
        Page page1 = PageHelper.startPage(page, size);
        int total = (int) page1.getTotal();
        page1.setTotal(total);
        return cateDao.findAll();
    }

    /**
     * 修改分类信息
     *
     * @param category 要修改的分类信息
     * @return 结果>0表示修改成功，否则失败
     * @throws Exception
     */
    @Override
    public boolean updateOne(Category category) throws Exception {
        int row = cateDao.updateOne(category);
        if (row > 0) {
            return true;
        }
        return false;
    }

    /**
     * 根据分类id查询分类信息
     *
     * @param cid 分类id
     * @return 查询到的分类信息
     */
    @Override
    public Category findByCid(String cid) {
        Category cate = cateDao.findByCid(cid);
        return cate;
    }

    /**
     * 添加分类信息
     *
     * @param category 要添加的分类信息
     * @throws Exception
     */
    @Override
    public void save(Category category) throws Exception {
        cateDao.save(category);
    }

    /**
     * 根据分类id删除分类信息
     *
     * @param cids 分类id
     * @throws Exception
     */
    @Override
    public void deleteByCid(String[] cids) throws Exception {
        cateDao.deleteByCid(cids);
    }

    /**
     * 查询分类信息数量
     *
     * @return 查询到的分类数量
     */
    @Override
    public int countCate() {
        return cateDao.countCate();
    }
}
