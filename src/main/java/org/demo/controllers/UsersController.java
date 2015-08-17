package org.demo.controllers;

import org.demo.constants.UserConst;
import org.demo.entities.User;
import org.demo.repositories.IUserRepository;
import org.demo.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

/**
 * Контроллер - Пользователи
 * Доступен для роли ROLE_ADMIN
 *
 * @author Selutin_AV
 * @since 14.08.2015 14:33
 */
@RestController
@RequestMapping(UserConst.REST_USERS)
// TODO рефакторинг в @securityService.hasPermission
@PreAuthorize("hasRole('ROLE_ADMIN')")
public class UsersController {

    @Autowired
    IUserRepository users;

    /**
     * Возвращение списка пользователей
     * Метод запроса - GET
     *
     * @return список пользователей
     */
    @RequestMapping(method = RequestMethod.GET)
    public List<User> getUsers() {
        List<User> result = new ArrayList<>();
        users.findAll().forEach(result::add);
        return result;
    }

    /**
     * Добавление пользователя по имени, паролю с подтверждением пароля
     * Метод запроса - POST
     *
     * @param username        имя пользователя
     * @param password        пароль пользовтаеля
     * @param passwordConfirm подтверждение пароля пользователя
     * @return пользователь
     */
    @RequestMapping(method = RequestMethod.POST)
    public User addUser(String username, String password, String passwordConfirm) {

        // Запрет пустых полей
        if (username.isEmpty() || password.isEmpty() || passwordConfirm.isEmpty())
            return null;

        // Запрет несоответствия паролей
        if (!password.equals(passwordConfirm))
            return null;

        // Generate hashed password
        String hashedPassword = Utils.GenerateHashedPassword(password);

        return users.save(new User(username, hashedPassword));
    }

    /**
     * Создание вложенного маршрута /add для шаблона add
     * на добавление пользователя
     * Метод запроса - GET
     *
     * @return пара ModelAndView для контроллера
     */
    @RequestMapping(value = UserConst.REST_USERS_ADD, method = RequestMethod.GET)
    public ModelAndView getUserForm() {
        return new ModelAndView(UserConst.PATTERN_USERS_ADD);
    }

    /**
     * Удаление пользователя по id
     * Метод запроса - DELETE
     *
     * @param id идентификатор пользователя
     */
    @RequestMapping(value = UserConst.REST_USERS_ID, method = RequestMethod.DELETE)
    public void delete(@PathVariable(UserConst.PATH_USERS_ID) Long id) {
        users.delete(id);
    }

    /**
     * Получение пользователя по id
     * Метод запроса - GET
     *
     * @param id идентификатор пользователя
     * @return пользователь
     */
    @RequestMapping(value = UserConst.REST_USERS_ID, method = RequestMethod.GET)
    public User getUser(@PathVariable(UserConst.PATH_USERS_ID) Long id) {
        return users.findOne(id);
    }
}
