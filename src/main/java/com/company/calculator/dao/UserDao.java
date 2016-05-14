package com.company.calculator.dao;

import com.company.calculator.model.User;

/**
 * Created by sergey on 14.05.2016.
 */
public interface UserDao {

    User getUserbyUsername(String username);
    void registerUser(User user);
}
