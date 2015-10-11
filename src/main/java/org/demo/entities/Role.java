package org.demo.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.demo.constants.RoleConst;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.util.Set;

/**
 * Сущность - Роль
 *
 * @author Selutin_AV
 * @since 14.08.2015 12:39
 */
@Entity
@Table(name = RoleConst.SQL_ROLES)
public class Role extends AbstractEntity implements GrantedAuthority {

    /**
     * Роль
     */
    @Column(name = RoleConst.SQL_ROLE, nullable = false)
    @Enumerated(EnumType.STRING)
    private RoleEnum role;

    /**
     * Пользователи
     */
    @JsonIgnore
    @ManyToMany(mappedBy = RoleConst.SQL_ROLES)
    private Set<User> users;

    public Role() {
    }

    public Role(RoleEnum role) {
        this.role = role;
    }

    public RoleEnum getRole() {
        return role;
    }

    public void setRole(RoleEnum role) {
        this.role = role;
    }

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }

    @Override
    public String getAuthority() {
        return getRole().name();
    }
}
