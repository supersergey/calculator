package com.company.calculator.model;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by sergey on 14.05.2016.
 */
public class Expression {

    private int id;

    private int userId;

    private String username;

    private String expression;

    private String result;

    private Date pubDate;

    private String pubDateStr;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPubDate(Date pubDate) {
        this.pubDate = pubDate;
        if(pubDate != null) {
            pubDateStr = new SimpleDateFormat("yyyy-MM-dd @ HH:mm").format(pubDate);
        }
    }

    public String getPubDateStr() {
        return pubDateStr;
    }

    public Date getPubDate() {
        return pubDate;
    }

    public String getExpression() {
        return expression;
    }

    public void setExpression(String expression) {
        this.expression = expression;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
}
