package com.company.calculator.dao.Impl;

import com.company.calculator.dao.ExpressionDao;
import com.company.calculator.model.Expression;
import com.company.calculator.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

        String sql = "select * from expression LEFT JOIN user on user.id = :id";

        List<Expression> result = template.query(sql, params, expressionMapper);

        return result;
    }

    @Override
    public void addExpression(User user, Expression expression) {

    }

    private RowMapper<Expression> expressionMapper = (rs, rowNum) -> {
        Expression e = new Expression();

        e.setId(rs.getInt("message_id"));
        e.setUserId(rs.getInt("author_id"));
        e.setUsername(rs.getString("username"));
        e.setExpression(rs.getString("expression"));
        e.setResult(rs.getString("result"));
        e.setPubDate(rs.getTimestamp("pub_date"));
        return e;
    };

}
