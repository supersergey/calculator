package com.company.calculator.dao.impl;

import com.company.calculator.dao.ExpressionDao;
import com.company.calculator.dao.UserDao;
import com.company.calculator.model.Expression;
import com.company.calculator.model.User;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by sergey on 17.05.2016.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ComponentScan("com.company.calculator")
@ContextConfiguration(classes = { com.company.calculator.App.class})

public class ExpressionDaoImplTest {

    @Autowired
    private ExpressionDao expressionDao;

    @Autowired
    private UserDao userDao;

    private Expression exp1, exp2, exp3, exp4;
    private User user1 = new User();
    private User user2 = new User();

    @Before
    public void initExpressions()
    {
        user1.setUsername("test1");
        user1.setPassword("test1");
        user1.setEmail("test1@test.com");

        user2.setUsername("test2");
        user2.setPassword("test2");
        user2.setEmail("test2@test.com");

        userDao.addUser(user1);
        userDao.addUser(user2);

        user1 = userDao.getUserByUsername(user1.getUsername());
        user2 = userDao.getUserByUsername(user2.getUsername());

        exp1 = new Expression();
        exp1.setExpression("2+2");
        exp1.setResult("4");

        exp2 = new Expression();
        exp2.setExpression("3+3");
        exp2.setResult("6");

        exp3 = new Expression();
        exp3.setExpression("3+3");
        exp3.setResult("6");

        exp4 = new Expression();
        exp4.setExpression("4+4");
        exp4.setResult("8");
    }


    @Test
    @Rollback(true)
    public void testGetAllExpressions() throws Exception {
        List<Expression> expList1 = expressionDao.getAllExpressions(user1);
        int initSize1 = expList1.size();
        List<Expression> expList2 = expressionDao.getAllExpressions(user2);
        int initSize2 = expList2.size();

        expressionDao.addExpression(user1, exp1);
        expressionDao.addExpression(user1, exp2);
        expressionDao.addExpression(user2, exp3);
        expList1 = expressionDao.getAllExpressions(user1);
        assertEquals(initSize1 + 2, expList1.size());
        expList2 = expressionDao.getAllExpressions(user2);
        assertEquals(initSize2 + 1, expList2.size());
    }

    @Test
    @Rollback(true)
    public void testAddExpression() throws Exception {
        int size = expressionDao.getAllExpressions(user1).size();
        expressionDao.addExpression(user1, exp4);
        int newSize = expressionDao.getAllExpressions(user1).size();
        assertEquals(size + 1, newSize);
    }

    @Test
    @Rollback
    public void testClearExpressions() throws Exception {
        expressionDao.addExpression(user1, exp1);
        expressionDao.addExpression(user1, exp2);
        expressionDao.addExpression(user2, exp3);
        expressionDao.clearExpressions(user1);
        assertEquals(0, expressionDao.getAllExpressions(user1).size());
        assertNotEquals(0, expressionDao.getAllExpressions(user2).size());
    }
}