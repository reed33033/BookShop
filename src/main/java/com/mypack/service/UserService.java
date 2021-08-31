package com.mypack.service;

import com.mypack.domain.Category;
import com.mypack.domain.User;
import org.springframework.stereotype.Component;

import java.util.List;


public interface UserService {
    public User adminLogin(String username, String password);

    List<User> findAll() throws Exception;

    List<User> findAll(int page,int size) throws Exception;

    boolean updateManager(Integer manager,String uid);

    User findByUid(String uid);

    void deleteByUid(String[] uids) throws Exception ;
}
