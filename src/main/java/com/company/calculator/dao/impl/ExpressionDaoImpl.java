package com.company.calculator.dao.impl;

import com.company.calculator.dao.ExpressionDao;
import com.company.calculator.model.Expression;
import com.company.calculator.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.*;

/**
 * Created by sergey on 14.05.2016.
 */
@Repository
public class ExpressionDaoImpl implements ExpressionDao {

    private NamedParameterJdbcTemplate template;

    @Autowired
    public ExpressionDaoImpl(DataSource ds) {
        template = new NamedParameterJdbcTemplate(ds);
    }


    @Override
    public List<Expression> getAllExpressions(User user) {
        Map<String, Object> params = new HashMap<>();

        params.put("id", user.getId());

        String sql = "select expression.* from expression join user on expression.user_id = user.user_id where user.user_id = :id order by expression.pub_date DESC";

        List<Expression> result = template.query(sql, params, expressionMapper);

        return result;
    }

    @Override
    public void addExpression(User user, Expression expression) {
        String sql = "insert into expression (user_id, expression, result, pub_date) values (:userId, :expression, :result, :pubDate)";

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("userId", user.getId());
        params.put("expression", expression.getExpression());
        params.put("result", expression.getResult());
        params.put("pubDate", new Date());

        template.update(sql, params);
    }

    private RowMapper<Expression> expressionMapper = (rs, rowNum) -> {
        Expression e = new Expression();

        e.setId(rs.getInt("expression_id"));
        e.setUserId(rs.getInt("user_id"));
        e.setExpression(rs.getString("expression"));
        e.setResult(rs.getString("result"));
        e.setPubDate(rs.getTimestamp("pub_date"));
        return e;
    };

    @Override
    public void clearExpressions(User user) {
        Map<String, Object> params = new HashMap<>();

        params.put("id", user.getId());
        String sql = "delete from expression where expression.user_id = :id";
        template.update(sql, params);
    }
}
