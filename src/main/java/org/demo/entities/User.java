package org.demo.entities;

import org.demo.constants.EntitiesConst;

import javax.persistence.*;
import java.util.Set;

/**
 * Сущность - Пользователь
 * <p>
 * Created by Selutin_AV on 05.08.2015.
 */
@Entity
@Table(name = EntitiesConst.SQL_USERS)
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String username;
    private String password;

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

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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
}
