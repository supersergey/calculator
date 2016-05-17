package com.company.calculator.service;

import com.company.calculator.model.LoginResult;
import com.company.calculator.model.User;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.*;

/**
 * Created by sergey on 17.05.2016.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ComponentScan("com.company.calculator")
@ContextConfiguration(classes = { com.company.calculator.App.class})
public class MainServiceTest {

    User fakeUser = new User();
    User realUser = new User();

    @Autowired
    private MainService service;

    @Before
    public void initUser()
    {
        realUser.setUsername("test");
        realUser.setPassword("test");
        realUser.setEmail("test@test.com");
    }

    @Test
    public void testGetUserbyUsername() throws Exception {
        // tested on DAO level, no additional logic
    }

    @Test
    @Transactional
    public void testRegisterUser() throws Exception {
        service.registerUser(realUser);
        User aUser = service.getUserbyUsername(realUser.getUsername());
        assertEquals(realUser, aUser);
    }

    @Test
    @Transactional
    public void testCheckUser() throws Exception {
        LoginResult result = service.checkUser(fakeUser);
        assertNotNull(result.getError());
        fakeUser.setPassword("abc");
        fakeUser.setUsername("fake");
        fakeUser.setEmail("abc@abc.com");
        service.registerUser(fakeUser);
        fakeUser.setPassword("abcdd");
        result = service.checkUser(fakeUser);
        assertNotNull(result.getError());
    }

    @Test
    public void testGetExpressions() throws Exception {
        // tested on DAO level, no additional logic
    }

    @Test
    public void testAddExpressions() throws Exception {
        // tested on DAO level, no additional logic
    }

    @Test
    public void testClearHistory() throws Exception {
        // tested on DAO level, no additional logic
    }
}