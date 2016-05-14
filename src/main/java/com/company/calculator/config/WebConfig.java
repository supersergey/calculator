package com.company.calculator.config;

import static spark.Spark.before;
import static spark.Spark.get;
import static spark.Spark.halt;
import static spark.Spark.post;
import static spark.SparkBase.staticFileLocation;


import com.company.calculator.model.LoginResult;
import com.company.calculator.model.User;
import com.company.calculator.service.MainService;
import org.apache.commons.beanutils.BeanUtils;
import org.eclipse.jetty.util.MultiMap;
import org.eclipse.jetty.util.UrlEncoded;
import spark.ModelAndView;
import spark.Request;
import spark.template.freemarker.FreeMarkerEngine;
import spark.utils.StringUtils;

import java.util.HashMap;
import java.util.Map;

import static spark.SparkBase.staticFileLocation;

/**
 * Created by sergey on 14.05.2016.
 */
public class WebConfig {

    private static final String USER_SESSION_ID = "user";

    private MainService service;

    public WebConfig(MainService service) {
        this.service = service;
        staticFileLocation("/public");
        setupRoutes();
    }

    private void setupRoutes() {

        /* default route - if the user is not logged in, redirect to the login page
            if the user is logged in, display the main page /getExpressions
         */

        before("/", (req, res) -> {
            User user = getAuthenticatedUser(req);
            if(user == null) {
                res.redirect("/login");
                halt();
            }
            else
            {
                res.redirect("/getExpressions?user=" + user.getUsername());
                halt();
            }
        });

        get("/login", (req, res) -> {
            Map<String, Object> map = new HashMap<>();
            if(req.queryParams("r") != null) {
                map.put("message", "You were successfully registered and can login now");
            }
            return new ModelAndView(map, "login.ftl");
        }, new FreeMarkerEngine());

		/*
		 * Logs the user in.
		 */
        post("/login", (req, res) -> {
            Map<String, Object> map = new HashMap<>();
            User user = new User();
            try {
                MultiMap<String> params = new MultiMap<>();
                UrlEncoded.decodeTo(req.body(), params, "UTF-8", -1);
                BeanUtils.populate(user, params);
            } catch (Exception e) {
                halt(501);
                return null;
            }
            LoginResult result = service.checkUser(user);
            if(result.getUser() != null) {
                addAuthenticatedUser(req, result.getUser());
                res.redirect("/");
                halt();
            } else {
                map.put("error", result.getError());
            }
            map.put("username", user.getUsername());
            return new ModelAndView(map, "login.ftl");
        }, new FreeMarkerEngine());
		/*
		 * Checks if the user is already authenticated
		 */
        before("/login", (req, res) -> {
            User authUser = getAuthenticatedUser(req);
            if(authUser != null) {
                res.redirect("/");
                halt();
            }
        });


		/*
		 * Presents the register form or redirect the user to
		 * her timeline if it's already logged in
		 */
        get("/register", (req, res) -> {
            Map<String, Object> map = new HashMap<>();
            return new ModelAndView(map, "register.ftl");
        }, new FreeMarkerEngine());
		/*
		 * Registers the user.
		 */
        post("/register", (req, res) -> {
            Map<String, Object> map = new HashMap<>();
            User user = new User();
            try {
                MultiMap<String> params = new MultiMap<>();
                UrlEncoded.decodeTo(req.body(), params, "UTF-8", -1);
                BeanUtils.populate(user, params);
            } catch (Exception e) {
                halt(501);
                return null;
            }
            String error = user.validate();
            if(StringUtils.isEmpty(error)) {
                User existingUser = service.getUserbyUsername(user.getUsername());
                if(existingUser == null) {
                    service.registerUser(user);
                    res.redirect("/login?r=1");
                    halt();
                } else {
                    error = "The username is already taken";
                }
            }
            map.put("error", error);
            map.put("username", user.getUsername());
            map.put("email", user.getEmail());
            return new ModelAndView(map, "register.ftl");
        }, new FreeMarkerEngine());
		/*
		 * Checks if the user is already authenticated
		 */
        before("/register", (req, res) -> {
            User authUser = getAuthenticatedUser(req);
            if(authUser != null) {
                res.redirect("/");
                halt();
            }
        });



    /*
     * Registers the user.
     */
        post("/register", (req, res) -> {
            Map<String, Object> map = new HashMap<>();
            User user = new User();
            try {
                MultiMap<String> params = new MultiMap<>();
                UrlEncoded.decodeTo(req.body(), params, "UTF-8", -1);
                BeanUtils.populate(user, params);
            } catch (Exception e) {
                halt(501);
                return null;
            }
            String error = user.validate();
            if (StringUtils.isEmpty(error)) {
                User existingUser = service.getUserbyUsername(user.getUsername());
                if (existingUser == null) {
                    service.registerUser(user);
                    res.redirect("/login?r=1");
                    halt();
                } else {
                    error = "The username is already taken";
                }
            }
            map.put("error", error);
            map.put("username", user.getUsername());
            map.put("email", user.getEmail());
            return new ModelAndView(map, "register.ftl");
        }, new FreeMarkerEngine());
    /*
     * Checks if the user is already authenticated
     */
        before("/register", (req, res) -> {
            User authUser = getAuthenticatedUser(req);
            if (authUser != null) {
                res.redirect("/");
                halt();
            }
        });
    }

    private void addAuthenticatedUser(Request request, User u) {
        request.session().attribute(USER_SESSION_ID, u);
    }

    private void removeAuthenticatedUser(Request request) {
        request.session().removeAttribute(USER_SESSION_ID);
    }

    private User getAuthenticatedUser(Request request) {
        return request.session().attribute(USER_SESSION_ID);
    }

}
