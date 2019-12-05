package com.ffy.blog.service;

import com.ffy.blog.po.User;

public interface UserService {

    User findUserLogin(String username , String password);

}
