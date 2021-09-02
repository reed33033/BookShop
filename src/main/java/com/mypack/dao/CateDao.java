package com.mypack.dao;

import com.mypack.domain.Books;
import com.mypack.domain.Category;
import com.mypack.domain.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface CateDao {
    @Select("select * from category")
    List<Category> findAll() throws Exception;

    @Update("update category set cname=#{cname},state=#{state},order_number=#{order_number},description=#{description},create_time=#{create_time} where cid=#{cid}")
    int updateOne(Category category) throws Exception;

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

    @Insert("insert into category(cid,cname,state,order_number,description,create_time) values(#{cid},#{cname},#{state},#{order_number},#{description},#{create_time})")
    void save(Category category) throws Exception;

    @Delete(" <script>" +"delete from category where cid in <foreach collection='array' item='item' open='(' close=')' separator=',' >#{item}</foreach>"+"</script>")
    void deleteByCid(@Param("array") String[] array) throws Exception;

    @Select("select count(*) from category")
    int countCate();

}
