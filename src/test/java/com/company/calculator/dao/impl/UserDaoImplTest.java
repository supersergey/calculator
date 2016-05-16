package com.company.calculator.dao.impl;

import com.company.calculator.dao.UserDao;
import com.company.calculator.model.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.ContextHierarchy;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;

/**
 * Created by sergey on 17.05.2016.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ComponentScan("com.company.calculator")
@ContextConfiguration(classes = { com.company.calculator.App.class})
public class UserDaoImplTest {

    @Autowired
    private UserDao userDao;
    private User user;

    {
        user = new User();
        user.setUsername("test");
        user.setPassword("test");
        user.setEmail("test@test.com");
    }


    @Test
    public void testGetUserByUsername_Negative() throws Exception {
        User user = userDao.getUserByUsername("abc");
        assertNull(user);
    }

    @Test
    public void testAddUser() throws Exception {
        userDao.addUser(user);
        User testUser = userDao.getUserByUsername(user.getUsername());
        assertNotNull(testUser);
        assertEquals(user, testUser);
    }
}