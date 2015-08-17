package org.demo.constants;

/**
 * Константы сущности Пользователи
 *
 * @author Selutin_AV
 * @since 17.08.2015 14:57
 */
public class UserConst extends AbstractConst {

    /**
     * SQL
     */
    public static final String SQL_USERS = "users";
    public static final String SQL_USERNAME = "username";
    public static final String SQL_PASSWORD = "password";

    /**
     * REST
     */
    public static final String REST_USERS = "/users";
    public static final String REST_USERS_ADD = "/add";
    public static final String REST_USERS_ID = "/{id}";

    /**
     * OTHER
     */
    public static final String PATTERN_USERS_ADD = "add";
    public static final String PATH_USERS_ID = "id";
}
