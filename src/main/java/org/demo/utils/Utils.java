package org.demo.utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * Инструментарий
 *
 * @author Selutin_AV
 * @since 17.08.2015 16:44
 */
public class Utils {

    /**
     * Generate hashed password
     *
     * @param password пароль
     * @return хэш код пароля
     */
    public static String GenerateHashedPassword(String password) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String hashedPassword = passwordEncoder.encode(password);
        return hashedPassword;
    }
}
