package com.mypack.dao;

import com.mypack.domain.Category;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface CateDao {
    @Select("select * from category")
    List<Category> findAll() throws Exception;

    @Update("update category set cname=#{cname},state=#{state},order_number=#{order_number},description=#{description},create_time=#{create_time} where cid=#{cid}")
    int updateOne(Category category) throws Exception;

    @Select("select * from category where cid=#{cid}")
    Category findByCid(String cid);

    @Insert("insert into category(cid,cname,state,order_number,description,create_time) values(#{cid},#{cname},#{state},#{order_number},#{description},#{create_time})")
    void save(Category category) throws Exception;

    @Delete(" <script>" +"delete from category where cid in <foreach collection='array' item='item' open='(' close=')' separator=',' >#{item}</foreach>"+"</script>")
    void deleteByCid(@Param("array") String[] array) throws Exception;

}
