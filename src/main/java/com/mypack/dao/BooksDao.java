package com.mypack.dao;

import com.mypack.domain.Books;
import com.mypack.domain.Category;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface BooksDao {
    @Select("select * from books")
    @Results({
            @Result(id = true, property = "bid", column = "bid"),
            @Result(property = "cid", column = "cid"),
            @Result(property = "bname", column = "bname"),
            @Result(property = "author", column = "author"),
            @Result(property = "publish", column = "publish"),
            @Result(property = "isbn", column = "isbn"),
            @Result(property = "words", column = "words"),
            @Result(property = "price", column = "price"),
            @Result(property = "description", column = "description"),
            @Result(property = "full_description", column = "full_description"),
            @Result(property = "pic", column = "pic"),
            @Result(property = "state", column = "state"),
            @Result(property = "version", column = "version"),
            @Result(property = "product_date", column = "product_date"),
            @Result(property = "category", column = "cid", javaType = Category.class, one = @One(select = "com.mypack.dao.CateDao.findByCid")),
    })
    List<Books> findAll() throws Exception;


    @Update("update books set cid=#{cid},bname=#{bname},author=#{author},publish=#{publish},isbn=#{isbn},words=#{words},price=#{price},description=#{description},full_description=#{full_description},pic=#{pic},state=#{state},version=#{version},product_date=#{product_date} where bid=#{bid}")
    int updateOne(Books books) throws Exception;

    @Select("select * from books where bid=#{bid}")
    Books findByBCid(String bid);

    @Insert("insert into books(bid,cid,bname,author,publish,isbn,words,price,description,full_description,pic,state,version,product_time) values(#{bid},#{cid},#{bname},#{author},#{publish},#{isbn},#{words},#{price},#{description},#{full_description},#{pic},#{state},#{version},#{product_time})")
    void save(Books books) throws Exception;

    @Delete(" <script>" +"delete from books where bid in <foreach collection='array' item='item' open='(' close=')' separator=',' >#{item}</foreach>"+"</script>")
    void deleteByBid(@Param("array") String[] array) throws Exception;

}
