package org.demo.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.demo.constants.EntitiesConst;
import org.demo.constants.UserConst;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Сущность - Пользователь
 * <p>
 * Created by Selutin_AV on 05.08.2015.
 */
@Entity
@Table(name = UserConst.SQL_USERS)
public class User extends AuthEntity {

    /**
     * Логин
     */
    @Column(name = UserConst.SQL_USERNAME, nullable = false, unique = true)
    private String username;

    /**
     * Пароль
     */
    @JsonIgnore
    @Column(name = UserConst.SQL_PASSWORD, nullable = false, unique = false)
    private String password;

    /**
     * Роли
     */
    @ManyToMany
    @JoinTable(name = EntitiesConst.SQL_USERS_ROLES,
            joinColumns = {@JoinColumn(name = EntitiesConst.SQL_USER_ID)},
            inverseJoinColumns = @JoinColumn(name = EntitiesConst.SQL_ROLE_ID))
    private Set<Role> roles;

    public User() {
    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    /**
     * Возвращает множество enable ролей пользователя или пустое множество
     *
     * @return множество enable ролей пользователя
     */
    public Set<Role> getEnableRoles() {
        Set<Role> result = new HashSet<>();
        roles.forEach(role -> {
            if (role.isEnable()) result.add(role);
        });
        return result;
    }
}
