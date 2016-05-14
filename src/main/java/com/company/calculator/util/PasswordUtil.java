package com.company.calculator.util;

import org.mindrot.jbcrypt.BCrypt;

/**
 * Created by sergey on 14.05.2016.
 */
public class PasswordUtil {
    public static String hashPassword(String pwd) {
        String hashed = BCrypt.hashpw(pwd, BCrypt.gensalt());

        return hashed;
    }

    public static boolean verifyPassword(String pwd, String hash) {
        return BCrypt.checkpw(pwd, hash);
    }

}
