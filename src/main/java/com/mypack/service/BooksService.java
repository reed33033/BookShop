package com.mypack.service;

import com.mypack.domain.Books;
import com.mypack.domain.Category;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BooksService {
    List<Books> findAll() throws Exception;
    public List<Books> findAll(int page, int size) throws Exception;
    int updateOne(Books books) throws Exception;
    Books findByBid(String bid);
    void save(Books books) throws Exception;
    void deleteByBid(String[] array) throws Exception;
    int selectBooksCount(int cid);

    List<Books> findBooksByCid(String cid,int page, int size) throws Exception;
    Books findBooksByIsbn(String isbn) throws Exception;
    List<Books> findBooksByBname(String bname,int page, int size);
    List<Books> findBooksByState(int state, int page, int size);
}
