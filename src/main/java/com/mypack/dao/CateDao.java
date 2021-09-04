package com.mypack.dao;

import com.mypack.domain.Books;
import com.mypack.domain.Category;
import com.mypack.domain.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * 分类的数据库操作层
 */
public interface CateDao {
    /**
     * 查询所有分类信息
     *
     * @return 查询到的分类集合
     * @throws Exception
     */
    @Select("select * from category")
    List<Category> findAll() throws Exception;

    /**
     * 修改分类信息
     *
     * @param category 要修改的分类信息
     * @return 结果>0表示修改成功，否则失败
     * @throws Exception
     */
    @Update("update category set cname=#{cname},state=#{state},order_number=#{order_number},description=#{description},create_time=#{create_time} where cid=#{cid}")
    int updateOne(Category category) throws Exception;

    /**
     * 根据分类id查询分类信息
     *
     * @param cid 分类id
     * @return 查询到的分类信息
     */
    @Select("select * from category where cid=#{cid}")
    @Results({
            @Result(id = true, property = "cid", column = "cid"),
            @Result(property = "cname", column = "cname"),
            @Result(property = "state", column = "state"),
            @Result(property = "number", column = "number"),
            @Result(property = "order_number", column = "order_number"),
            @Result(property = "description", column = "description"),
            @Result(property = "create_time", column = "create_time")
    })
    Category findByCid(String cid);

    /**
     * 添加分类信息
     *
     * @param category 要添加的分类信息
     * @throws Exception
     */
    @Insert("insert into category(cid,cname,state,order_number,description,create_time) values(#{cid},#{cname},#{state},#{order_number},#{description},#{create_time})")
    void save(Category category) throws Exception;

    /**
     * 根据分类id删除分类信息
     *
     * @param array 分类id
     * @throws Exception
     */
    @Delete(" <script>" + "delete from category where cid in <foreach collection='array' item='item' open='(' close=')' separator=',' >#{item}</foreach>" + "</script>")
    void deleteByCid(@Param("array") String[] array) throws Exception;

    /**
     * 查询分类信息数量
     *
     * @return 查询到的分类数量
     */
    @Select("select count(*) from category")
    int countCate();

}
