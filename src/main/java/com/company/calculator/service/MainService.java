package com.company.calculator.service;

import com.company.calculator.dao.ExpressionDao;
import com.company.calculator.dao.UserDao;
import com.company.calculator.model.LoginResult;
import com.company.calculator.model.User;
import com.company.calculator.util.PasswordUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by sergey on 14.05.2016.
 */
@Service
public class MainService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private ExpressionDao expressionDao;

    public User getUserbyUsername(String username) {
        return userDao.getUserbyUsername(username);
    }

    public void registerUser(User user) {
        userDao.registerUser(user);
    }

    public LoginResult checkUser(User user) {
        LoginResult result = new LoginResult();
        User userFound = userDao.getUserbyUsername(user.getUsername());
        if(userFound == null) {
            result.setError("Invalid username");
        } else if(!PasswordUtil.verifyPassword(user.getPassword(), userFound.getPassword())) {
            result.setError("Invalid password");
        } else {
            result.setUser(userFound);
        }

        return result;
    }
}
