package com.mypack.service;

import com.mypack.domain.Category;
import com.mypack.domain.User;
import org.apache.ibatis.annotations.Delete;

import java.util.List;

/**
 * 图书分类业务层接口
 */
public interface CateService {
    /**
     * 查询所有分类信息
     *
     * @return 查询到的分类集合
     * @throws Exception
     */
    List<Category> findAll() throws Exception;

    /**
     * 分页查询所有分类信息
     *
     * @param page 页码值
     * @param size 每页的条数
     * @return 查询到的分类集合
     * @throws Exception
     */
    List<Category> findAll(int page, int size) throws Exception;

    /**
     * 修改分类信息
     *
     * @param category 要修改的分类信息
     * @return 结果>0表示修改成功，否则失败
     * @throws Exception
     */
    boolean updateOne(Category category) throws Exception;

    /**
     * 根据分类id查询分类信息
     *
     * @param cid 分类id
     * @return 查询到的分类信息
     */
    public Category findByCid(String cid);

    /**
     * 添加分类信息
     *
     * @param category 要添加的分类信息
     * @throws Exception
     */
    public void save(Category category) throws Exception;

    /**
     * 根据分类id删除分类信息
     *
     * @param cids 分类id
     * @throws Exception
     */
    public void deleteByCid(String[] cids) throws Exception;

    /**
     * 查询分类信息数量
     *
     * @return 查询到的分类数量
     */
    int countCate();

}
