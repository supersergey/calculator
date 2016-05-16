package com.company.calculator.dao;

import com.company.calculator.model.Expression;
import com.company.calculator.model.User;

import java.util.List;

/**
 * Created by sergey on 14.05.2016.
 */
public interface ExpressionDao {

    List<Expression> getAllExpressions(User user);
    void addExpression(User user, Expression expression);
    void clearExpressions(User user);

}
